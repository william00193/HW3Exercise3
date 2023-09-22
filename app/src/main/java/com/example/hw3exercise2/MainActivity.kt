package com.example.hw3exercise2

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.hw3exercise2.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar


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



//// (New) added a new variable to substitute for currentIndex
// New variable addition seems to be making the condition work for some reason
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
            //deleted updateQuestion listener
        }




        binding.previousButton.setOnClickListener {
            currentIndex = (currentIndex - 1) % questionBank.size
            //deleted updateQuestion listener
        }




        binding.nextButton.setOnClickListener {

            currentIndex = (currentIndex + 1) % questionBank.size

            binding.falseButton.isEnabled = true
            binding.trueButton.isEnabled = true


//(New) variable Increment of questionCount,
            questionCount++

            updateQuestion()


        }

        updateQuestion()

    }












//(New) I have now moved the condition to the updateQuestion function
//Things seem to be working correctly now, thanks for the help professor!
private fun showPoints() {

    val percentage = (numberCorrect.toDouble() / questionBank.size) * 100
    val percentageNew = "%.1f".format(percentage)



//(New) I will change this out when it comes time to turn things In
//There is to long of a delay when testing
//        Toast.makeText(
//            this,
//            "$percentageNew%",
//            Toast.LENGTH_SHORT
//        )   //Showing the toast
//            .show()
//
//(New)When If statement is working will display a green Snackbar
     val snackBar = Snackbar.make (
         findViewById(android.R.id.content),
            "$percentageNew%",
            Snackbar.LENGTH_SHORT
        )

        snackBar.setTextColor(Color.BLACK)
        snackBar.setBackgroundTint(Color.GREEN)
        snackBar.show()

}









//Update question function
    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        binding.questionTextView.setText(questionTextResId)


   if (questionCount == questionBank.size) {

       showPoints()
       numberCorrect = 0

       questionCount = 0

   }  else  {


        }

    }












//Function to check if user answer is correct
    private fun checkAnswer(userAnswer: Boolean) {


    val correctAnswer = questionBank[currentIndex].answer


    val messageResId = if (userAnswer == correctAnswer) {
        R.string.correct_toast

    } else {
        R.string.incorrect_toast
    }


//(New) Added 9/19 to take into consideration left out question
    if (userAnswer == correctAnswer)


        numberCorrect++



 Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
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

