package com.andysworkshop.postsreader.domain.repository

import com.andysworkshop.postsreader.database.usecases.GetPostsFromDBUseCase
import com.andysworkshop.postsreader.database.usecases.InsertPostsToDBUseCase
import com.andysworkshop.postsreader.database.usecases.InsertUserToDBUseCase
import com.andysworkshop.postsreader.domain.data.PostData
import com.andysworkshop.postsreader.domain.data.PostsDataRequestResult
import com.andysworkshop.postsreader.domain.data.UserDataRequestResult
import com.andysworkshop.postsreader.domain.usecases.IGetPostsFromRemoteUseCase
import com.andysworkshop.postsreader.domain.usecases.IGetUserFromRemoteUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class Store @Inject constructor(
    private val getPostsFromRemoteUseCase: IGetPostsFromRemoteUseCase,
    private val getUserFromRemoteUseCase: IGetUserFromRemoteUseCase,
    private val getPostsFromDBUseCase: GetPostsFromDBUseCase,
    private val insertUserToDBUseCase: InsertUserToDBUseCase,
    private val insertPostsToDBUseCase: InsertPostsToDBUseCase
) : IStore {

    private val _postsData = MutableSharedFlow<PostsDataRequestResult>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    override val postsData: SharedFlow<PostsDataRequestResult>
        get() {
            return _postsData
        }

    override suspend fun refreshData() {
        withContext(Dispatchers.IO) {
            refreshPostDataFromServer()
        }
    }

    override suspend fun requestPostsData() {
        withContext(Dispatchers.IO) {
            getPostsDataFromDB()
        }
    }

    private fun getPostsDataFromDB() {
            _postsData.tryEmit(
                getPostsFromDBUseCase.invoke()
            )
    }

    private suspend fun onRequestPostDataSuccess(postsListData: List<PostData>) {
        insertPostsToDBUseCase.invoke(postsListData)
        postsListData.forEach { post ->
            when (val userDataResult = getUserFromRemoteUseCase.invoke(post.userId)) {
                is UserDataRequestResult.Success -> {
                    post.userName = userDataResult.value.name
                    insertUserToDBUseCase.invoke(userDataResult.value)
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