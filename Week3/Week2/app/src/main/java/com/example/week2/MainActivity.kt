package com.example.week2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var getResultText : ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("test", "onCreate");

        getResultText = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
            if(result.resultCode == RESULT_OK){
                val value1 = result.data?.getIntExtra("value1", 0)
                val value2 = result.data?.getDoubleExtra("value2", 0.0)
                val value3 = result.data?.getBooleanExtra("value3", true)
                val value4 = result.data?.getStringExtra("value4")

                textView.text = "value1 : ${value1}\n"
                textView.append("value2 : ${value2}\n")
                textView.append("value3 : ${value3}\n")
                textView.append("value4 : ${value4}\n")
            }
        }

        button.setOnClickListener {
            val Second_intent = Intent(this, SecondActivity::class.java)
            Second_intent.putExtra("data1", 100)
            Second_intent.putExtra("data2", 1.1)
            Second_intent.putExtra("data3", false)
            Second_intent.putExtra("data4", "문자열1")

            setResult(RESULT_OK, intent)
            getResultText.launch(Second_intent)
        }
    }


//    override fun onStart() {
//        super.onStart()
//        Log.d("test", "onStart");
//    }
//
//
//    //Activity Running
//    override fun onResume() {
//        super.onResume()
//        Log.d("test", "onResume");
//    }
//
//    override fun onPause() {
//        super.onPause()
//        Log.d("test", "onPaues");
//    }
//
//    override fun onStop() {
//        super.onStop()
//        Log.d("test", "onStop");
//    }
//
//    //종료될 때는 onDestory 에서 코드를
//    override fun onDestroy() {
//        super.onDestroy()
//        Log.d("test", "onDestroy");
//    }
//
//    //다시 돌아옴 onStart()로 간다. 따라서, 다시 시작할 경우에는 onResume 에서 다시 시작한 코드를
//    override fun onRestart() {
//        super.onRestart()
//        Log.d("test", "onRestart");
//    }

}