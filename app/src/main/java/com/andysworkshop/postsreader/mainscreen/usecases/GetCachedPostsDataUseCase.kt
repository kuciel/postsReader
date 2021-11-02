package com.andysworkshop.postsreader.mainscreen.usecases

import com.andysworkshop.postsreader.domain.repository.IStore
import javax.inject.Inject

class GetCachedPostsDataUseCase @Inject constructor(private val store: IStore): IGetCachedPostsDataUseCase {
    override suspend fun invoke() {
        store.requestPostsData()
    }
}