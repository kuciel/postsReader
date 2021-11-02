package com.andysworkshop.postsreader.mainscreen.usecases

import com.andysworkshop.postsreader.domain.repository.IStore
import javax.inject.Inject

class RefreshPostsDataUseCase @Inject constructor(private val store: IStore): IRefreshPostsDataUseCase {
    override suspend fun invoke() {
        store.refreshData()
    }
}