package com.andysworkshop.postsreader.mainscreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andysworkshop.postsreader.mainscreen.usecases.IGetCachedPostsDataUseCase
import com.andysworkshop.postsreader.mainscreen.usecases.IGetPostsDataFlowUseCase
import com.andysworkshop.postsreader.mainscreen.usecases.IRefreshPostsDataUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainScreenViewModel @Inject constructor(
    private val getCachedPostsDataUseCase: IGetCachedPostsDataUseCase,
    private val refreshPostsDataUseCase: IRefreshPostsDataUseCase,
    private val getPostsDataFlowUseCase: IGetPostsDataFlowUseCase
) : ViewModel() {

    val postListData = MutableLiveData<List<PostListData>>()
    val errorMessage = MutableLiveData<String>()

    fun fragmentResumed() {
        setupPostsDataObserver()
        getCachedPostsData()
        refreshPostData()
    }

    private fun setupPostsDataObserver() {
        getPostsDataFlowUseCase.invoke(viewModelScope).onEach { postDataListResult ->
            when (postDataListResult) {
                is PostListDataResult.Success -> {
                    postListData.value = postDataListResult.value
                }
                is PostListDataResult.Error ->
                    errorMessage.value = postDataListResult.message
            }
        }.launchIn(viewModelScope)
    }

    private fun getCachedPostsData() {
        viewModelScope.launch(Dispatchers.IO) {
            getCachedPostsDataUseCase.invoke()
        }
    }

    private fun refreshPostData() {
        viewModelScope.launch(Dispatchers.IO) {
            refreshPostsDataUseCase.invoke()
        }
    }
}