package com.andysworkshop.postsreader.domain.usecases

import com.andysworkshop.postsreader.domain.data.PostData

interface IInsertPostsToDBUseCase {
    fun invoke(postsListData: List<PostData>)
}