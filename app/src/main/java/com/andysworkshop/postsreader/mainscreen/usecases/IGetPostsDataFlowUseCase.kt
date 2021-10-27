package com.andysworkshop.postsreader.mainscreen.usecases

import com.andysworkshop.postsreader.mainscreen.PostListDataResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharedFlow

interface IGetPostsDataFlowUseCase {
    fun invoke(scope: CoroutineScope): SharedFlow<PostListDataResult>
}