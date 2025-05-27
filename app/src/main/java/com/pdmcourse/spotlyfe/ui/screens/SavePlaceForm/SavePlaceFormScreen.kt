package com.pdmcourse.spotlyfe.ui.screens.SavePlaceForm

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.pdmcourse.spotlyfe.data.model.Place
import com.pdmcourse.spotlyfe.ui.helpers.SelectLocationMap
import com.pdmcourse.spotlyfe.ui.layout.CustomTopBar

@Composable
fun SavePlaceFormScreen(
    navigateReturn: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SavePlaceFormViewModel = viewModel(factory = SavePlaceFormViewModel.Factory)
) {
    val name = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }
    val latitude = remember { mutableStateOf(0.0) }
    val longitud = remember { mutableStateOf(0.0) }
    val ids = remember {mutableStateOf(0)}

    Scaffold(
        topBar = { CustomTopBar(title = "test", onBackPressed = navigateReturn) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField (
                value = name.value,
                onValueChange = { name.value = it },
                placeholder = { Text( text = "Name" ) },
                modifier = Modifier.fillMaxWidth()
            )

            TextField (
                value = description.value,
                onValueChange = { description.value = it },
                placeholder = { Text( text = "Description" ) },
                modifier = Modifier.fillMaxWidth()
            )

            SelectLocationMap(onLocationChanged = { pos ->
                latitude.value = pos.latitude
                longitud.value = pos.longitude
            })

            Button(onClick = {
            val place = Place(
                id = ids.value,
                name = name.value,
                remark = description.value,
                latitude = latitude.value,
                longitude = longitud.value
            )

                ids.value = ids.value + 1

            viewModel.savePlace(place)
            navigateReturn()
            }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Save")
            }
    }
    }
}