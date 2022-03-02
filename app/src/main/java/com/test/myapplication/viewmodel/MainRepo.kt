package com.test.myapplication.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.test.myapplication.commons.APIClient
import com.test.myapplication.commons.ApiInterface
import com.test.myapplication.model.ForecastWeatherResponse
import com.test.myapplication.model.NewsResponse
import com.test.myapplication.model.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by Kashif on 10/9/2019.
 */

class MainRepo @Inject constructor (@Named("weather")private val apiInterface: ApiInterface, @Named("news")private val newsApiInterface: ApiInterface){

    val TAG = javaClass.simpleName

    fun fetch_weather(city_name : String, appid : String): MutableLiveData<WeatherResponse>{
        var mutableList: MutableLiveData<WeatherResponse> = MutableLiveData()


       // var apiInterface = APIClient.client.create(ApiInterface::class.java)

        apiInterface.get_weather(city_name, appid).enqueue(object: Callback<WeatherResponse>{

            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                Log.e(TAG, "onResponse response="+response.toString() )

                if(response.isSuccessful){

                    if(response.body()!=null ) {
                      //  mutableList.value = response.body()!!
                        mutableList.postValue(response.body()!!)
                    }
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.e(TAG, "onFailure call="+t.message )
            }

        })

        return mutableList
    }

    fun forecast_weather(city_name : String, appid : String): MutableLiveData<ForecastWeatherResponse>{
        var mutableList: MutableLiveData<ForecastWeatherResponse> = MutableLiveData()


      //  var apiInterface = APIClient.client.create(ApiInterface::class.java)

        apiInterface.forecast_weather(city_name, appid).enqueue(object: Callback<ForecastWeatherResponse>{

            override fun onResponse(call: Call<ForecastWeatherResponse>, response: Response<ForecastWeatherResponse>) {
                Log.e(TAG, "onResponse response="+response.toString() )

                if(response.isSuccessful){

                    if(response.body()!=null ) {
                      //  mutableList.value = response.body()!!
                        mutableList.postValue(response.body()!!)
                    }
                }
            }

            override fun onFailure(call: Call<ForecastWeatherResponse>, t: Throwable) {
                Log.e(TAG, "onFailure call="+t.message )
            }

        })

        return mutableList
    }


    fun fetch_news(topic: String, appid : String): MutableLiveData<NewsResponse>{
        var mutableList: MutableLiveData<NewsResponse> = MutableLiveData()

      //  var apiInterface = APIClient.news_client.create(ApiInterface::class.java)

        newsApiInterface.get_news(topic, appid).enqueue(object: Callback<NewsResponse>{

            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                Log.e(TAG, "onResponse response="+response.toString() )

                if(response.isSuccessful){

                    if(response.body()!=null ) {
                     //   mutableList.value = response.body()!!
                        mutableList.postValue(response.body()!!)
                    }
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.e(TAG, "onFailure call="+t.message )
            }

        })

        return mutableList
    }

}