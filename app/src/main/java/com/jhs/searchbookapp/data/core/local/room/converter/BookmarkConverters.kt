package com.jhs.searchbookapp.data.core.local.room.converter

import androidx.room.TypeConverter
import com.google.gson.Gson

class BookmarkConverters {
    @TypeConverter
    fun listToJson(value: List<String>?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String): List<String>? {
        return Gson().fromJson(value,Array<String>::class.java)?.toList()
    }
}