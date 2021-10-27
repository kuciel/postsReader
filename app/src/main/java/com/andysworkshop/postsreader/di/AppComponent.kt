package com.andysworkshop.postsreader.di

import android.app.Application
import com.andysworkshop.postsreader.PostsReaderApplication
import com.andysworkshop.postsreader.database.di.DatabaseModule
import com.andysworkshop.postsreader.mainscreen.di.FragmentModule
import com.andysworkshop.postsreader.mainscreen.di.MainScreenModule
import com.andysworkshop.postsreader.model.di.StoreModule
import com.andysworkshop.postsreader.networking.di.NetworkModule
import com.andysworkshop.postsreader.mainscreen.di.UseCasesModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        FragmentModule::class,
        MainScreenModule::class,
        NetworkModule::class,
        StoreModule::class,
        ContextModule::class,
        DatabaseModule::class,
        UseCasesModule::class
    ]
)

interface AppComponent: AndroidInjector<PostsReaderApplication> {
    fun inject(application: Application)

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun applicationBind(application: Application): Builder
    }
}