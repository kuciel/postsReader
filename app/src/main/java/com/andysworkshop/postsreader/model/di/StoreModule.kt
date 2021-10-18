package com.andysworkshop.postsreader.model.di

import com.andysworkshop.postsreader.model.IStore
import com.andysworkshop.postsreader.model.Store
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class StoreModule {
    @Singleton
    @Binds
    abstract fun bindStoreInterface(storeModule: Store) : IStore
}