package com.shuhao.helloweather.logic.network

import com.shuhao.helloweather.HelloWeatherApplication
import com.shuhao.helloweather.logic.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PlaceService {

    @GET("v2/place?token=NnRUj6WvT9iUD3ce&lang=zh_CN")
    fun searchPlaces(@Query("query") query: String): Call<PlaceResponse>

}