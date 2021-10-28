package com.andysworkshop.postsreader.networking.di

import com.andysworkshop.postsreader.domain.usecases.IGetPostsFromRemoteUseCase
import com.andysworkshop.postsreader.domain.usecases.IGetUserFromRemoteUseCase
import com.andysworkshop.postsreader.networking.usecases.GetPostsFromRemoteUseCase
import com.andysworkshop.postsreader.networking.usecases.GetUserFromRemoteUseCase
import dagger.Binds
import dagger.Module

@Module
abstract class NetworkUseCasesModule {
    @Binds
    abstract fun bindGetPostsFromRemoteUseCase(getPostsFromRemoteUseCase: GetPostsFromRemoteUseCase): IGetPostsFromRemoteUseCase

    @Binds
    abstract fun bindGetUserFromRemoteUseCase(getUserFromRemoteUseCase: GetUserFromRemoteUseCase): IGetUserFromRemoteUseCase
}