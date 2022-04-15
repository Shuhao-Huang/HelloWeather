package com.shuhao.helloweather.ui.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.shuhao.helloweather.logic.Repository
import com.shuhao.helloweather.logic.model.Location

class WeathreViewModel: ViewModel() {
    private val locationLiveData = MutableLiveData<Location>()

    var placeName = ""

    var locationLng = ""

    var locationLat = ""

    val weathreLiveData = Transformations.switchMap(locationLiveData) {
        location ->
        Repository.refreshWeather(location.lng, location.lat)
    }

    fun refreshWeather(lng: String, lat: String) {
        locationLiveData.value = Location(lng, lat)
    }

}