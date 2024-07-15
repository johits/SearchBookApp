package com.jhs.searchbookapp.data.core.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.jhs.searchbookapp.data.core.constant.DataStoreKey
import com.jhs.searchbookapp.data.core.constant.toPreferenceKey
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class DataStoreManager @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    fun fetchSyncDataStoreString(key: DataStoreKey): String {
        val value = runBlocking {
            dataStore.data.first()
        }[key.toPreferenceKey()] ?: ""
        return value
    }

    fun fetchDataStoreString(key: DataStoreKey): Flow<String> {
        return dataStore.data.map { it[key.toPreferenceKey()] ?: "" }
    }

    suspend fun patchDataStoreString(key: DataStoreKey, value: String) {
        dataStore.edit { it[key.toPreferenceKey()] = value }
    }

    fun patchSyncDataStoreString(key: DataStoreKey, value: String) {
        runBlocking(Dispatchers.IO) {
            dataStore.edit { it[key.toPreferenceKey()] = value }
        }
    }

    suspend fun removeDataString(key: DataStoreKey) {
        dataStore.edit { it.remove(key.toPreferenceKey()) }
    }

    fun removeSyncDataStoreString(key: DataStoreKey) {
        runBlocking(Dispatchers.IO) {
            dataStore.edit { it.remove(key.toPreferenceKey()) }
        }
    }
}