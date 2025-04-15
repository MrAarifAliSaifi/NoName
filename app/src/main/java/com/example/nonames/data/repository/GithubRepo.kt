package com.example.nonames.data.repository

import com.example.nonames.data.api.GithubApiService
import com.example.nonames.data.model.GithubData
import com.example.nonames.utils.Resources
import javax.inject.Inject

// data/repository/GithubRepository.kt
class GithubRepo @Inject constructor(
    private val apiService: GithubApiService
) {
    suspend fun getRepos(user: String, page: Int): Resources<List<GithubData>> {
        return try {
            val response = apiService.getRepos(user, page)
            if (response.isSuccessful) {
                Resources.Success(response.body() ?: emptyList())
            } else {
                Resources.Error("Error: ${response.code()}")
            }
        } catch (e: Exception) {
            Resources.Error("Exception: ${e.localizedMessage}")
        }
    }
}
