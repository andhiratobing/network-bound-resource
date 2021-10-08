package com.andhiratobing.nbr.data.local.util

import com.andhiratobing.nbr.util.DataState
import kotlinx.coroutines.flow.*
import retrofit2.HttpException
import retrofit2.Response

inline fun <ResultType, RequestType> networkBoundResources(
    crossinline queryFromLocal: () -> Flow<ResultType>,
    crossinline fetchFromRemote: suspend () -> RequestType,
    crossinline saveToLocal: suspend (RequestType) -> Unit,
    crossinline shouldFetchFromRemote: (ResultType) -> Boolean = { true }
) = flow {

    val dataLocal = queryFromLocal().first()

    val flow = if (shouldFetchFromRemote(dataLocal)) {
        emit(DataState.Loading(dataLocal))
        try {
            saveToLocal(fetchFromRemote())
            queryFromLocal().map {
                DataState.Success(it)
            }
        } catch (t: Throwable) {
            when(t){
                is HttpException -> {
                    queryFromLocal().map { resultType ->
                        DataState.Error(false, t.response() as Response<Any>, resultType) }
                }else -> {
                    queryFromLocal().map { resultType ->
                        DataState.Error(true, null, resultType) }
                }
            }
        }
    } else {
        queryFromLocal().map { DataState.Success(it) }
    }

    emitAll(flow)
}