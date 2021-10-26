package com.andysworkshop.postsreader.model

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharedFlow

interface IStore {
    val postsData: SharedFlow<PostsDataRequestResult>
    fun requestPostsData(scope: CoroutineScope)
    fun refreshData(scope: CoroutineScope)
}