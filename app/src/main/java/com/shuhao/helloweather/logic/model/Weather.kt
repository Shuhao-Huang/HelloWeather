package com.shuhao.helloweather.logic.model

data class Weather(val realtimeResponse: RealtimeResponse.Realtime, val dailyResponse: DailyResponse.Daily)