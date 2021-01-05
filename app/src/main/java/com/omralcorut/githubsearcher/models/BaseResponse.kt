package com.omralcorut.githubsearcher.models

data class BaseResponse<T>(
    val total_count: Int,
    val incomplete_results: Boolean,
    val items: List<T>
)