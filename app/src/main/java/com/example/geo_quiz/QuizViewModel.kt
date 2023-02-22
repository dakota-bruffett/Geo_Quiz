package com.example.geo_quiz

import android.util.Log
import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"
class QuizViewModel : ViewModel() {
    var currentIndex = 0
    var isCheater = false

         var  questionBank = listOf(
        Question(R.string.Question_Africa,true),
        Question(R.string.Question_America, true),
        Question(R.string.Question_Asia, true),
        Question(R.string.Question_Austraila, true),
        Question(R.string.Question_Midwest, true),
        Question(R.string.questions_Oceans, true))
        val currentQuestion: Boolean
        get()= questionBank[currentIndex].answer
        val currentQuestionText: Int
        get()= questionBank[currentIndex].textResId
        fun moveToText(){
            currentIndex = (currentIndex+1)% questionBank.size

        }



}