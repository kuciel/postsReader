package com.andysworkshop.postsreader.mainscreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andysworkshop.postsreader.model.IStore
import com.andysworkshop.postsreader.model.PostsDataRequestResult
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class MainScreenViewModel @Inject constructor(private val store: IStore) : ViewModel() {

    val postListData = MutableLiveData<List<PostListData>>()
    val errorMessage = MutableLiveData<String>()

    fun fragmentResumed() {
        setupPostsDataObserver()
        getCachedPostsData()
        refreshPostData()
    }

    private fun setupPostsDataObserver() {
        store.postsData.onEach { postDataResult ->
            when(postDataResult) {
                is PostsDataRequestResult.Success -> {
                    postDataResult.value.map {
                        PostListData(
                            title = it.title,
                            userName = it.userName
                        )
                    }.also { parsedPostsList ->
                        postListData.value = parsedPostsList
                    }
                }
                is PostsDataRequestResult.Error ->
                    errorMessage.value = postDataResult.message
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