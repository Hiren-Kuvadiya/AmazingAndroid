package com.test.myapplication.di.modules

import com.test.myapplication.commons.APIClient
import com.test.myapplication.commons.ApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class ApiInterfaceModule @Inject constructor() {

    @Singleton
    @Provides
    @Named("weather")
    fun getApiInterface(): ApiInterface{
        return  APIClient.client.create(ApiInterface::class.java)
    }


    @Singleton
    @Provides
    @Named("news")
    fun getNewsInterface(): ApiInterface{
        return APIClient.news_client.create(ApiInterface::class.java)
    }

}