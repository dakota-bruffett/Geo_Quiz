package com.example.geo_quiz

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

private const val TAG = "MainActivity"
private const val KEY_INDEX = "Index"
private const val REQUEST_CODE_CHEAT = 0
private const val EXTRA_ANSWER_SHOWEN= "True"

class MainActivity : AppCompatActivity() {
    private lateinit var Truth_Button: Button
    private lateinit var False_button: Button
    private lateinit var Question: TextView
    private lateinit var Next: Button
    private lateinit var Cheat: Button

    @SuppressLint("RestrictAPI")
    override fun onCreate(savedInstanceState: Bundle?) {

        val currentIndex = savedInstanceState?.getInt(KEY_INDEX, 0) ?: 0
        quizViewModel.currentIndex = currentIndex

        Truth_Button = findViewById(R.id.Truth_button)
        False_button = findViewById(R.id.False_button)
        Question = findViewById(R.id.Question)
        Next = findViewById(R.id.Next)
        Cheat = findViewById(R.id.Cheat)



        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")

        setContentView(R.layout.activity_main)
        val provider: ViewModelProvider = ViewModelProviders.of(this)
        val quizViewModel = provider.get(QuizViewModel::class.java)
        Log.d(TAG, "Got a quizModel: $quizViewModel")

        Truth_Button.setOnClickListener {
            checkAwnser(true)
        }
            False_button.setOnClickListener {
                checkAwnser(false)
            }
                Next.setOnClickListener {


                    quizViewModel.moveToText()
                    updateQuestion()
            }
                    Cheat.setOnClickListener {
                        //Start CheatActivity
                        val answerIsTrue = quizViewModel.currentQuestion
                        val intent = CheatActivity.newIntent(this@MainActivity, answerIsTrue)
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {


                        val options = ActivityOptions
                            //.makeClipRevealAnimation(view, 0 ,0 view.width, view.height)

                        startActivityForResult(intent, REQUEST_CODE_CHEAT, options.toBundle())
                        }else{
                            startActivityForResult(intent, REQUEST_CODE_CHEAT)
                        }
                        updateQuestion()
                    }

    }

    private fun checkAwnser(userAnswer: Boolean) {
        val correctAnswer = quizViewModel.currentQuestion
        val messageId = when{
            quizViewModel.isCheater-> R.string.cheating_is_wrong
            userAnswer == correctAnswer-> R.string.correct_toast
            else -> R.string.incorrect_toast
        }

    }


    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart()Called)")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause()Called")
    }

    override fun onSaveInstanceState(savedInstanceStateState: Bundle) {
        super.onSaveInstanceState(savedInstanceStateState)
        Log.i(TAG, "onSaveInstanceState")
        savedInstanceStateState.putInt(KEY_INDEX, quizViewModel.currentIndex)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode!= Activity.RESULT_OK){
            return
        }
        if (requestCode== REQUEST_CODE_CHEAT){
            quizViewModel.isCheater = data?.getBooleanExtra(EXTRA_ANSWER_SHOWEN, false)?: false
        }

    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onStart()Called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop()Called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy()Called")
    }


    private fun updateQuestion() {

        val questionTextResId = quizViewModel.currentQuestion
        Question.setText(questionTextResId)
    }

    private val quizViewModel: QuizViewModel by lazy {
        ViewModelProviders.of(this).get(QuizViewModel::class.java)
    }


}

