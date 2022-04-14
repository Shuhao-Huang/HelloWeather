package com.shuhao.helloweather

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class HelloWeatherApplication: Application() {
    companion object{
        const val TOKEN = "NnRUj6WvT9iUD3ce"
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}