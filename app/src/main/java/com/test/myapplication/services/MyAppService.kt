package com.test.myapplication.services

import android.app.IntentService
import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log

class MyAppService : IntentService("MyService") {

    private var TAG: String = javaClass.simpleName

    init {
        Log.d(TAG, "SERVICE_STARTED");
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onHandleIntent(intent: Intent?) {
        TODO("Not yet implemented")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        /*  thread {
              Thread.sleep(1000)
              Log.d(TAG,"SERVICE_RUNNING")
          }*/

        val mainHandler = Handler(Looper.getMainLooper())

        mainHandler.post(object : Runnable {
            override fun run() {
                Log.d(TAG, "SERVICE_RUNNING")
                mainHandler.postDelayed(this, 1000)
            }
        })


        //  return super.onStartCommand(intent, flags, startId)
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "SERVICE_DESTROYED")
    }
}