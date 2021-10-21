package com.andysworkshop.postsreader.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.andysworkshop.postsreader.database.dao.PostDao
import com.andysworkshop.postsreader.database.entities.Post

@Database(entities = [Post::class], version = 1)
abstract class PostsReaderDatabase: RoomDatabase() {
    abstract fun postDao(): PostDao
}