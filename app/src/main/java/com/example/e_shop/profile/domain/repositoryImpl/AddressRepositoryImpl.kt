package com.example.e_shop.profile.domain.repositoryImpl

import androidx.lifecycle.LiveData
import com.example.e_shop.main.data.local.database.ArticleDatabase
import com.example.e_shop.profile.data.repository.AddressRepository
import com.example.e_shop.profile.domain.model.Address

class AddressRepositoryImpl(private val db : ArticleDatabase): AddressRepository {

    override suspend fun insertAddress(address: Address) {
        return db.getAddressDao().insertAddress(address)
    }

    override suspend fun deleteAddress(address: Address) {
        return db.getAddressDao().deleteAddress(address)
    }

    override suspend fun updateAddress(address: Address) {
        return db.getAddressDao().updateAddress(address)
    }

    override fun getAllAddresses(): LiveData<List<Address>> {
        return db.getAddressDao().getAllAddresses()
    }

}