package com.dev.azeem.thirukural.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.dev.azeem.thirukural.ThirukuralApplication
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class UiState(
    val favourites: Kural
)

class FavListViewModel(
    private val favKuralRepository: FavKuralRepository
) : ViewModel() {

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as ThirukuralApplication)
                FavListViewModel(application.favKuralRepository)
            }
        }
    }

    val uiState: StateFlow<UiState> =
        favKuralRepository.getData.map { favourites ->
            UiState(favourites)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = UiState(Kural(arrayListOf()))
        )

    fun saveFavourites(favKuralList: Kural) {
        viewModelScope.launch {
            favKuralRepository.saveData(favKuralList)
        }
    }
}