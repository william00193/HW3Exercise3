package com.example.hw3exercise2

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.hw3exercise2.databinding.ActivityMainBinding

import java.lang.Math.round
import java.math.RoundingMode
import kotlin.math.roundToInt


//Property that the log function takes, called a TAG
//filters down verbose logging
//Keeps function call cleaner
private const val TAG = "MainActivity"


class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding



    private val questionBank = listOf(
        question(R.string.question_australia, true),
        question(R.string.question_oceans, true),
        question(R.string.question_mideast, false),
        question(R.string.question_africa, false),
        question(R.string.question_americas, true),
        question(R.string.question_asia, true),
    )



// (New) added a new variable to substitute for currentIndex and im getting something different
    private var questionCount = 0

    private var numberCorrect = 0
    private var currentIndex = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


            Log.d(TAG, "onCreate (Bundle?) called")

            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)




        binding.trueButton.setOnClickListener {

            checkAnswer(true)
            binding.trueButton.isEnabled = !(binding.trueButton.isEnabled)
            binding.falseButton.isEnabled = !(binding.falseButton.isEnabled)

        }




        binding.falseButton.setOnClickListener {

            checkAnswer(false)
            binding.falseButton.isEnabled = !(binding.falseButton.isEnabled)
            binding.trueButton.isEnabled = !(binding.trueButton.isEnabled)

        }




        binding.questionTextView.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }




        binding.previousButton.setOnClickListener {
            currentIndex = (currentIndex - 1) % questionBank.size
            updateQuestion()
        }




        binding.nextButton.setOnClickListener {

            currentIndex = (currentIndex + 1) % questionBank.size

            binding.falseButton.isEnabled = true
            binding.trueButton.isEnabled = true

//(New) variable Increment of questionCount
            questionCount++

            updateQuestion()
            showPoints()

        }

        updateQuestion()

    }












//(New) Function I am Having problems with getting to output correctly
private fun showPoints() {


    val percentage = (numberCorrect.toDouble() / questionBank.size) * 100
    val percentageNew = "%.1f".format(percentage)


//Example: Answer everything correct and you will only get 83.3%
//(New) Will not take into consideration the IF statement, is ignoring the currentIndex
//(New) Is skipping to the second part of the condition, displaying red snackBar
    if (currentIndex == questionBank.size) {


//Will Change back to a toast after condition is ready
//        Toast.makeText(
//            this,
//            "$percentageNew%",
//            Toast.LENGTH_SHORT
//        )   //Showing the toast
//            .show()


//(New)When If statement is working will display a green Snackbar instead of red
     val snackBar = Snackbar.make (
         findViewById(android.R.id.content),
            "$percentageNew%",
            Snackbar.LENGTH_LONG
        )

        snackBar.setTextColor(Color.BLACK)
        snackBar.setBackgroundTint(Color.GREEN)
        snackBar.show()




//(New) when setting (questionCount == 6) everything displays as I would hope
//(New) This is increasing when you press the next button, displaying at the end, and resetting
    } else if (questionCount == 6)  {



//Will Change back to a toast after condition is ready
//        Toast.makeText(
//            this,
//            "$percentageNew%",
//            Toast.LENGTH_SHORT
//        )   //Showing the toast
//            .show()



//(New)Displaying red Snackbar immediately after the sixth question
        val snackBar = Snackbar.make (
            findViewById(android.R.id.content),
            "$percentageNew%",
            Snackbar.LENGTH_SHORT
        )

        snackBar.setTextColor(Color.BLACK)
        snackBar.setBackgroundTint(Color.RED)
        snackBar.show()


        numberCorrect = 0

//(New) This different variable is still resetting once you have completed
// the 6 different questions but for some reason is doing what I want it to
        questionCount = 0

    }
}









//Update question function
    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        binding.questionTextView.setText(questionTextResId)
    }












//Function to check if user answer is correct
    private fun checkAnswer(userAnswer: Boolean) {


        val correctAnswer = questionBank[currentIndex].answer


        val messageResId = if (userAnswer == correctAnswer)

        {
            R.string.correct_toast

        } else

            {
                R.string.incorrect_toast
            }


//(New) Added 9/19 to take into consideration left out question
    if (userAnswer == correctAnswer)


        numberCorrect++

        Toast.makeText(this, messageResId, Toast.LENGTH_LONG)
            .show()

    }































//Logs for the first exercise
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }


    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }



    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }



    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }



    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

}

