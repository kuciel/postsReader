package com.andysworkshop.postsreader.database

import com.andysworkshop.postsreader.domain.data.PostData
import com.andysworkshop.postsreader.domain.data.PostsDataRequestResult
import com.andysworkshop.postsreader.domain.data.UserData

interface IDatabaseInterface {
    fun getUserNameById(userId: String): UserData
    fun getAllPosts(): PostsDataRequestResult
    fun insertPosts(postsListData: List<PostData>)
    fun insertUser(userData: UserData)
}