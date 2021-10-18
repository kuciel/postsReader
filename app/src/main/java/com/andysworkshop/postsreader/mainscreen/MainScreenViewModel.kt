package com.andysworkshop.postsreader.mainscreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andysworkshop.postsreader.model.IStore
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainScreenViewModel @Inject constructor(private val store: IStore) : ViewModel() {
    val postListData = MutableLiveData<List<PostListData>>()

    fun fragmentResumed() {
        requestPostData()
    }

    private fun requestPostData() {
        viewModelScope.launch(Main) {
            store.refreshPostsData(viewModelScope).apply {
                postListData.value = map {
                    PostListData(
                        title = it.title,
                        userName = store.getUserByUserId(viewModelScope, it.userId).name
                    )
                }
            }
        }
    }
}