package com.andysworkshop.postsreader.mainscreen.di

import androidx.lifecycle.ViewModelProvider
import com.andysworkshop.postsreader.di.PostReaderViewModelFactory
import com.andysworkshop.postsreader.model.IStore
import dagger.Module
import dagger.Provides

@Module
class MainScreenModule {
    @Provides
    fun provideMainScreenViewModelFactory (store: IStore): ViewModelProvider.Factory {
        return PostReaderViewModelFactory(store)
    }
}