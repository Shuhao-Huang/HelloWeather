package com.shuhao.helloweather.logic.network

import com.shuhao.helloweather.HelloWeatherApplication
import com.shuhao.helloweather.logic.model.DailyResponse
import com.shuhao.helloweather.logic.model.RealtimeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherService {

    @GET("v2.5/${HelloWeatherApplication.TOKEN}/{lng},{lat}/realtime.json")
    fun getRealtimeWeather(@Path("lng") lng: String, @Path("lat") lat: String): Call<RealtimeResponse>

    @GET("v2.5/${HelloWeatherApplication.TOKEN}/{lng},{lat}/weather.json")
    fun getDailyWeather(@Path("lng") lng: String, @Path("lat") lat: String): Call<DailyResponse>

}