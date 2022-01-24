package com.example.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.os.SystemClock
import android.util.Log
import androidx.core.app.NotificationCompat
import kotlin.concurrent.thread

class textService : Service() {

    var isRunning = false

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Log.d("test", "서비스 가동")

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ){
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val channel = NotificationChannel("service", "service", NotificationManager.IMPORTANCE_HIGH)

            channel.enableLights(true)
            channel.enableVibration(true)

            manager.createNotificationChannel(channel)

            val builder = NotificationCompat.Builder(this, "service")

            builder.setSmallIcon(android.R.drawable.ic_menu_search)
            builder.setContentTitle("서비스 가동중")
            builder.setContentText("서비스가 가동 중입니다.")

            val notification = builder.build()

            startForeground(10, notification)
        }

        isRunning = true

        thread {
            while(isRunning){
                SystemClock.sleep(500)
                val now = System.currentTimeMillis()
                Log.d("test", "Service : $now")
            }
        }

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("test", "서비스 중지")

        isRunning = false;
    }
}