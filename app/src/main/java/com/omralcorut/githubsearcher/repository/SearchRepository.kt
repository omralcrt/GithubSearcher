package com.omralcorut.githubsearcher.repository

import com.omralcorut.githubsearcher.data.remote.Api
import com.omralcorut.githubsearcher.models.BaseResponse
import com.omralcorut.githubsearcher.models.Repository
import com.omralcorut.githubsearcher.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val api: Api
) {
    suspend fun getUsers(query: String): Flow<BaseResponse<User>> =
        api.getUsers(query).flowOn(Dispatchers.Default).conflate()

    suspend fun getRepository(query: String): Flow<BaseResponse<Repository>> =
        api.getRepositories(query).flowOn(Dispatchers.Default).conflate()
}