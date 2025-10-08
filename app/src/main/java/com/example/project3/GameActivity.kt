package com.example.project3

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_game)

        var redButton = findViewById<Button>(R.id.redButton)
        redButton.setOnClickListener { v -> checkInput("red") }

        var greenButton = findViewById<Button>(R.id.greenButton)
        greenButton.setOnClickListener { v -> checkInput("green") }

        var yellowButton = findViewById<Button>(R.id.yellowButton)
        yellowButton.setOnClickListener { v -> checkInput("yellow") }

        var blueButton = findViewById<Button>(R.id.blueButton)
        blueButton.setOnClickListener { v -> checkInput("blue") }

        var resetButton = findViewById<Button>(R.id.resetButton)
        resetButton.setOnClickListener { v -> resetHandler() }

        startGame()
    }

    fun startGame () {
        MainActivity.game.addNewColor()
        val message = MainActivity.game.getTargetSeq().joinToString(", ")
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()

    }

    fun resetHandler () {
        MainActivity.game.resetGame()
        MainActivity.game.setPreferences( this )
        goBack()
    }

    fun checkInput (color : String) {
        val result = MainActivity.game.addToSequence(color)
        when (result) {
            "correct" -> {
                Log.w("ActivityMain", "input correct")
            }
            "complete" -> {
                val message = MainActivity.game.getTargetSeq().joinToString(", ")
                Toast.makeText(this, message, Toast.LENGTH_LONG).show()
                Log.w("ActivityMain", "input complete")
            }
            "wrong" -> {
                Log.w("ActivityMain", "input wrong")
                goBack()
            }
            else -> {
                Log.w("ActivityMain", "unexpected result in checkInput")
            }
        }
    }

    fun goBack( ) {
        MainActivity.game.setPreferences( this )
        finishAfterTransition()
    }
}