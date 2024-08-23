package com.example.e_shop.main.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity( tableName = "product" )
data class Product (

  @PrimaryKey(autoGenerate = true) var dbId : Int? = null,
  @SerializedName("id"          ) var id          : Int?              = null,
  @SerializedName("title"       ) var title       : String?           = null,
  @SerializedName("price"       ) var price       : Int?              = null,
  @SerializedName("description" ) var description : String?           = null,
  @SerializedName("images"      ) var images      : ArrayList<String> = arrayListOf(),
  @SerializedName("creationAt"  ) var creationAt  : String?           = null,
  @SerializedName("updatedAt"   ) var updatedAt   : String?           = null,
  @SerializedName("category"    ) var category    : Category?         = Category()

): Parcelable