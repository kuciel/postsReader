package com.andysworkshop.postsreader.domain.repository

import com.andysworkshop.postsreader.domain.data.PostsDataRequestResult
import kotlinx.coroutines.flow.SharedFlow

interface IStore {
    val postsData: SharedFlow<PostsDataRequestResult>
    suspend fun requestPostsData()
    suspend fun refreshData()
}