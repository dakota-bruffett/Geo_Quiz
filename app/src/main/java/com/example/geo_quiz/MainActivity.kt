package com.example.geo_quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModel
private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var Truth_Button: Button
    private  lateinit var False_button: Button
    private lateinit var Question:TextView
    private lateinit var Next:Button
    private lateinit var Cheat:Button



    override fun onCreate(savedInstanceState: Bundle?) {
        Truth_Button = findViewById(R.id.Truth_button)
        False_button = findViewById(R.id.False_button)
        Question = findViewById(R.id.Question)
        Next= findViewById(R.id.Next)



        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called" )

        setContentView(R.layout.activity_main)
        Truth_Button.setOnClickListener{
            checkAwnser(true)
        False_button.setOnClickListener {
            checkAwnser(false)
            Next.setOnClickListener {
                currentIndex = (currentIndex + 1) % questionBank.size
                val questionTextResId = questionBank[currentIndex].textResId
                updateQuestion()
            }
        }
        }


    }

    private fun checkAwnser(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer
        val messageResId = if (userAnswer == correctAnswer) {
            R.string.correct_toast

        } else {
            R.string.incorrect_toast
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
            .show()
    }
    override fun onStart(){
        super.onStart()
        Log.d(TAG, "onStart()Called)")
    }
    override fun onResume(){
        super.onResume()
        Log.d(TAG, "onStart()Called")
    }
    override fun onStop(){
        super.onStop()
        Log.d(TAG, "onStop()Called")
    }
    override  fun onDestroy(){
        super.onDestroy()
        Log.d(TAG, "onDestroy()Called")
    }

    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        Question.setText(questionTextResId)
    }


    private val  questionBank = listOf(
            Question(R.string.Question_Africa,true),
            Question(R.string.Question_America, true),
            Question(R.string.Question_Asia, true),
            Question(R.string.Question_Austraila, true),
            Question(R.string.Question_Midwest, true),
            Question(R.string.questions_Oceans, true))
        private  var currentIndex = 0
    }
