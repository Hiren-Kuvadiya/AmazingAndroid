package com.test.myapplication

import android.app.Application
import androidx.multidex.MultiDex
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.test.myapplication.workmanager.WeatherWorker
import dagger.hilt.android.HiltAndroidApp
import java.util.concurrent.TimeUnit


@HiltAndroidApp
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        MultiDex.install(this)
        setUpWorker()
    }

    private fun setUpWorker() {
        val constraint =
            Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build();
        val workRequest =
            PeriodicWorkRequest.Builder(WeatherWorker::class.java, 30, TimeUnit.MINUTES)
                .setConstraints(constraint).build();
        WorkManager.getInstance(this).enqueue(workRequest)
    }

}