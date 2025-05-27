package com.pdmcourse.spotlyfe.ui.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pdmcourse.spotlyfe.ui.screens.SavePlaceForm.SavePlaceFormScreen
import com.pdmcourse.spotlyfe.ui.screens.SavedPlaces.SavedPlacesScreen

@Composable
fun MainNavigation(navController: NavHostController) {
  NavHost(navController = navController, startDestination = SavedPlacesScreenNavigation) {
    composable<SavedPlacesScreenNavigation> {
      SavedPlacesScreen(navigate = { navController.navigate(SavePlaceFormNavigation) })
    }

    composable<SavePlaceFormNavigation> {
      Log.d("test1", "si")
      SavePlaceFormScreen(navigateReturn = { navController.popBackStack() })
    }
  }
}