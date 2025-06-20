package com.pdmcourse.spotlyfe.ui.screens.SavedPlaces

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.pdmcourse.spotlyfe.data.model.Place
import com.pdmcourse.spotlyfe.ui.layout.CustomFloatingButton
import com.pdmcourse.spotlyfe.ui.layout.CustomTopBar
import com.pdmcourse.spotlyfe.ui.navigation.SavePlaceFormNavigation
import com.pdmcourse.spotlyfe.ui.navigation.SavedPlacesScreenNavigation

@Composable
fun SavedPlacesScreen(
  navigate: () -> Unit,
  viewModel: SavedPlacesViewModel = viewModel(factory = SavedPlacesViewModel.Factory)
) {

  val places = viewModel.places.collectAsStateWithLifecycle()

  Log.d("test1", places.toString())

  val UCA = Place(
    id = 1,
    name = "Centro Monseñor Romero",
    remark = "Marker in Centro Monseñor Romero",
    latitude = 13.679024407659101,
    longitude = -89.23578718993471,
  )

  val cameraPositionState = rememberCameraPositionState {
    position = CameraPosition.fromLatLngZoom(LatLng(UCA.latitude, UCA.longitude), 16f)
  }

  var uiSettings by remember {
    mutableStateOf(MapUiSettings(zoomControlsEnabled = false))
  }
  var properties by remember {
    mutableStateOf(MapProperties(mapType = MapType.HYBRID))
  }

  Scaffold(
    topBar = { CustomTopBar() },
    floatingActionButton = { CustomFloatingButton(onClick = navigate)}
  ) { innerPadding ->
    Column(modifier = Modifier.padding(innerPadding)) {

      GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        properties = properties,
        uiSettings = uiSettings
      ) {
        places.value.forEach {
          Marker(
            state = MarkerState(position = LatLng(it.latitude, it.longitude)),
            title = it.name,
            snippet = it.remark
          )
        }
      }
    }
  }
}