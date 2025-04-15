package com.example.nonames.utils


// util/Resource.kt
sealed class Resources<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : Resources<T>(data)
    class Error<T>(msg: String) : Resources<T>(null, msg)
    class Loading<T> : Resources<T>()
}

