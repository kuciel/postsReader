package com.andysworkshop.postsreader.mainscreen.usecases

import kotlinx.coroutines.CoroutineScope

interface IRefreshPostsDataUseCase {
    fun invoke(scope: CoroutineScope)
}