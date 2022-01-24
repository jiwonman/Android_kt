package com.example.ipc

import android.app.ActivityManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var ipcService: IpcService? = null

    val connection = object : ServiceConnection{
        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            val binder = p1 as IpcService.LocalBinder
            ipcService = binder.getService()
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            ipcService = null
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val serviceIntent = Intent(this, IpcService::class.java)
        val chk = isServiceRunning("com.example.ipc.IpcService")

        if(!chk){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ){
                startForegroundService(serviceIntent)
            } else {
                startService(serviceIntent)
            }
        }

        bindService(serviceIntent, connection, Context.BIND_AUTO_CREATE)

        button.setOnClickListener {
            var value = ipcService?.getNumber()
            textView.text = "value : $value"
        }
    }

    fun isServiceRunning(name: String) : Boolean {
        val manager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val serviceList = manager.getRunningServices(Int.MAX_VALUE)
        for(serviceInfo in serviceList){
            if(serviceInfo.service.className == name){
                return true
            }
        }

        return false
    }



}