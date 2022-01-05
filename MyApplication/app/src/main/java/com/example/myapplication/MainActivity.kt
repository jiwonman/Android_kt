package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.recycle.view.*

class MainActivity : AppCompatActivity() {

    val imgRes = intArrayOf(
        R.drawable.rectangle, R.drawable.rectangle, R.drawable.rectangle,
        R.drawable.rectangle, R.drawable.rectangle, R.drawable.rectangle,
        R.drawable.rectangle, R.drawable.rectangle)

    val data1 = arrayOf("실내 걷기", "실외 걷기", "실외 걷기", "실내 걷기", "실외 걷기", "실외 걷기", "실내 걷기", "실외 걷기")
    val data2 = arrayOf("0.85KM","0.86KM", "3.21KM", "0.85KM","0.86KM", "3.21KM", "0.85KM","0.86KM")
    val data3 = arrayOf("2021.11.27. >", "2021.11.22. >", "2021.11.20. >", "2021.11.27. >", "2021.11.22. >", "2021.11.20. >", "2021.11.27. >", "2021.11.22. >")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter1 = RecyclerAdapter()
        recycler1.adapter = adapter1

        recycler1.layoutManager = LinearLayoutManager(this)


        val dataList = ArrayList<HashMap<String, Any>>()

        for(i in imgRes.indices){
            val map = HashMap<String, Any>()
            map["img"] = imgRes[i]
            map["data1"] = data1[i]
            map["data2"] = data2[i]
            map["data3"] = data3[i]

            dataList.add(map)

        }

        val keys = arrayOf("img", "data1", "data2", "data3")

        val ids = intArrayOf(R.id.rowImage, R.id.rowText1, R.id.rowText2, R.id.buttonName)

    }

    inner class RecyclerAdapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolderClass>(){

        inner class ViewHolderClass(itemView: View) : RecyclerView.ViewHolder(itemView){
            val rowImageView = itemView.rowImage
            val rowTextView1 = itemView.rowText1
            val rowTextView2 = itemView.rowText2
            val rowButton = itemView.buttonName
        }

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): RecyclerAdapter.ViewHolderClass {
            val itemView = layoutInflater.inflate(R.layout.recycle, parent, false)
            val holder = ViewHolderClass(itemView)

            return holder
        }

        override fun getItemCount(): Int {
            return imgRes.size
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            holder.rowImageView.setImageResource(imgRes[position])
            holder.rowTextView1.text = data1[position]
            holder.rowTextView2.text = data2[position]
            holder.rowButton.text = data3[position]
        }
    }



}