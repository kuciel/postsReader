package com.andysworkshop.postsreader.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.andysworkshop.postsreader.mainscreen.MainScreenViewModel
import com.andysworkshop.postsreader.mainscreen.usecases.IGetCachedPostsDataUseCase
import com.andysworkshop.postsreader.mainscreen.usecases.IGetPostsDataFlowUseCase
import com.andysworkshop.postsreader.mainscreen.usecases.IRefreshPostsDataUseCase
import javax.inject.Inject

class PostReaderViewModelFactory @Inject constructor(
    private val getCachedPostsDataUseCase: IGetCachedPostsDataUseCase,
    private val refreshPostsDataUseCase: IRefreshPostsDataUseCase,
    private val getPostsDataFlowUseCase: IGetPostsDataFlowUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass == MainScreenViewModel::class.java) {
            MainScreenViewModel(getCachedPostsDataUseCase, refreshPostsDataUseCase, getPostsDataFlowUseCase) as T
        } else {
            modelClass.newInstance()
        }
    }
}