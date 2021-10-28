package com.andysworkshop.postsreader.database.usecases

import com.andysworkshop.postsreader.database.IDatabaseInterface
import com.andysworkshop.postsreader.domain.data.PostData
import com.andysworkshop.postsreader.domain.usecases.IInsertPostsToDBUseCase
import javax.inject.Inject

class InsertPostsToDBUseCase @Inject constructor(private val databaseInterface: IDatabaseInterface): IInsertPostsToDBUseCase {
    override fun invoke(postsListData: List<PostData>) {
        databaseInterface.insertPosts(postsListData)
    }
}