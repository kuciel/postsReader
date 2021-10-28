package com.andysworkshop.postsreader.networking.usecases

import com.andysworkshop.postsreader.domain.data.UserDataRequestResult
import com.andysworkshop.postsreader.domain.usecases.IGetUserFromRemoteUseCase
import com.andysworkshop.postsreader.networking.INetworkInterface
import javax.inject.Inject

class GetUserFromRemoteUseCase @Inject constructor(private val networkInterface: INetworkInterface):
    IGetUserFromRemoteUseCase {
    override suspend fun invoke(id: String): UserDataRequestResult {
        return networkInterface.requestUserData(id)
    }
}