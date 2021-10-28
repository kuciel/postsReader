package com.andysworkshop.postsreader.database.usecases

import com.andysworkshop.postsreader.database.IDatabaseInterface
import com.andysworkshop.postsreader.domain.data.PostsDataRequestResult
import com.andysworkshop.postsreader.domain.usecases.IGetPostsFromDBUseCase
import javax.inject.Inject

class GetPostsFromDBUseCase @Inject constructor(private val databaseInterface: IDatabaseInterface) :
    IGetPostsFromDBUseCase {
    override fun invoke(): PostsDataRequestResult {
        return databaseInterface.getAllPosts()
    }
}