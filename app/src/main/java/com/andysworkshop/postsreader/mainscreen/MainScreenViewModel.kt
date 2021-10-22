package com.andysworkshop.postsreader.mainscreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andysworkshop.postsreader.model.IStore
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
                    userName = store.getUserById(viewModelScope, it.userId).name
                )
            }.also { parsedPostsList ->
                withContext(Main) {
                    postListData.value = parsedPostsList
                }
            }
        }.flowOn(IO)
            .launchIn(viewModelScope)
    }

    private fun getCachedPostsData() {
        viewModelScope.launch(IO) {
            store.requestPostsData(viewModelScope)
        }
    }

    private fun refreshPostData() {
        viewModelScope.launch(IO) {
            store.refreshData(viewModelScope)
        }
    }
}