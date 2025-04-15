package com.example.nonames.data.model


// data/model/Repository.kt
data class GithubData(
    val name: String,
    val description: String?,
    val language: String?,
    val stargazers_count: Int,
    val forks_count: Int
)

