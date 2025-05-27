package com.pdmcourse.spotlyfe.data.repository

import com.pdmcourse.spotlyfe.data.dao.PlaceDao
import com.pdmcourse.spotlyfe.data.database.entities.PlaceEntity
import com.pdmcourse.spotlyfe.data.model.Place
import kotlinx.coroutines.flow.Flow

interface PlaceRepository {
    fun getPlaces(): Flow<List<Place>>
    suspend fun savePlace(place: Place)
}