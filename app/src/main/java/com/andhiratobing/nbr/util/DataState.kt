package com.andhiratobing.nbr.util

import retrofit2.Response

sealed class DataState<T>(
    val data: T? = null,
    val errorBody: Response<Any>? = null,
    val isNetworkError: Boolean? = null
) {
    class Success<T>(data: T) : DataState<T>(data)
    class Loading<T>(data: T? = null) : DataState<T>(data)
    class Error<T>(isNetworkError: Boolean, errorBody: Response<Any>?, data: T? = null) :
        DataState<T>(data, errorBody, isNetworkError)

}