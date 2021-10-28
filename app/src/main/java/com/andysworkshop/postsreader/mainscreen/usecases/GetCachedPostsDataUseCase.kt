package com.andysworkshop.postsreader.mainscreen.usecases

import com.andysworkshop.postsreader.domain.repository.IStore
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class GetCachedPostsDataUseCase @Inject constructor(private val store: IStore): IGetCachedPostsDataUseCase {
    override fun invoke(scope: CoroutineScope) {
        store.requestPostsData(scope)
    }
}