package com.andysworkshop.postsreader.mainscreen.usecases

import com.andysworkshop.postsreader.domain.repository.IStore
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class RefreshPostsDataUseCase @Inject constructor(private val store: IStore): IRefreshPostsDataUseCase {
    override fun invoke(scope: CoroutineScope) {
        store.refreshData(scope)
    }
}