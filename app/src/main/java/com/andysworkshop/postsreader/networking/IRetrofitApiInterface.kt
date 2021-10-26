package com.andysworkshop.postsreader.networking

import com.andysworkshop.postsreader.model.UserData
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface IRetrofitApiInterface {

    @GET("posts")
    suspend fun getPosts(): List<PostsResponseDto>

    @GET("users/{id}")
    suspend fun getUsersById(@Path(value="id") id: String): UserData

    companion object {
        private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

        fun create(): IRetrofitApiInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(IRetrofitApiInterface::class.java)
        }
    }
}