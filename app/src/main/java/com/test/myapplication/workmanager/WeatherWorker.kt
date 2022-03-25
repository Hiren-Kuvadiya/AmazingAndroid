package com.test.myapplication.workmanager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.test.myapplication.commons.APIClient
import com.test.myapplication.commons.ApiInterface
import com.test.myapplication.model.WeatherResponse
import com.test.myapplication.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherWorker(private val context: Context, params: WorkerParameters) :
    Worker(context, params) {
    private var TAG: String = javaClass.simpleName

    override fun doWork(): Result {

        Log.e(TAG,"WORK_MANAGER")

        var apiService = APIClient.client.create(ApiInterface::class.java)
        val call = apiService.get_weather("Surat", Constants.NEWS_API_KEY)

        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {

             //   Log.e(TAG, "WEATHER_REPONSE;"+Gson().toJson(response).toString())

            }

            override fun onFailure(call: Call<WeatherResponse>?, t: Throwable?) {

            }
        })


        return Result.success()
    }




}

