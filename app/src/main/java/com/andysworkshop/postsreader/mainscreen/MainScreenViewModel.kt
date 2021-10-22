package com.andysworkshop.postsreader.mainscreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andysworkshop.postsreader.model.IStore
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainScreenViewModel @Inject constructor(private val store: IStore) : ViewModel() {

    val postListData = MutableLiveData<List<PostListData>>()

    init {
        setupPostsUpdater()
    }

    fun fragmentResumed() {
        setupPostsUpdater()
        requestPostData()
    }

    private fun setupPostsUpdater() {
        viewModelScope.launch(Main) {
            store.postsData.onEach { postData ->
                postListData.value = postData.map {
                    PostListData(
                        title = it.title,
                        userName = store.getUserByUserId(viewModelScope, it.userId).name
                    )
                }
            }
                .launchIn(viewModelScope)
        }
    }

    private fun requestPostData() {
        viewModelScope.launch(IO) {
            store.refreshPostsData(viewModelScope)
        }
    }
}