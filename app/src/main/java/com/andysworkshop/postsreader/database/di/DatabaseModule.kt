package com.andysworkshop.postsreader.database.di

import android.content.Context
import androidx.room.Room
import com.andysworkshop.postsreader.database.PostsReaderDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(appContext: Context): PostsReaderDatabase {
        return Room.databaseBuilder(appContext,
            PostsReaderDatabase::class.java, "posts_reader").build()
    }
}