package com.example.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //initialise mutable diceHistoryList and adapter *For implementing RecyclerView*
    private val diceHistoryList : MutableList<Int> = mutableListOf()
    private val adapter = MainAdapter(diceHistoryList)

    //onCreate is a lifecycle callback. When MainActivity starts, it is the first lifecycle method called
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //button and click functionality
        val rollButton: Button = findViewById(R.id.button)
        rollButton.setOnClickListener{ rollDice() }
        rollDice()

        //recycler view setting *For implementing RecyclerView*
        //val recyDiceHistory: RecyclerView = findViewById(R.id.recyDiceHistory) //old way of referencing views/viewgroups in XML layout
        recyDiceHistory.layoutManager = LinearLayoutManager(this)
        recyDiceHistory.adapter = adapter
    }


    //roll dice function
    private fun rollDice() {
        val dice = Dice(6)
        val diceRoll = dice.roll()
        val diceImage: ImageView = findViewById(R.id.imageView)

        //selects image from res/drawable folder based on the dice rolled from 1..6
        val drawableResource = when(diceRoll) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        diceImage.setImageResource(drawableResource)

        //update dice diceHistoryList *For implementing RecyclerView*
        diceHistoryList.add(diceRoll)

        //notify adapter about data being added to diceHistoryList *For implementing RecyclerView*
        adapter.notifyDataSetChanged()
    }
}

//dice class that has roll function which returns random number between 1 and 6
class Dice(val numSides: Int) {
    fun roll(): Int {
        return (1..numSides).random()
    }
}