package com.shuhao.helloweather.logic

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.shuhao.helloweather.logic.dao.PlaceDao
import com.shuhao.helloweather.logic.model.Place
import com.shuhao.helloweather.logic.model.RealtimeResponse
import com.shuhao.helloweather.logic.model.Weather
import com.shuhao.helloweather.logic.network.HelloWeatherNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import java.lang.Exception
import java.lang.RuntimeException
import kotlin.coroutines.CoroutineContext

object Repository {

    fun savePlace(place: Place) = PlaceDao.savePlace(place)

    fun getSavedPlace() = PlaceDao.getSavedPlace()

    fun isPlaceSaved() = PlaceDao.isPlaceSaved()

    fun searchPlace(query: String) = fire<List<Place>>(Dispatchers.IO) {

        val placeResponse = HelloWeatherNetwork.searchPlace(query)
        if (placeResponse.status == "ok") {
            val places = placeResponse.places
            Result.success(places)
        } else {
            Result.failure(RuntimeException("response status is ${placeResponse.status}"))
        }

    }

    fun refreshWeather(lng: String, lat: String) = fire(Dispatchers.IO) {
        coroutineScope {
            val deferredRealtime = async {
                HelloWeatherNetwork.getRealtimeWeather(lng, lat)
            }

            val deferredDaily = async {
                HelloWeatherNetwork.getDailyWeather(lng, lat)
            }

            val realtimeResponse = deferredRealtime.await()
            val dailyResponse = deferredDaily.await()

            if (realtimeResponse.status == "ok" && dailyResponse.status == "ok") {
                val weathre = Weather(realtimeResponse.result.realtime, dailyResponse.result.daily)
                Result.success(weathre)
            } else {
                Result.failure(
                    RuntimeException(
                        "realtimeResponse status is ${realtimeResponse.status}"
                                +
                                "dailyResponse status is ${dailyResponse.status}"
                    )
                )
            }
        }
    }

    private fun <T> fire(context: CoroutineContext, block: suspend () -> Result<T>) =
        liveData (context) {
            val result = try {
                block()
            } catch (e: Exception) {
                Result.failure<T>(e)
            }
            emit(result)
        }


}