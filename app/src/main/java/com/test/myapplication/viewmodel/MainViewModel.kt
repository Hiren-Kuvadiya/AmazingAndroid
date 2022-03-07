package com.test.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.myapplication.model.ForecastWeatherResponse
import com.test.myapplication.model.NewsResponse
import com.test.myapplication.model.WeatherResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(var main_repo: MainRepo) : ViewModel() {

    var mutableLiveData: MutableLiveData<WeatherResponse>? = null
    var forecastMutableLiveData: MutableLiveData<ForecastWeatherResponse>? = null
    var newsMutableLiveData: MutableLiveData<NewsResponse>? = null

    init {
        //     main_repo = MainRepo()
    }


    // coroutines using launch
    fun get_weather(city_name: String, appid: String): LiveData<WeatherResponse> {
        if (mutableLiveData == null) {

            viewModelScope.launch() {
                mutableLiveData = main_repo!!.fetch_weather(city_name, appid)
            }

        }
        return mutableLiveData!!
    }

    // coroutines using async and wait
    suspend fun forecast_weather(city_name: String, appid: String): LiveData<ForecastWeatherResponse> {
        if (forecastMutableLiveData == null) {

            forecastMutableLiveData = viewModelScope.async {
                main_repo!!.forecast_weather(city_name, appid)
            }.await()

        }
        return forecastMutableLiveData!!
    }

    // coroutines using launch and join
    suspend fun get_news(topic: String, appid: String): LiveData<NewsResponse>? {
        if (newsMutableLiveData == null) {
            viewModelScope.launch() {
                newsMutableLiveData = main_repo!!.fetch_news(topic, appid)
            }.join()
            return newsMutableLiveData!!
        }
        return null
    }

}