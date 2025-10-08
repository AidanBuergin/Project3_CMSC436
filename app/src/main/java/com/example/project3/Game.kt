package com.example.project3

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class Game {

    private var bestLevel : Int = 0
    private var currentLevel : Int = 0
    private var targetSeq : ArrayList<String> = arrayListOf<String>()
    private var currentSeq : ArrayList<String> = arrayListOf<String>()

    constructor( context : Context) {
        var sp : SharedPreferences = context.getSharedPreferences( context.packageName + "_preferences", Context.MODE_PRIVATE )
        setBestLevel( sp.getInt( BEST_LEVEL, 0 ) )
        setCurrentLevel( sp.getInt( CURRENT_LEVEL, 0 ) )

    }

    private fun setBestLevel (level : Int) {
        bestLevel = level
    }

    private fun setCurrentLevel (level : Int) {
        currentLevel = level
    }

    fun getBestLevel () : Int {
        return bestLevel
    }

    fun getTargetSeq () : ArrayList<String> {
        return targetSeq
    }

    fun addNewColor () {
        val randNumber = (0..3).random()
        val colorName = when (randNumber) {
            0 -> "red"
            1 -> "green"
            2 -> "yellow"
            3 -> "blue"
            else -> "error"
        }
        targetSeq.add(colorName)
    }

    fun nextLevel () {
        currentLevel += 1
        if(currentLevel > bestLevel){
            bestLevel = currentLevel
        }
        currentSeq = arrayListOf<String>()
        addNewColor()
    }

    fun resetGame () {
        currentLevel = 0
        bestLevel = 0
        targetSeq = arrayListOf<String>()
        currentSeq = arrayListOf<String>()
    }

    fun addToSequence(color : String) : String {

        currentSeq.add(color)

        if(currentSeq.size == targetSeq.size){
            if (currentSeq == targetSeq){
                nextLevel()
                return "complete"
            }
            else {
                currentSeq = arrayListOf<String>()
                currentLevel = 0
                return "wrong"
            }
        }

        for (i in currentSeq.indices){
            if (currentSeq[i] != targetSeq[i]){
                currentSeq = arrayListOf<String>()
                currentLevel = 0
                return "wrong"
            }
        }

        return "correct"
    }

    fun setPreferences (context : Context) {
        val sp : SharedPreferences = context.getSharedPreferences( context.packageName + "_preferences", Context.MODE_PRIVATE )
        sp.edit {
            putInt(BEST_LEVEL, bestLevel)
            putInt(CURRENT_LEVEL, currentLevel)
        }

    }

    companion object {
        const val BEST_LEVEL : String = "bestLevel"
        const val CURRENT_LEVEL : String = "currentLevel"
    }


}