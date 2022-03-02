package com.test.myapplication.model

import com.google.gson.annotations.SerializedName


class ForecastWeatherResponse{

    @SerializedName("list")
    lateinit var weather: ArrayList<WeatherResponse>

}

