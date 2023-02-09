package com.example.geo_quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var Truth_Button: Button
    private  lateinit var False_button: Button
    private lateinit var Question:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        Truth_Button = findViewById(R.id.Truth_button)
        False_button = findViewById(R.id.False_button)
        Question = findViewById(R.id.Question)

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)


    }
    private const val TAG = "QuizViewModel"
    class QuizViewModel : ViewModel(){
        init {
            Log.d(TAG, "ViewModel instance Created")
        }

        override fun onCleared() {
            super.onCleared()
            Log.d(TAG, "ViewModel Instance about to be destroyed" )
        }
        private val  questionBank = listOf<>(
            Question(R.id.Question, true),
            Question(R.id.Question, true),
            Question(R.id.Question, true),
            Question(R.id.Question, true),
            Question(R.id.Question, true),

        )
    }
}