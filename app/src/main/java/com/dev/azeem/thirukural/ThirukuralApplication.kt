package com.dev.azeem.thirukural

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.dev.azeem.thirukural.data.FavKuralRepository

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "favourites")

class ThirukuralApplication : Application() {
    lateinit var favKuralRepository: FavKuralRepository
    override fun onCreate() {
        super.onCreate()
        favKuralRepository = FavKuralRepository(dataStore)
    }
}