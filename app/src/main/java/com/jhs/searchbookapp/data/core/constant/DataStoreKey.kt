package com.jhs.searchbookapp.data.core.constant

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey


enum class DataStoreKey {
    QUERY
}

fun DataStoreKey.toPreferenceKey(): Preferences.Key<String> = when (this) {
    DataStoreKey.QUERY -> stringPreferencesKey("query")
}