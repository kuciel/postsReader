package com.andysworkshop.postsreader.database.di

import com.andysworkshop.postsreader.database.usecases.GetPostsFromDBUseCase
import com.andysworkshop.postsreader.database.usecases.InsertPostsToDBUseCase
import com.andysworkshop.postsreader.database.usecases.InsertUserToDBUseCase
import com.andysworkshop.postsreader.domain.usecases.IGetPostsFromDBUseCase
import com.andysworkshop.postsreader.domain.usecases.IInsertPostsToDBUseCase
import com.andysworkshop.postsreader.domain.usecases.IInsertUserToDatabaseUseCase
import dagger.Binds
import dagger.Module

@Module
abstract class DatabaseUseCasesModule {
    @Binds
    abstract fun bindGetPostsFromDatabaseUseCase(getPostsFromDBUseCase: GetPostsFromDBUseCase): IGetPostsFromDBUseCase

    @Binds
    abstract fun bindInsertUserFromDatabaseUseCase(insertUserToDBUseCase: InsertUserToDBUseCase): IInsertUserToDatabaseUseCase

    @Binds
    abstract fun bindInsertPostsToDatabaseUseCase(insertPostsToDBUseCase: InsertPostsToDBUseCase): IInsertPostsToDBUseCase

}