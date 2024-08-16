package com.example.e_shop.main.domain.model

data class ParentItem(
    var id: Int,
    var title: String? = null,
    var seeAll: String? = null,
    var childItems: List<Product>? = null
)
