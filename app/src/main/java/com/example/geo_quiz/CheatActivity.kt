package com.example.geo_quiz

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

private const val EXTRA_AWNSER_TRUE= "com.example.geo_quiz.answer_is_true"
class CheatActivity : AppCompatActivity() {
    private lateinit var AnswerTextView: TextView
    private lateinit var Show_Answer_Button:Button
    private lateinit var warning_text :TextView
    private var answerIsTrue = false

    companion object{
     fun newIntent(packageContext:Context, answerIsTrue: Boolean):Intent{
         return Intent(packageContext, CheatActivity::class.java).apply {
             putExtra(EXTRA_AWNSER_TRUE,answerIsTrue)
         }
     }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)
        Show_Answer_Button = findViewById(R.id.show_answer_button)
        AnswerTextView = findViewById(R.id.AnswerTextView)
        warning_text = findViewById(R.id.warning_text)
        Show_Answer_Button.setOnClickListener {
            val AnswerText = when{
                answerIsTrue-> R.string.truth
                else-> R.string.False
            }
            AnswerTextView.setText(AnswerText)
            setAnswerShownResult(true)
        }


        answerIsTrue= intent.getBooleanExtra(EXTRA_AWNSER_TRUE, false)


    }

    private fun setAnswerShownResult(isAwnswerShowen: Boolean) {
        val data = Intent().apply{
            putExtra(EXTRA_AWNSER_TRUE, isAwnswerShowen)
        }
        setResult(Activity.RESULT_OK, data)

    }
}