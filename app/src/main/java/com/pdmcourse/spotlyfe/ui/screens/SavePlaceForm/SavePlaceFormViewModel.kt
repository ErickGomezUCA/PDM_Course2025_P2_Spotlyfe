package com.pdmcourse.spotlyfe.ui.screens.SavePlaceForm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.pdmcourse.spotlyfe.SpotLyfeApplication
import com.pdmcourse.spotlyfe.data.model.Place
import com.pdmcourse.spotlyfe.data.repository.PlaceRepository
import com.pdmcourse.spotlyfe.ui.screens.SavedPlaces.SavedPlacesViewModel
import kotlinx.coroutines.launch

class SavePlaceFormViewModel(
    val placeRepository: PlaceRepository
): ViewModel() {

    fun savePlace(place: Place) {
        viewModelScope.launch {
            placeRepository.savePlace(place)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as SpotLyfeApplication
                SavedPlacesViewModel(application.appProvider.providePlaceRepository())
            }
        }
    }
}