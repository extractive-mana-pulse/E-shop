package com.example.e_shop.main.presentation.category.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_shop.main.data.remote.repository.CategoryRepository
import com.example.e_shop.main.domain.model.Category
import com.example.e_shop.main.domain.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val repository: CategoryRepository): ViewModel() {

    var category: MutableLiveData<Response<List<Category>>> = MutableLiveData()

    fun getAllCategories() {

        viewModelScope.launch {
            val response = repository.getAllCategories()
            category.value = response
        }
    }

    var allProductsByCategory: MutableLiveData<Response<List<Product>>> = MutableLiveData()

    fun getAllProductsByCategory(categoryId : Int) {

        viewModelScope.launch {
            val response = repository.getAllProductsByCategory(categoryId)
            allProductsByCategory.value = response
        }
    }

}