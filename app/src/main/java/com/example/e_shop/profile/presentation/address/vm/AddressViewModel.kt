package com.example.e_shop.profile.presentation.address.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_shop.main.domain.model.Product
import com.example.e_shop.profile.data.repository.AddressRepository
import com.example.e_shop.profile.domain.model.Address
import com.example.e_shop.profile.domain.model.AddressResult
import com.example.e_shop.profile.domain.model.AddressState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddressViewModel @Inject constructor(private val repository: AddressRepository): ViewModel() {

    private val _state = MutableStateFlow(AddressState())
    val state = _state.asStateFlow()

    fun onAddressAdded(result: AddressResult) {
        _state.update { it.copy(
            isAddressAdded = result.data != null,
            error = result.errorMessage
        ) }
    }

    fun getAllAddresses() = repository.getAllAddresses()

    fun saveAddress(address: Address) = viewModelScope.launch { repository.insertAddress(address) }

    fun deleteAddress(address: Address) = viewModelScope.launch { repository.deleteAddress(address) }

    fun updateAddress(address: Address) = viewModelScope.launch { repository.updateAddress(address) }
}