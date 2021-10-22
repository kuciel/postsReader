package com.andysworkshop.postsreader.model

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharedFlow

interface IStore {
    val postsData: SharedFlow<List<PostData>>
    fun requestPostsData(scope: CoroutineScope)
    suspend fun getUserById(scope: CoroutineScope, userId: String): UsersData
    fun refreshData(scope: CoroutineScope)
}