package com.andysworkshop.postsreader.database

import com.andysworkshop.postsreader.database.entities.Post
import com.andysworkshop.postsreader.database.entities.User
import com.andysworkshop.postsreader.domain.data.PostData
import com.andysworkshop.postsreader.domain.data.PostsDataRequestResult
import com.andysworkshop.postsreader.domain.data.UserData
import javax.inject.Inject

class DatabaseImpl @Inject constructor(
    private val postsReaderDb: PostsReaderDatabase
) : IDatabaseInterface {

    override fun getUserNameById(userId: String): UserData {
        return try {
            val user = postsReaderDb.userDao().getUserNameById(userId)
            UserData(
                name = user.name,
                id = user.id
            )
        } catch (error: Throwable) {
            UserData(
                name = "Unknown",
                id = userId
            )
        }
    }

    override fun getAllPosts(): PostsDataRequestResult {
        return try {
            val postsFromDB = postsReaderDb.postDao().getAll()

                PostsDataRequestResult.Success(
                    postsFromDB.map {
                        PostData(
                            id = it.id,
                            title = it.title,
                            body = it.body,
                            userId = it.userId,
                            userName = getUserNameById(it.userId).name
                        )
                    })

        } catch (error: Throwable) {
                PostsDataRequestResult.Error(error.message?: "Unknown error")
        }
    }

    override fun insertPosts(postsListData: List<PostData>) {
        postsReaderDb.postDao().deleteAll()
        val postEntities: List<Post> = postsListData.map {
            Post(
                id = it.id,
                title = it.title,
                body = it.body,
                userId = it.userId
            )
        }
        postsReaderDb.postDao().insertAll(postEntities)

    }

    override fun insertUser(userData: UserData) {
        postsReaderDb.userDao().insert(
            User(name = userData.name, id = userData.id)
        )
    }
}