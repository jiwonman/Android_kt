package com.example.ipc

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.os.SystemClock
import android.util.Log
import androidx.core.app.NotificationCompat
import kotlin.concurrent.thread

class IpcService : Service() {

    var value = 0

    var binder = LocalBinder()

    var isRunning = false

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ){
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val channel = NotificationChannel("service", "service", NotificationManager.IMPORTANCE_HIGH)

            channel.enableLights(true)
            channel.enableVibration(true)
            manager.createNotificationChannel(channel)

            val builder = NotificationCompat.Builder(this, "service")
            builder.setSmallIcon(android.R.drawable.ic_menu_camera)
            builder.setContentTitle("서비스가 가동.")
            builder.setContentText("서비스가 가동중 입니다.")

            val notification = builder.build()

            startForeground(10, notification)
        }

        isRunning = true

        thread {
            while (isRunning){
                SystemClock.sleep(500)
                Log.d("test", "value : $value")
                value++
            }
        }

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()

        isRunning = false
    }

    fun getNumber() : Int{
        return value
    }

    inner class LocalBinder : Binder(){
        fun getService() : IpcService{
            return this@IpcService
        }
    }
}