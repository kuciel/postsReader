package com.andysworkshop.postsreader.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.andysworkshop.postsreader.database.entities.Post

@Dao
interface PostDao {
    @Query("SELECT * FROM post")
    fun getAll(): List<Post>

    @Insert
    fun insertAll(posts: List<Post>)

    @Query("DELETE FROM post")
    fun deleteAll()
}