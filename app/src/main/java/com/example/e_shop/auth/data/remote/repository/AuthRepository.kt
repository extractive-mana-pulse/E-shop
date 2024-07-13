package com.example.e_shop.auth.data.remote.repository

import com.example.e_shop.auth.domain.model.SignUpUser
import retrofit2.Call

interface AuthRepository {

    suspend fun signUp(user : SignUpUser) : Call<SignUpUser>
}