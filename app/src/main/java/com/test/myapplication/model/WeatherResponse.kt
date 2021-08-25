package com.test.myapplication.model

import com.google.gson.annotations.SerializedName


class WeatherResponse{

    @SerializedName("weather")
    lateinit var weather: ArrayList<Weather>

    @SerializedName("main")
    lateinit var weatherMain: WeatherMain

    @SerializedName("dt_txt")
    lateinit var dt_txt: String

    @SerializedName("dt")
    lateinit var dt: String


    class   WeatherMain {

        @SerializedName("temp")
        var temp: String = ""

        @SerializedName("temp_min")
        var temp_min: Double = 0.0

        @SerializedName("temp_max")
        var temp_max: Double = 0.0

    }

}

