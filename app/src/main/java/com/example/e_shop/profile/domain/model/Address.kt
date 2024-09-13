package com.example.e_shop.profile.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity( tableName = "address" )
data class Address(
    @PrimaryKey(autoGenerate = true) var id : Int? = null,
    val city: String,
    val streetAddress: String,
    val state: String,
    val zipCode: String?
):Parcelable