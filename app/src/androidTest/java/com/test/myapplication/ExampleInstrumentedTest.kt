package com.test.myapplication

import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.gson.Gson
import com.test.myapplication.commons.APIClient
import com.test.myapplication.utils.AppUtils

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import com.test.myapplication.commons.ApiInterface
import com.test.myapplication.model.NewsResponse
import com.test.myapplication.model.WeatherResponse
import com.test.myapplication.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    private var TAG = javaClass.simpleName

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.test.myapplication", appContext.packageName)
    }

    @Test
    fun checkInternetConnectionTest() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val result = AppUtils.isNetworkConnected(appContext)

        assert(result).equals(true)
    }


    @Test
    fun weatherTest() {

        var mAPIClient: ApiInterface = APIClient.client.create(ApiInterface::class.java)

        var call: Call<WeatherResponse> =
            mAPIClient.get_weather("Surat", Constants.WEAHTER_API_KEY);

        try {

            var response: Response<WeatherResponse> = call.execute();

            var authResponse = response.body()

            Log.e(TAG, "weatherTest:" + Gson().toJson(authResponse).toString())

             assertTrue(response.isSuccessful());

        } catch (e: Exception) {
            e.printStackTrace();
        }
    }


}