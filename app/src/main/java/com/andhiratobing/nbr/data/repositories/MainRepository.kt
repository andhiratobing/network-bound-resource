package com.andhiratobing.nbr.data.repositories

import com.andhiratobing.nbr.data.local.dao.UserDao
import com.andhiratobing.nbr.data.local.mapper.LocalMapper
import com.andhiratobing.nbr.data.model.User
import com.andhiratobing.nbr.data.remote.api.ApiService
import com.andhiratobing.nbr.data.remote.mapper.RemoteMapper
import com.andhiratobing.nbr.util.Constants.DELAY
import com.andhiratobing.nbr.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class MainRepository constructor(
    private val apiService: ApiService,
    private val userDao: UserDao,
    private val remoteMapper: RemoteMapper,
    private val localMapper: LocalMapper
) {

    suspend fun getUser(): Flow<DataState<List<User>>> = flow {
        emit(DataState.Loading)
        delay(DELAY)
        try {
            val getUserFromRemote = apiService.getRandomUser()
            val user = remoteMapper.mapFromModelList(getUserFromRemote)
            for (i in user) {
                userDao.insertUser(localMapper.mapToModel(i))
            }
            val getUserFromLocal = userDao.getUser()
            emit(DataState.Success(localMapper.mapFromModelList(getUserFromLocal)))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        } catch (e: HttpException) {
            emit((DataState.Error(e)))
        }
    }

}