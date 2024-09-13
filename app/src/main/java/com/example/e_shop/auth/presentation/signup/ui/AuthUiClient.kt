package com.example.e_shop.auth.presentation.signup.ui

import android.content.Context
import com.example.e_shop.auth.domain.model.SignInResult
import com.example.e_shop.auth.domain.model.UserData
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.cancellation.CancellationException

class AuthUiClient(
    private val context: Context,
    private val oneTapClient: SignInClient
) {

    private val auth = Firebase.auth

    suspend fun signUpWithEmailAndPassword(
        email: String,
        password: String,
    ): SignInResult {
        return try {
            val authResult = auth.createUserWithEmailAndPassword(email, password).await().user
            SignInResult(
                data = authResult?.run {
                    UserData(
                        userId = uid,
                        username = displayName,
                        email = email,
                        profilePictureUrl = photoUrl?.toString()
                    )
                },
                errorMessage = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            if(e is CancellationException) throw e
            SignInResult(
                data = null,
                errorMessage = e.message
            )
        }
    }
}