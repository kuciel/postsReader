package com.andysworkshop.postsreader.model

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharedFlow

interface IStore {
    val postsData: SharedFlow<List<PostData>>
    fun requestPostsData(scope: CoroutineScope)
    fun refreshData(scope: CoroutineScope)
}