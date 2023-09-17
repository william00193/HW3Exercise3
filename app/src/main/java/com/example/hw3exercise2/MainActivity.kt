package com.example.hw3exercise2

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.hw3exercise2.databinding.ActivityMainBinding

import com.google.android.material.snackbar.Snackbar
import java.lang.Math.round
import java.math.RoundingMode
import kotlin.math.roundToInt


//Property that the log function takes, called a TAG
//filters down verbose logging
//Keeps function call cleaner
private const val TAG = "MainActivity"


class MainActivity : AppCompatActivity() {



//creating a variable for the binding
    private lateinit var binding: ActivityMainBinding



//Question bank
    private val questionBank = listOf(

        question(R.string.question_australia, true),
            question(R.string.question_oceans, true),
             question(R.string.question_mideast, false),
                 question(R.string.question_africa, false),
                     question(R.string.question_americas, true),
                        question(R.string.question_asia, true),

    )



// (New) hardcoded values for CurrentIndex and NumberCorrect
//This part seems to be working
    private var currentIndex = 0
    private var numberCorrect = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


//First Log for on create action
            Log.d(TAG, "onCreate (Bundle?) called")



            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)




//My on onclick listener for the true button, and coding it with 'True!'
        binding.trueButton.setOnClickListener {

            checkAnswer(true)

//Turing off both the true and false buttons once true is selected
            binding.trueButton.isEnabled = !(binding.trueButton.isEnabled)
            binding.falseButton.isEnabled = !(binding.falseButton.isEnabled)

// (New) Showing calculation function based on Pressing True Button
//This part seems to be working
            showPoints()
        }



//My on onclick listener for the false button, and coding it with 'False!'
        binding.falseButton.setOnClickListener {

            checkAnswer(false)

//Turing off both the true and false buttons once false is selected
            binding.falseButton.isEnabled = !(binding.falseButton.isEnabled)
            binding.trueButton.isEnabled = !(binding.trueButton.isEnabled)


// (New) Showing calculation function based on Pressing False Button
//This part seems to be working
            showPoints()
        }



//Finding questionTextView through binding
//Moving to next and previous questions on click
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

            updateQuestion()

            binding.falseButton.isEnabled = true

            binding.trueButton.isEnabled = true

// (New) Showing calculation function based on Pressing Next Button
//This part seems to be working
            showPoints()
        }

        updateQuestion()
    }








// (New) Will not pick up on the first part of the IF statement
//Only displaying percentage as a result of the Else If section and
//Not picking up on the last question
private fun showPoints() {


// (New) Taking count of Number Correct turning it into a double, dividing, and multiplying by 100
    val percentage = (numberCorrect.toDouble() / questionBank.size) * 100

// (New) Turing 'percentage' into single decimal format
    val percentageNew = "%.1f".format(percentage)




// (New) Comparing current index with total question bank size
// (Question) What will not output (Will not pick up and display once condition here is met)
//Example: Answer everything correct and you will only get 83.3%
    if (currentIndex == questionBank.size) {


// (New) Displaying toast if condition is met
// (Question) What will not output (Will not pick up and display once condition here is met)
        Toast.makeText(
            this,
            "$percentageNew%",
            Toast.LENGTH_SHORT
        )   //Showing the toast
            .show()




// (New) Displaying toast if condition is not met, or is equal to a set value
    } else if (currentIndex == 5)  {
        Toast.makeText(
            this,
            "$percentageNew%",
            Toast.LENGTH_SHORT
        )   //Showing the toast
            .show()


// (New) Resetting number correct to 0, once currentIndex is at Maximum
//This part seems to be working
        numberCorrect = 0

    }

}






//Update question function
    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        binding.questionTextView.setText(questionTextResId)
    }




//Function to check if user answer is correct
    private fun checkAnswer(userAnswer: Boolean) {


//Setting correct answer to what is in question bank
        val correctAnswer = questionBank[currentIndex].answer



//Start of condition
        val messageResId = if (userAnswer == correctAnswer) {

// (New) Incrementing Number correct
//This part seems to be working
            numberCorrect++

            R.string.correct_toast

        } else

            {
                R.string.incorrect_toast

            }


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

