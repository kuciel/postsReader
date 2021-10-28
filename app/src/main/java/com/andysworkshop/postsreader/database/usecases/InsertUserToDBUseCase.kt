package com.andysworkshop.postsreader.database.usecases

import com.andysworkshop.postsreader.database.IDatabaseInterface
import com.andysworkshop.postsreader.domain.data.UserData
import com.andysworkshop.postsreader.domain.usecases.IInsertUserToDatabaseUseCase
import javax.inject.Inject

class InsertUserToDBUseCase @Inject constructor(private val databaseInterface: IDatabaseInterface): IInsertUserToDatabaseUseCase{
    override fun invoke(userData: UserData) {
        databaseInterface.insertUser(userData)
    }
}