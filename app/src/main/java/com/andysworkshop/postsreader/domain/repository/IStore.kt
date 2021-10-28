package com.andysworkshop.postsreader.domain.repository

import com.andysworkshop.postsreader.domain.data.PostsDataRequestResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharedFlow

interface IStore {
    val postsData: SharedFlow<PostsDataRequestResult>
    fun requestPostsData(scope: CoroutineScope)
    fun refreshData(scope: CoroutineScope)
}