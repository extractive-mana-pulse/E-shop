package com.example.e_shop.main.presentation.favorite.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_shop.main.data.local.repository.ProductRepositoryDB
import com.example.e_shop.main.domain.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DatabaseViewModel @Inject constructor(private val repository: ProductRepositoryDB): ViewModel() {

    fun getSavedProducts() = repository.getAllProducts()

    fun saveProduct(product: Product) = viewModelScope.launch { repository.insert(product) }

    fun deleteArticle(product: Product) = viewModelScope.launch { repository.deleteProduct(product) }

    private val _itemExistenceState = MutableLiveData<ItemExistenceState>()
    val itemExistenceState: LiveData<ItemExistenceState> = _itemExistenceState

    fun checkIfItemExists(itemId: Int) {
        viewModelScope.launch {
            val itemExists = repository.isItemInDatabase(itemId)
            _itemExistenceState.postValue(if (itemExists) ItemExistenceState.Exists else ItemExistenceState.NotExists)
        }
    }

    suspend fun checkIfItemsExist(productIds: List<Int>): LiveData<List<Boolean>> {
        val existenceStates = MutableLiveData<List<Boolean>>()
        val dbProducts = repository.getProductsByIds(productIds)
        val existenceList = dbProducts.map { true }
        existenceStates.value = existenceList
        return existenceStates
    }
}

sealed class ItemExistenceState {
    data object Exists : ItemExistenceState()
    data object NotExists : ItemExistenceState()
}