package com.andysworkshop.postsreader.domain.usecases

import com.andysworkshop.postsreader.domain.data.UserDataRequestResult

interface IGetUserFromRemoteUseCase {
    suspend fun invoke(id: String): UserDataRequestResult
}