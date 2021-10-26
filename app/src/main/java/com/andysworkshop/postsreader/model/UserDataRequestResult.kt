package com.andysworkshop.postsreader.model

sealed class UserDataRequestResult {
    data class Success(val value: UserData): UserDataRequestResult()
    data class Error(val message: String): UserDataRequestResult()
}
