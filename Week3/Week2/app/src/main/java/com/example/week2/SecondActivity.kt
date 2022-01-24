package com.example.week2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val data1 = intent.getIntExtra("data1", 0)
        val data2 = intent.getDoubleExtra("data2", 0.0)
        val data3 = intent.getBooleanExtra("data3", true)
        val data4 = intent.getStringExtra("data4")

        textView2.text = "data1 : ${data1}\n"
        textView2.append("data2 : ${data2}\n")
        textView2.append("data3 : ${data3}\n")
        textView2.append("data4 : ${data4}\n")

        button2.setOnClickListener{
            val mainIntent = Intent(this, MainActivity::class.java)

            mainIntent.putExtra("value1", 100)
            mainIntent.putExtra("value2", 0.0)
            mainIntent.putExtra("value3", true)
            mainIntent.putExtra("value4", "문자열1")
            setResult(RESULT_OK, mainIntent)

            finish();           //해당 Activity 끝냄
        }
    }
}