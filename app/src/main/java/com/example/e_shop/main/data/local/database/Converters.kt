package com.example.e_shop.main.data.local.database

import androidx.room.TypeConverter
import com.example.e_shop.main.domain.model.Category
import com.google.gson.Gson


class Converters {

    private val gson = Gson()

    @TypeConverter
    fun fromStringList(value: ArrayList<String>?): String? {
        return value?.joinToString(separator = ",")
    }

    @TypeConverter
    fun toStringList(value: String?): ArrayList<String>? {
        return value?.split(",")?.let { ArrayList(it) }
    }

    @TypeConverter
    fun fromCategory(category: Category?): String? {
        return category?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toCategory(categoryString: String?): Category? {
        return categoryString?.let { gson.fromJson(it, Category::class.java) }
    }
}