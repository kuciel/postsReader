package com.andysworkshop.postsreader.mainscreen.usecases

import kotlinx.coroutines.CoroutineScope

interface IGetCachedPostsDataUseCase {
    fun invoke(scope: CoroutineScope)
}