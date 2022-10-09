package com.example.lesson3

import android.app.Instrumentation.ActivityResult
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider

private const val TAG = "MainActivity"
private const val KEY_INDEX = "index"
class MainActivity : AppCompatActivity() {
    private lateinit var btnTrue : Button
    private lateinit var btnFalse : Button
    private lateinit var nextButton : Button
    private lateinit var prevButton : Button
    private lateinit var questButton: Button
    private lateinit var cheatButton: Button
    private val quizViewModel: QuizViewModel by lazy{
        val provider = ViewModelProvider(this)
        provider.get(QuizViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        quizViewModel.currentIndex = savedInstanceState?.getInt(KEY_INDEX)?:0
        btnTrue = findViewById(R.id.true_button)
        btnFalse = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        prevButton = findViewById(R.id.prev_button)
        questButton = findViewById(R.id.question_text)
        cheatButton = findViewById(R.id.cheat_button)
        val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result -> if (result.resultCode == RESULT_OK)
                        {
                                val data: Intent? = result.data
                            quizViewModel.isCheater = data?.getBooleanExtra(EXTRA_ANSWER_SHOWN, false)?:false
                        }
        }
        cheatButton.setOnClickListener{
            val intent = CheatActivity.newIntent(this@MainActivity, quizViewModel.currentQuestionAnswer)
            resultLauncher.launch(intent)
        }
//        btnTrue.setOnClickListener{
//            checkAnswer(True)
//        }
//        btnFalse.setOnClickListener{
//            checkAnswer(False)
//        }
//        nextButton.set
    }
}