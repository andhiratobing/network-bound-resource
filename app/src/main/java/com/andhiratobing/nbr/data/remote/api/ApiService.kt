package com.andhiratobing.nbr.data.remote.api

import com.andhiratobing.nbr.data.remote.response.UserResponse
import retrofit2.http.GET

interface ApiService {

    @GET("users/random_user?size=20")
    suspend fun getRandomUser(): List<UserResponse>
}