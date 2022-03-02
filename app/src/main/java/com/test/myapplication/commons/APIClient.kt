package com.test.myapplication.commons

import com.test.myapplication.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class APIClient {

    companion object {

        val baseURL: String = Constants.BASE_URL
        val newsBaseURL: String = Constants.BASE_URL_NEWS


        val client: Retrofit
            get() {

                var retofit: Retrofit? = null

                val interceptor = HttpLoggingInterceptor()
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                val client = OkHttpClient.Builder()
                    .addInterceptor(interceptor).build()

                if (retofit == null) {
                    retofit = Retrofit.Builder()
                            .baseUrl(baseURL)
                            .client(client)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                }
                return retofit!!
            }


        val news_client: Retrofit
            get() {

                var retofit: Retrofit? = null

                val interceptor = HttpLoggingInterceptor()
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                val client = OkHttpClient.Builder()
                    .addInterceptor(interceptor).build()

                if (retofit == null) {
                    retofit = Retrofit.Builder()
                        .baseUrl(newsBaseURL)
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                }
                return retofit!!
            }

    }
}