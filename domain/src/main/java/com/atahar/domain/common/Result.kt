package com.atahar.domain.common

sealed class Result<T>(
    val data: T? = null,
    val message: String? = null
) {

    class Success<T>(data: T) : Result<T>(data)
    class Error<T>(message: String?, data: T? = null) : Result<T>(data, message)
    class Loading<T> : Result<T>()
    class Empty<T> : Result<T>()


    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$message]"
            is Loading -> "Loading"
            is Empty -> "Empty"
        }
    }
}