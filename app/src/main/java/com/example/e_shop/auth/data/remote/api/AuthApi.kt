package com.example.e_shop.auth.data.remote.api

import com.example.e_shop.auth.domain.model.SignUpUser
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("users")
    suspend fun signUp(@Body user : SignUpUser) : Call<SignUpUser>

}