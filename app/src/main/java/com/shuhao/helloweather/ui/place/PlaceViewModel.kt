package com.shuhao.helloweather.ui.place

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.shuhao.helloweather.logic.Repository
import com.shuhao.helloweather.logic.model.Place
import com.shuhao.helloweather.logic.model.PlaceResponse

class PlaceViewModel: ViewModel() {

    val searchLiveData = MutableLiveData<String>()
    val placesList = ArrayList<Place>()

    val placeLiveData = Transformations.switchMap(searchLiveData){query->
        Repository.searchPlace(query)
    }

    fun searchPlaces(query: String) {
        searchLiveData.value = query
    }

}