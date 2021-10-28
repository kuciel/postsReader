package com.andysworkshop.postsreader.domain.data

sealed class UserDataRequestResult {
    data class Success(val value: UserData): UserDataRequestResult()
    data class Error(val message: String): UserDataRequestResult()
}
