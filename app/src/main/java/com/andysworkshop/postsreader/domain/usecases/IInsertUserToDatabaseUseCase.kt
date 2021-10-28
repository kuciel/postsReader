package com.andysworkshop.postsreader.domain.usecases

import com.andysworkshop.postsreader.domain.data.UserData

interface IInsertUserToDatabaseUseCase {
    fun invoke(userData: UserData)
}