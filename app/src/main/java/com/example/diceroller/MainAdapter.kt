package com.example.diceroller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_dice_history.view.*

class MainAdapter(var diceHistoryList: MutableList<Int>) : RecyclerView.Adapter<MainAdapter.MainViewHolder>(){

    class MainViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
       var view =  LayoutInflater.from(parent.context).inflate(R.layout.list_dice_history, parent, false)
        return MainViewHolder(view)
    }

    override fun getItemCount(): Int {
        return diceHistoryList.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val curDice = diceHistoryList[position]
        val curDiceImage = when(curDice) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        holder.itemView.dice_image.setImageResource(curDiceImage)
    }
}