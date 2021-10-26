package com.andysworkshop.postsreader.model

import com.andysworkshop.postsreader.database.PostsReaderDatabase
import com.andysworkshop.postsreader.database.entities.Post
import com.andysworkshop.postsreader.database.entities.User
import com.andysworkshop.postsreader.networking.INetworkInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class Store @Inject constructor(
    private val networkInterface: INetworkInterface,
    private val postsReaderDb: PostsReaderDatabase
) : IStore {

    private val _postsData = MutableSharedFlow<PostsDataRequestResult>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    override val postsData: SharedFlow<PostsDataRequestResult>
        get() {
            return _postsData
        }

    override fun refreshData(scope: CoroutineScope) {
        scope.launch(Dispatchers.IO) {
            refreshPostDataFromServer()
        }
    }

    override fun requestPostsData(scope: CoroutineScope) {
        scope.launch(Dispatchers.IO) {
            getPostsDataFromDB()
        }
    }

    private fun getUserById(userId: String): UserData {
        return try {
            val user = postsReaderDb.userDao().getUserNameById(userId)
            UserData(
                name = user.name,
                id = user.id
            )
        } catch (error: Throwable) {
            UserData(
                name = "Unknown",
                id = userId
            )
        }
    }

    private fun getPostsDataFromDB() {
         try {
            val postsFromDB = postsReaderDb.postDao().getAll()
            _postsData.tryEmit(
                PostsDataRequestResult.Success(
                    postsFromDB.map {
                        PostData(
                            id = it.id,
                            title = it.title,
                            body = it.body,
                            userId = it.userId,
                            userName = getUserById(it.userId).name
                        )
                    })
            )
        } catch (error: Throwable) {
            _postsData.tryEmit(
                PostsDataRequestResult.Error(error.message?: "Unknown error")
            )
        }
    }

    private suspend fun onRequestPostDataSuccess(postsListData: List<PostData>) {
        postsReaderDb.postDao().deleteAll()
        val postEntities: List<Post> = postsListData.map {
            Post(
                id = it.id,
                title = it.title,
                body = it.body,
                userId = it.userId
            )
        }
        postsReaderDb.postDao().insertAll(postEntities)
        postsListData.forEach { post ->
            when (val userDataResult = networkInterface.requestUserData(post.userId)) {
                is UserDataRequestResult.Success -> {
                    post.userName = userDataResult.value.name
                    postsReaderDb.userDao().insert(
                        User(name = userDataResult.value.name, id = userDataResult.value.id)
                    )
                }
                is UserDataRequestResult.Error -> {
                    _postsData.tryEmit(PostsDataRequestResult.Error(userDataResult.message))
                }
            }
        }
        _postsData.tryEmit(PostsDataRequestResult.Success(postsListData))
    }

    private suspend fun refreshPostDataFromServer() {
        when (val postDataResult = networkInterface.requestPostsData()) {
            is PostsDataRequestResult.Success -> {
                onRequestPostDataSuccess(postDataResult.value)
            }
            is PostsDataRequestResult.Error -> {
                _postsData.tryEmit(PostsDataRequestResult.Error(postDataResult.message))
            }
        }
    }
}