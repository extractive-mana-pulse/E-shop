package com.example.e_shop.auth.presentation.sealed

import com.example.e_shop.auth.domain.model.SignUpUser


sealed class SignUp {

    data class Success(val response: SignUpUser) : SignUp()
    data class Error(val message: String) : SignUp()
    data object Nothing: SignUp()

}