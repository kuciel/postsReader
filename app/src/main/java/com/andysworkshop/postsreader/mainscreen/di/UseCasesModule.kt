package com.andysworkshop.postsreader.mainscreen.di

import com.andysworkshop.postsreader.mainscreen.usecases.*
import dagger.Binds
import dagger.Module

@Module
abstract class UseCasesModule {
    @Binds
    abstract fun bindRefreshPostsDataUseCse(getCachedPostsDataUseCase: GetCachedPostsDataUseCase): IGetCachedPostsDataUseCase

    @Binds
    abstract fun bindGetCachedPostsDataUseCase(refreshPostsDataUseCase: RefreshPostsDataUseCase): IRefreshPostsDataUseCase

    @Binds
    abstract fun bindGetPostsDataFlowUseCase(getPostsDataFlowUseCase: GetPostsDataFlowUseCase): IGetPostsDataFlowUseCase
}