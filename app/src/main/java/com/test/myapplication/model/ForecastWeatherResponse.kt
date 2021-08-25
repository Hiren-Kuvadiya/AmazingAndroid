package com.test.myapplication.model

import com.google.gson.annotations.SerializedName


class ForecastWeatherResponse{

    @SerializedName("list")
    lateinit var weather: ArrayList<WeatherResponse>


  /*  class WeatherForcast {

        @SerializedName("main")
        lateinit var weatherMain: WeatherMain

        @SerializedName("weather")
        lateinit var weather: Weather

    }



    class   WeatherMain {

        @SerializedName("temp")
        var temp: String = ""

        @SerializedName("temp_min")
        var temp_min: String = ""

        @SerializedName("temp_max")
        var temp_max: String = ""

    }*/

}

