package com.andysworkshop.postsreader.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.andysworkshop.postsreader.mainscreen.MainScreenViewModel
import com.andysworkshop.postsreader.model.IStore
import javax.inject.Inject

class PostReaderViewModelFactory @Inject constructor(private val store: IStore): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if(modelClass == MainScreenViewModel::class.java) {
             MainScreenViewModel(store) as T
        } else {
            modelClass.newInstance()
        }
    }
}