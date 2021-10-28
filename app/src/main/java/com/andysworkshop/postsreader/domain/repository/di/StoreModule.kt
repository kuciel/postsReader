package com.andysworkshop.postsreader.domain.repository.di

import com.andysworkshop.postsreader.domain.repository.IStore
import com.andysworkshop.postsreader.domain.repository.Store
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class StoreModule {
    @Singleton
    @Binds
    abstract fun bindStoreInterface(storeModule: Store) : IStore
}