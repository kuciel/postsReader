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

    private val _postsData = MutableSharedFlow<List<PostData>>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    override val postsData: SharedFlow<List<PostData>>
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

    override suspend fun getUserById(scope: CoroutineScope, userId: String): UsersData {
        val user = postsReaderDb.userDao().getUserNameById(userId)
        return UsersData(
            name = user.name,
            id = user.id
        )
    }

    private fun getPostsDataFromDB(){
        val postsFromDB = postsReaderDb.postDao().getAll()
        _postsData.tryEmit(postsFromDB.map {
            PostData(
                id = it.id,
                title = it.title,
                body = it.body,
                userId = it.userId
            )
        })
    }

    private suspend fun refreshPostDataFromServer() {
        val postData = networkInterface.requestPostsData()
        postsReaderDb.postDao().deleteAll()
        val postEntities: List<Post> = postData.map {
            Post(
                id = it.id,
                title = it.title,
                body = it.body,
                userId = it.userId
            )
        }
        postsReaderDb.postDao().insertAll(postEntities)
        postData.forEach {
            postData ->
                val userData = networkInterface.requestUserData(postData.userId)
                postsReaderDb.userDao().insert(
                    User(
                        name = userData.name,
                        id = userData.id
                    ))
        }
        _postsData.tryEmit(postData)
    }
}