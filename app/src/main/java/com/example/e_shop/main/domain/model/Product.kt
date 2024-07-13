package com.example.e_shop.main.domain.model

import com.google.gson.annotations.SerializedName


data class Product (

  @SerializedName("id"          ) var id          : Int?              = null,
  @SerializedName("title"       ) var title       : String?           = null,
  @SerializedName("price"       ) var price       : Int?              = null,
  @SerializedName("description" ) var description : String?           = null,
  @SerializedName("images"      ) var images      : ArrayList<String> = arrayListOf(),
  @SerializedName("creationAt"  ) var creationAt  : String?           = null,
  @SerializedName("updatedAt"   ) var updatedAt   : String?           = null,
  @SerializedName("category"    ) var category    : Category?         = Category()

)