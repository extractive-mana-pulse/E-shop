package com.example.e_shop.auth.domain.model

data class SignUpUser(
    val name: String,
    val email: String,
    val password: String,
    val avatar: String
)
