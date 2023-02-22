package com.example.geo_quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Geo2 : AppCompatActivity() {
    private lateinit var Yes: Button
    private lateinit var No: Button
    private lateinit var Queuestion:TextView

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_geo2)
    }
}