package com.andysworkshop.postsreader.mainscreen.di

import androidx.lifecycle.ViewModelProvider
import com.andysworkshop.postsreader.di.PostReaderViewModelFactory
import com.andysworkshop.postsreader.mainscreen.usecases.IGetCachedPostsDataUseCase
import com.andysworkshop.postsreader.mainscreen.usecases.IGetPostsDataFlowUseCase
import com.andysworkshop.postsreader.mainscreen.usecases.IRefreshPostsDataUseCase
import com.andysworkshop.postsreader.model.IStore
import dagger.Module
import dagger.Provides

@Module
class MainScreenModule {
    @Provides
    fun provideMainScreenViewModelFactory(
        getCachedPostsDataUseCase: IGetCachedPostsDataUseCase,
        refreshPostsDataUseCase: IRefreshPostsDataUseCase,
        getPostsDataFlowUseCase: IGetPostsDataFlowUseCase
    ): ViewModelProvider.Factory {
        return PostReaderViewModelFactory(
            getCachedPostsDataUseCase,
            refreshPostsDataUseCase,
            getPostsDataFlowUseCase
        )
    }
}