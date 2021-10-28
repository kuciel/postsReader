package com.andysworkshop.postsreader.mainscreen.usecases

import com.andysworkshop.postsreader.mainscreen.PostListData
import com.andysworkshop.postsreader.mainscreen.PostListDataResult
import com.andysworkshop.postsreader.domain.repository.IStore
import com.andysworkshop.postsreader.domain.data.PostsDataRequestResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class GetPostsDataFlowUseCase @Inject constructor(
    private val store: IStore
) : IGetPostsDataFlowUseCase {

    private val _postsListDataFlow = MutableSharedFlow<PostListDataResult>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    override fun invoke(scope: CoroutineScope): SharedFlow<PostListDataResult> {
        setupStorePostsDataObserver(scope)
        return _postsListDataFlow
    }

    private fun setupStorePostsDataObserver(scope: CoroutineScope) {
        store.postsData.onEach { postDataResult ->
            when (postDataResult) {
                is PostsDataRequestResult.Success -> {
                    postDataResult.value.map {
                        PostListData(
                            title = it.title,
                            userName = it.userName
                        )
                    }.also {
                        _postsListDataFlow.tryEmit(PostListDataResult.Success(it))
                    }
                }
                is PostsDataRequestResult.Error ->
                    _postsListDataFlow.tryEmit(PostListDataResult.Error(postDataResult.message))
            }
        }.launchIn(scope)
    }
}