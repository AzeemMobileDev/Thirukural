package com.dev.azeem.thirukural.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavKuralRepository(private val dataStore: DataStore<Preferences>) {

    private val gson: Gson = Gson()

    private companion object {
        val FAVORITES = stringPreferencesKey("favourites")
    }

    suspend fun saveData(name: Kural) {
        dataStore.edit { preferences ->
            val jsonString = gson.toJson(name)
            preferences[FAVORITES] = jsonString
        }
    }

    val getData: Flow<Kural> = dataStore.data
        .map { preferences ->
            val jsonString = preferences[FAVORITES] ?: "{ kural: [] }"
            gson.fromJson(jsonString, Kural::class.java)
        }
}