package com.andysworkshop.postsreader.mainscreen.di

import com.andysworkshop.postsreader.mainscreen.MainScreenFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector ( modules = [(MainScreenModule::class)])
    abstract fun contributeFragmentAndroidInjector(): MainScreenFragment
}