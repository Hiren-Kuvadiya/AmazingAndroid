package com.test.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.myapplication.model.ForecastWeatherResponse
import com.test.myapplication.model.NewsResponse
import com.test.myapplication.model.WeatherResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(var main_repo: MainRepo) : ViewModel(){

    var mutableLiveData: MutableLiveData<WeatherResponse>? = null
    var forecastMutableLiveData: MutableLiveData<ForecastWeatherResponse>? = null
    var newsMutableLiveData: MutableLiveData<NewsResponse>? = null

   /* init {
        main_repo = MainRepo()
    }*/

    fun get_weather(city_name : String, appid : String): LiveData<WeatherResponse> {
        if (mutableLiveData == null) {
            mutableLiveData = main_repo!!.fetch_weather(city_name, appid)
        }
        return mutableLiveData!!
    }

    fun forecast_weather(city_name : String, appid : String): LiveData<ForecastWeatherResponse> {
        if (forecastMutableLiveData == null) {
            forecastMutableLiveData = main_repo!!.forecast_weather(city_name, appid)
        }
        return forecastMutableLiveData!!
    }


    fun get_news(topic : String, appid : String): LiveData<NewsResponse> {
        if (newsMutableLiveData == null) {
            newsMutableLiveData = main_repo!!.fetch_news(topic, appid)
        }
        return newsMutableLiveData!!
    }

}