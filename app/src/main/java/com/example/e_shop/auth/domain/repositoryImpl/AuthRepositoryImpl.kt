package com.example.e_shop.auth.domain.repositoryImpl

import com.example.e_shop.auth.data.remote.api.AuthApi
import com.example.e_shop.auth.data.remote.repository.AuthRepository
import com.example.e_shop.auth.domain.model.SignUpUser
import retrofit2.Call

class AuthRepositoryImpl(private val authApi: AuthApi): AuthRepository {

    override suspend fun signUp(user: SignUpUser): Call<SignUpUser> {
        return authApi.signUp(user)
    }

}