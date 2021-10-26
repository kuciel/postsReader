package com.andysworkshop.postsreader.mainscreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andysworkshop.postsreader.model.IStore
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class MainScreenViewModel @Inject constructor(private val store: IStore) : ViewModel() {

    val postListData = MutableLiveData<List<PostListData>>()

    fun fragmentResumed() {
        setupPostsDataObserver()
        getCachedPostsData()
        refreshPostData()
    }

    private fun setupPostsDataObserver() {
        store.postsData.onEach { postData ->
            postData.map {
                PostListData(
                    title = it.title,
                    userName = it.userName
                )
            }.also { parsedPostsList ->
                postListData.value = parsedPostsList
            }
        }.launchIn(viewModelScope)
    }

    private fun getCachedPostsData() {
        store.requestPostsData(viewModelScope)
    }

    private fun refreshPostData() {
        store.refreshData(viewModelScope)
    }
}