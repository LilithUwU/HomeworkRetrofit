package com.example.homeworkretrofit.model

sealed class Result<out R> {
    data class Success<out R>(val value: R) : Result<R>()
    data class Failure(
        val message: String?,
        val throwable: Throwable?
    ) : Result<Nothing>()

    class Loading<T> : Result<T>()
}