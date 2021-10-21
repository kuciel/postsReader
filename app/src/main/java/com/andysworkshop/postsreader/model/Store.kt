package com.andysworkshop.postsreader.model

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.andysworkshop.postsreader.database.PostsReaderDatabase
import com.andysworkshop.postsreader.networking.INetworkInterface
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class Store @Inject constructor(
    private val networkInterface: INetworkInterface,
    private val appContext: Context
) : IStore {

    val database = Room.databaseBuilder(appContext,
        PostsReaderDatabase::class.java, "posts_reader").build()

    override suspend fun refreshPostsData(scope: CoroutineScope): List<PostData> {
        return networkInterface.requestPostsData()
    }

    override suspend fun getUserByUserId(scope: CoroutineScope, userId: String): UsersData {
        return networkInterface.requestUserData(userId)
    }
}