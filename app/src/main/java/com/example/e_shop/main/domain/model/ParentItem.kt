package com.example.e_shop.main.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ParentItem(
    var id: Int,
    var title: String? = null,
    var seeAll: String? = null,
    var childItems: List<Product>? = null
): Parcelable
