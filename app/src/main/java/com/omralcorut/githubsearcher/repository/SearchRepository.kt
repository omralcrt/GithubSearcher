package com.omralcorut.githubsearcher.repository

import com.omralcorut.githubsearcher.data.remote.Api
import com.omralcorut.githubsearcher.models.BaseResponse
import com.omralcorut.githubsearcher.models.Repository
import com.omralcorut.githubsearcher.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val api: Api
) {
    fun getUsers(query: String): Flow<BaseResponse<User>> = flow {
        val users = api.getUsers(query)
        emit(users)
    }.flowOn(Dispatchers.IO)

    fun getRepositories(query: String): Flow<BaseResponse<Repository>> = flow {
        val users = api.getRepositories(query)
        emit(users)
    }.flowOn(Dispatchers.IO)
}