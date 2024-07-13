package com.example.e_shop.main.domain.model

import com.google.gson.annotations.SerializedName


data class Category (

  @SerializedName("id"         ) var id         : Int?    = null,
  @SerializedName("name"       ) var name       : String? = null,
  @SerializedName("image"      ) var image      : String? = null,
  @SerializedName("creationAt" ) var creationAt : String? = null,
  @SerializedName("updatedAt"  ) var updatedAt  : String? = null

)