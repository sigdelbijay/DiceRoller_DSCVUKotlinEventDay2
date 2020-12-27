package com.example.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //initialize diceHistory and adapter
    private val diceHistoryList : MutableList<Int> = mutableListOf()
    private val adapter = MainAdapter(diceHistoryList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //button and click functionality
        val rollButton: Button = findViewById(R.id.button)
        rollButton.setOnClickListener{ rollDice() }
        rollDice()

        //recycler view setting
        //val recyDiceHistory: RecyclerView = findViewById(R.id.recyDiceHistory)
        recyDiceHistory.layoutManager = LinearLayoutManager(this)
        recyDiceHistory.adapter = adapter
    }


    //roll dice function
    private fun rollDice() {
        val dice = Dice(6)
        val diceRoll = dice.roll()
        val diceImage: ImageView = findViewById(R.id.imageView)
        val drawableResource = when(diceRoll) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        diceImage.setImageResource(drawableResource)

        //update dice history
        diceHistoryList.add(diceRoll)

        //notify adapter
        adapter.notifyDataSetChanged()
    }


}


class Dice(val numSides: Int) {
    fun roll(): Int {
        return (1..numSides).random()
    }
}