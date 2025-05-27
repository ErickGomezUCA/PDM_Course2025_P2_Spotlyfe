package com.pdmcourse.spotlyfe.data.repository

import android.util.Log
import com.pdmcourse.spotlyfe.data.dao.PlaceDao
import com.pdmcourse.spotlyfe.data.database.entities.toDomain
import com.pdmcourse.spotlyfe.data.model.Place
import com.pdmcourse.spotlyfe.data.model.toDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PlaceRepositoryImpl(
    val placeDao: PlaceDao,
): PlaceRepository {
    override fun getPlaces(): Flow<List<Place>> {
        return placeDao.getPlaces().map { list ->
            list.map { it.toDomain() }
        }
    }

    override suspend fun savePlace(place: Place) {
        val placeToDatabase = place.toDatabase()

        Log.d("test1", placeToDatabase.toString())

        placeDao.savePlace(place.toDatabase())
    }
}