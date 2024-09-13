package com.example.e_shop.profile.data.repository

import androidx.lifecycle.LiveData
import com.example.e_shop.profile.domain.model.Address

interface AddressRepository {

    suspend fun insertAddress(address: Address)

    suspend fun deleteAddress(address: Address)

    suspend fun updateAddress(address: Address)

    fun getAllAddresses(): LiveData<List<Address>>
}