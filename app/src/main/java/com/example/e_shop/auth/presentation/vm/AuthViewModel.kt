package com.example.e_shop.auth.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_shop.auth.data.remote.repository.AuthRepository
import com.example.e_shop.auth.domain.model.SignUpUser
import com.example.e_shop.auth.presentation.sealed.SignUp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val repository : AuthRepository): ViewModel() {

    private val _signUp = MutableStateFlow<SignUp>(SignUp.Nothing)
    val signUpResult: StateFlow<SignUp> = _signUp.asStateFlow()

    fun signUpProf(name: String, email: String, password: String,  avatar: String) {

        val request = SignUpUser(name, email, password, avatar)

        viewModelScope.launch {
            repository.signUp(request).enqueue(object : Callback<SignUpUser> {
                override fun onResponse(call: Call<SignUpUser>, response: Response<SignUpUser>) {

                    if (response.isSuccessful) {
                        _signUp.value = SignUp.Success(response.body()!!)
                    } else {
                        _signUp.value = SignUp.Error(response.errorBody().toString())
                    }
                }

                override fun onFailure(call: Call<SignUpUser>, t: Throwable) {
                    _signUp.value = SignUp.Error(t.message.toString())
                }
            })
        }
    }
}