package com.example.e_shop.main.presentation.home.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_shop.main.data.remote.repository.ProductRepository
import com.example.e_shop.main.domain.model.Category
import com.example.e_shop.main.domain.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: ProductRepository): ViewModel() {

    var getAllProductsResult: MutableLiveData<Response<List<Product>>> = MutableLiveData()

    fun getAllProducts() {

        viewModelScope.launch {
            val response = repository.getAllProducts()
            getAllProductsResult.value = response
        }
    }
}