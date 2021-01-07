package com.omralcorut.githubsearcher.data.remote

import com.omralcorut.githubsearcher.models.BaseResponse
import com.omralcorut.githubsearcher.models.Repository
import com.omralcorut.githubsearcher.models.User
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("/search/users")
    suspend fun getUsers(
        @Query("q") query: String,
    ): BaseResponse<User>

    @GET("/search/repositories")
    suspend fun getRepositories(
        @Query("q") query: String,
    ): BaseResponse<Repository>
}