package com.example.nonames.data.api

import com.example.nonames.data.model.GithubData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


// data/api/GithubApiService.kt
interface GithubApiService {
    @GET("users/{user}/repos")
    suspend fun getRepos(
        @Path("user") user: String,
        @Query("page") page: Int,
    ): Response<List<GithubData>>
}
