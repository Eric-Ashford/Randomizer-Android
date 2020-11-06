package com.ashford.randomizer

import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import java.lang.Thread.sleep

/**
 * This activity allows the user to roll a dice and view the result
 * on the screen.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val animDrawable = appBackground.background as AnimationDrawable
        animDrawable.setEnterFadeDuration(10)
        animDrawable.setExitFadeDuration(5000)
        animDrawable.start()

        val rollButton: Button = findViewById(R.id.button)
        rollButton.setOnClickListener{
            rollDice()
        }

        Timber.i("onCreate Called")
    }

    override fun onPause() {
        val animDrawable = appBackground.background as AnimationDrawable
        super.onPause()
        animDrawable.setVisible(false,false)
        Timber.i("onPause Called")
    }

    override fun onResume(){
        val animDrawable = appBackground.background as AnimationDrawable
        super.onResume()
        animDrawable.setVisible(true,true)
        Timber.i("onResume Called")
    }

    override fun onStop() {
        super.onStop()
        Timber.i("onStop Called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.i("onDestroy Called")
    }

    override fun onRestart() {
        super.onRestart()
        Timber.i("onRestart Called")
    }


    /**
     * Roll the dice and update the screen with the result.
     */
    private fun rollDice() {
        // Create new Dice object with 6 sides and roll it
        val dice = Dice(6)
        val diceRoll = dice.roll()

        // Update the screen with the dice roll
        val diceImage1: ImageView = findViewById(R.id.imageView)
        val resultRoll1: TextView = findViewById(R.id.rollNum)
        resultRoll1.text = dice.roll().toString()
        val tempResult = resultRoll1.text
        diceImage1.setImageResource(dice.returnImage(tempResult.toString()))

        val diceImage2: ImageView = findViewById(R.id.imageView2)
        val resultRoll2: TextView = findViewById(R.id.rollNum2)
        resultRoll2.text = dice.roll().toString()
        val tempResult2 = resultRoll2.text
        diceImage2.setImageResource(dice.returnImage(tempResult2.toString()))

        val diceRollTotal:TextView = findViewById(R.id.rollTotal)
        diceRollTotal.text = addTotal(tempResult.toString(),tempResult2.toString()).toString()


        Timber.i("Dice Rolled")
    }

}

class Dice(private val numSides: Int){
    fun roll():Int{
        return (1..this.numSides).random()
    }

    fun returnImage(num:String):Int{
        when(num){
            "1"->return R.drawable.dice_1
            "2"->return R.drawable.dice_2
            "3"->return R.drawable.dice_3
            "4"->return R.drawable.dice_4
            "5"->return R.drawable.dice_5
            "6"->return R.drawable.dice_6
            else-> return R.drawable.dice_1
        }
    }
}

fun addTotal(resultRoll1:String, resultRoll2:String):Int{
    return resultRoll1.toInt() + resultRoll2.toInt()
}


/*Coin Flip Logic
 */
class Coin() {
    fun flip(): Boolean {
        val flipResults = (1..2).random()
        var isHeads: Boolean = false
        when (flipResults) {
            1 -> {
                isHeads = true
                println("Heads")
            }
            2 -> {
                isHeads = false
                println("Tails")
            }
        }
        return isHeads
    }
}