package com.andysworkshop.postsreader.database

import com.andysworkshop.postsreader.model.PostData
import com.andysworkshop.postsreader.model.PostsDataRequestResult
import com.andysworkshop.postsreader.model.UserData

interface IDatabaseInterface {
    fun getUserNameById(userId: String): UserData
    fun getAllPosts(): PostsDataRequestResult
    fun insertPosts(postsListData: List<PostData>)
    fun insertUser(userData: UserData)
}