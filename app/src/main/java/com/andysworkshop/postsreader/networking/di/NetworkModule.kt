package com.andysworkshop.postsreader.networking.di

import com.andysworkshop.postsreader.networking.IRetrofitApiInterface
import com.andysworkshop.postsreader.networking.INetworkInterface
import com.andysworkshop.postsreader.networking.Network
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideNetworkInterface(apiInterface: IRetrofitApiInterface): INetworkInterface {
        return Network(apiInterface)
    }

    @Provides
    fun provideRetrofitApiInterface(): IRetrofitApiInterface {
        return IRetrofitApiInterface.create()
    }
}