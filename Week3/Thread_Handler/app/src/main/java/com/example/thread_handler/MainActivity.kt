package com.example.thread_handler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    var isRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener{
            val now = System.currentTimeMillis()
            textView3.text = "버튼 클릭 : $now"
        }

//        thread {
//            while (isRunning){
//                SystemClock.sleep(500)
//                val now2 = System.currentTimeMillis()
//
//                Log.d("test", "thread : $now2")
//
//                runOnUiThread(object : Thread() {
//                    override fun run() {
//                        super.run()
//                        textView2.text = "runOnUiThread : $now2"
//                    }
//                })
//
//            }
//        }
        val handler = Handler(Looper.myLooper()!!)

        val thread1 = object : Thread() {
            override fun run() {
                super.run()

                val now2 = System.currentTimeMillis()
                textView4.text = "handler : $now2"
                SystemClock.sleep(500)

                handler.postDelayed(this, 100)
            }
        }

        handler.postDelayed(thread1, 100)
    }
//    override fun onDestroy() {
//        super.onDestroy()
//
//        isRunning = false
//    }
}