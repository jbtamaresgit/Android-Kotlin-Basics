package com.example.testproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.testproject.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    /*private val LEMONADE_STATE = "LEMONADE_STATE"
    private val LEMON_SIZE = "LEMON_SIZE"
    private val SQUEEZE_COUNT = "SQUEEZE_COUNT"
    // SELECT represents the "pick lemon" state
    private val SELECT = "select"
    // SQUEEZE represents the "squeeze lemon" state
    private val SQUEEZE = "squeeze"
    // DRINK represents the "drink lemonade" state
    private val DRINK = "drink"
    // RESTART represents the state where the lemonade has be drunk and the glass is empty
    private val RESTART = "restart"
    // Default the state to select
    private var lemonadeState = "select"
    // Default lemonSize to -1
    private var lemonSize = -1
    // Default the squeezeCount to -1
    private var squeezeCount = -1

    private var lemonTree = LemonTree()
    private var lemonImage: ImageView? = null*/

    //lateinit initalise value before using it
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCalculate.setOnClickListener{
            calculateTip()
        }
        /*val rollButton: Button = findViewById(R.id.rollBtn)
        rollButton.setOnClickListener{
            val txtResult: TextView = findViewById(R.id.txtResultView)
            val resultRolledDice = rollDice()
            Toast.makeText(this, "Dice Rolled!", Toast.LENGTH_SHORT).show()
            txtResult.text = resultRolledDice
        }

        // Handles the saving of state of lemon
        if (savedInstanceState != null){
            lemonadeState = savedInstanceState.getString(LEMONADE_STATE, SELECT)
            lemonSize = savedInstanceState.getInt(LEMON_SIZE, -1)
            squeezeCount = savedInstanceState.getInt(SQUEEZE_COUNT, -1)
        }

        lemonImage = findViewById(R.id.lemonImageView)
        //Sets up view elements according to state
        setViewElements()
        lemonImage!!.setOnClickListener{
            clickLemonImage()
        }
        lemonImage!!.setOnLongClickListener{
            false
        }*/

    }

    private fun calculateTip() {
        val costOfService = binding.costOfServiceEditText.text.toString().toDoubleOrNull()
        val tipPercentage = when(binding.optionsRadioGroup.checkedRadioButtonId){
            R.id.option_twenty_percent -> 0.20
            R.id.option_fifteen_percent -> 0.15
            else -> 0.10
        }

        if(costOfService == null)
        {
            binding.txtTipAmount.text = ""
            return
        }


        var tip = tipPercentage * costOfService
        val isRoundUp = binding.roundUpSwitch.isChecked
        if(isRoundUp){
            tip = kotlin.math.ceil(tip)
        }

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.txtTipAmount.text = getString(R.string.tip_amount, formattedTip)

    }

/*
    private fun setViewElements() {

        var textLemonStateView : TextView = findViewById(R.id.txtLemonStateView)
        var lemonImageView : ImageView = findViewById(R.id.lemonImageView)
        var drawableLemonImageSource : Int = R.drawable.lemon_tree
        var drawableLemonStateSource : Int = R.string.selectLemonName
        var squeezeString : String = ""
        when(lemonadeState){
            SELECT -> {
                drawableLemonStateSource = R.string.selectLemonName
                drawableLemonImageSource = R.drawable.lemon_tree
            }
            SQUEEZE -> {
                squeezeString = getString(R.string.squeezeLemonName, lemonSize.toString())
                drawableLemonImageSource = R.drawable.lemon_squeeze
            }
            DRINK -> {
                drawableLemonStateSource = R.string.drinkLemonName
                drawableLemonImageSource = R.drawable.lemon_drink
            }
            RESTART -> {
                drawableLemonStateSource = R.string.restartLemonName
                drawableLemonImageSource = R.drawable.lemon_restart
            }
        }
        if(lemonadeState != SQUEEZE){
            textLemonStateView.setText(drawableLemonStateSource)
        }
        else{
            textLemonStateView.text = squeezeString
        }
        lemonImageView.setImageResource(drawableLemonImageSource)
    }

    private fun clickLemonImage() {
        val lemonTree = LemonTree()
        when(lemonadeState){
            SELECT -> {
                lemonadeState = SQUEEZE
                lemonSize = lemonTree.pick()
                squeezeCount = 0
            }
            SQUEEZE -> {
                squeezeCount++
                lemonSize--

                if(lemonSize == 0) {
                    lemonadeState = DRINK
                }
            }
            DRINK -> {
                lemonadeState = RESTART
                lemonSize = -1
            }
            RESTART -> {
                lemonadeState = SELECT
            }
        }

        setViewElements()
    }


    private fun rollDice() : String {
       val dice = Dice(6)
       val rollResult = dice.roll()
       val luckyNumber = 4
       val diceImage: ImageView = findViewById(R.id.diceImageView)
       var drawableDiceResource : Int = R.drawable.dice_1
       var resultString = "1"
       when(rollResult) {
           luckyNumber -> {
               resultString = "You win!"
               drawableDiceResource = R.drawable.dice_4
           }
           1 -> {
               resultString = "You drew 1. Try again"
               drawableDiceResource = R.drawable.dice_1
           }
           2 -> {
               resultString = "You drew 2. Better luck next time"
               drawableDiceResource = R.drawable.dice_2
           }
           3 -> {
               resultString = "You drew 3. So close yet so far"
               drawableDiceResource = R.drawable.dice_3
           }
           5 -> {
               resultString = "You drew 5. Close."
               drawableDiceResource = R.drawable.dice_5
           }
           6 -> {
               resultString = "You drew 6. Is it over?"
               drawableDiceResource = R.drawable.dice_6
           }
       }

       diceImage.setImageResource(drawableDiceResource)
       diceImage.contentDescription = rollResult.toString()
       return resultString
    }

 */
}

/*
class LemonTree{
    fun pick() : Int{
        return(2..4).random()
    }
}


class Dice(private val numSides: Int){
    fun roll(): Int{
        return (1..numSides).random()
    }
}
 */