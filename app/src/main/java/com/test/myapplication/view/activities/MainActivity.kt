package com.test.myapplication.view.activities

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.test.myapplication.R
import com.test.myapplication.utils.AppUtils
import com.test.myapplication.utils.Constants
import com.test.myapplication.model.ForecastWeatherResponse
import com.test.myapplication.model.NewsResponse
import com.test.myapplication.model.WeatherResponse
import com.test.myapplication.view.adapters.NewsAdapter
import com.test.myapplication.viewmodel.MainRepo
import com.test.myapplication.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.lang.Exception
import java.math.RoundingMode
import java.text.DecimalFormat
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    /*@Inject
    lateinit var mainRepo : MainRepo*/

    private lateinit var news_adapter: NewsAdapter

    private lateinit var main_view_model: MainViewModel

    //var main_view_model = MainViewModel()


    val TAG: String = javaClass.simpleName

    lateinit var dec_format: DecimalFormat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_view_model = ViewModelProvider(this).get(MainViewModel::class.java)

        dec_format = DecimalFormat("#.##")
        dec_format.roundingMode = RoundingMode.CEILING

        set_news_data()

        search_et.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                get_the_data()
                return@OnEditorActionListener true
            }
            false
        })

        search_iv.setOnClickListener(View.OnClickListener {
            get_the_data()
        })

    }


    fun get_the_data() {

        var city_name = search_et.text.toString().trim()

        if (TextUtils.isEmpty(city_name)) {
            search_et.requestFocus()
            search_et.setError("City name can't be empty")

        } else {
            progressBar.setVisibility(View.VISIBLE)

            get_weather_data(city_name)
            get_news_data(city_name)
            forcast_weather_data(city_name)


            // Moved coroutines from activity to viewmodel
          /*  CoroutineScope(Dispatchers.Main).launch {
                get_weather_data(city_name)
                get_news_data(city_name)
                forcast_weather_data(city_name)
            }*/

        }

    }


    fun get_weather_data(city_name: String) {

        main_view_model.get_weather(city_name, Constants.WEAHTER_API_KEY)
            .observe(this, object : Observer<WeatherResponse> {

                override fun onChanged(t: WeatherResponse) {
                    Log.e("WEATHER_RESPONSE:", "observe onChanged()=" + t)
                    progressBar.setVisibility(View.GONE)

                    city_tv.text = city_name

                    try {

                        Log.e(TAG, "MAX_TEMP:" + t.weatherMain.temp_max)
                        Log.e(TAG, "MIN_TEMP:" + t.weatherMain.temp_min)

                        var max = dec_format.format(t.weatherMain.temp_max - 272.15)
                        var min = dec_format.format(t.weatherMain.temp_min - 272.15)

                        max_min_tv.text = "max " + (max) + " / " + "min " + (min)
                    } catch (e: Exception) {

                    }


                    try {

                        var env_str =
                            "http://openweathermap.org/img/w/" + t.weather.get(0).icon + ".png"
                        Glide.with(this@MainActivity).load(env_str).into(env_iv)
                    } catch (e: Exception) {

                    }

                }

            })

    }


    fun forcast_weather_data(city_name: String) {

        main_view_model.forecast_weather(city_name, Constants.WEAHTER_API_KEY)
            .observe(this, object : Observer<ForecastWeatherResponse> {

                override fun onChanged(t: ForecastWeatherResponse) {
                    Log.e("FORE_WEATHER_RESPONSE:", "observe onChanged()=" + t)
                    progressBar.setVisibility(View.GONE)

                    Log.e(TAG, "WEATHER_SIZE:" + t.weather.size)

                    var templist = AppUtils.getList(t.weather)

                    var day1_img =
                        "http://openweathermap.org/img/w/" + templist.get(1).weather.get(0).icon + ".png"
                    Glide.with(this@MainActivity).load(day1_img).into(date1_iv)

                    var max1 = dec_format.format(templist.get(1).weatherMain.temp_max - 272.15)
                    var min1 = dec_format.format(templist.get(1).weatherMain.temp_min - 272.15)
                    date1_tv.setText("" + max1 + " / " + (min1))


                    var day2_img =
                        "http://openweathermap.org/img/w/" + templist.get(2).weather.get(0).icon + ".png"
                    Glide.with(this@MainActivity).load(day2_img).into(date2_iv)

                    var max2 = dec_format.format(templist.get(2).weatherMain.temp_max - 272.15)
                    var min2 = dec_format.format(templist.get(2).weatherMain.temp_min - 272.15)
                    date2_tv.setText("" + max2 + " / " + min2)


                    var day3_img =
                        "http://openweathermap.org/img/w/" + templist.get(3).weather.get(0).icon + ".png"
                    Glide.with(this@MainActivity).load(day3_img).into(date3_iv)

                    var max3 = dec_format.format(templist.get(3).weatherMain.temp_max - 272.15)
                    var min3 = dec_format.format(templist.get(3).weatherMain.temp_min - 272.15)
                    date3_tv.setText("" + max3 + " / " + min3)

                }

            })

    }


    fun get_news_data(city_name: String) {

        main_view_model.get_news(city_name, Constants.NEWS_API_KEY)
            .observe(this, object : Observer<NewsResponse> {

                override fun onChanged(t: NewsResponse) {
                    Log.e("NEWS_RESPONSE:", "observe onChanged()=" + Gson().toJson(t))
                    progressBar.setVisibility(View.GONE)

                    news_adapter.addData(t.articles)
                    news_adapter.notifyDataSetChanged()

                }
            })
    }


    fun set_news_data() {

        val layoutManager = LinearLayoutManager(this)
        news_rv.setLayoutManager(layoutManager)
        news_rv.setItemAnimator(DefaultItemAnimator())

        news_adapter = NewsAdapter(this)
        news_rv.adapter = news_adapter

    }


}