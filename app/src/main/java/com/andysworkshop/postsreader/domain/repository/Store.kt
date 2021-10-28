package com.andysworkshop.postsreader.domain.repository

import com.andysworkshop.postsreader.database.IDatabaseInterface
import com.andysworkshop.postsreader.domain.data.PostData
import com.andysworkshop.postsreader.domain.data.PostsDataRequestResult
import com.andysworkshop.postsreader.domain.data.UserDataRequestResult
import com.andysworkshop.postsreader.domain.usecases.IGetPostsFromRemoteUseCase
import com.andysworkshop.postsreader.networking.usecases.GetUserFromRemoteUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class Store @Inject constructor(
    private val getPostsFromRemoteUseCase: IGetPostsFromRemoteUseCase,
    private val getUserFromRemoteUseCase: GetUserFromRemoteUseCase,
    private val databaseInterface: IDatabaseInterface
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

    private fun getPostsDataFromDB() {
            _postsData.tryEmit(
                databaseInterface.getAllPosts()
            )
    }

    private suspend fun onRequestPostDataSuccess(postsListData: List<PostData>) {
        databaseInterface.insertPosts(postsListData)
        postsListData.forEach { post ->
            when (val userDataResult = getUserFromRemoteUseCase.invoke(post.userId)) {
                is UserDataRequestResult.Success -> {
                    post.userName = userDataResult.value.name
                    databaseInterface.insertUser(userDataResult.value)
                }
                is UserDataRequestResult.Error -> {
                    _postsData.tryEmit(PostsDataRequestResult.Error(userDataResult.message))
                }
            }
        }
        _postsData.tryEmit(PostsDataRequestResult.Success(postsListData))
    }

    private suspend fun refreshPostDataFromServer() {
        when (val postDataResult = getPostsFromRemoteUseCase.invoke()) {
            is PostsDataRequestResult.Success -> {
                onRequestPostDataSuccess(postDataResult.value)
            }
            is PostsDataRequestResult.Error -> {
                _postsData.tryEmit(PostsDataRequestResult.Error(postDataResult.message))
            }
        }
    }
}