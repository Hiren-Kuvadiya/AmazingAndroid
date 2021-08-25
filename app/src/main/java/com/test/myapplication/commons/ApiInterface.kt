package com.test.myapplication.commons

import com.test.myapplication.model.ForecastWeatherResponse
import com.test.myapplication.model.NewsResponse
import com.test.myapplication.model.WeatherResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiInterface {

    @GET("weather")
    fun get_weather(
        @Query("q") location: String,
        @Query("appid") appid: String
    ): Call<WeatherResponse>

    @GET("forecast")
    fun forecast_weather(
        @Query("q") location: String,
        @Query("appid") appid: String
    ): Call<ForecastWeatherResponse>

    @GET("everything")
    fun get_news(@Query("q") location: String, @Query("apiKey") appid: String): Call<NewsResponse>

}