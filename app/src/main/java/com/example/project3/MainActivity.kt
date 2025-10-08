package com.example.project3

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        game = Game( this )

        val scoreTV = findViewById<TextView>(R.id.bestLevel)
        val bestLevel = game.getBestLevel()
        scoreTV.text = "Best Level: $bestLevel"

        val goButton = findViewById<Button>(R.id.playButton)
        goButton.setOnClickListener { play() }

    }

    override fun onStart() {
        super.onStart()
        val scoreTV = findViewById<TextView>(R.id.bestLevel)
        val bestLevel = game.getBestLevel()
        scoreTV.text = "Best Level: $bestLevel"
    }

    fun play() {
        val intent : Intent = Intent( this, GameActivity::class.java )
        startActivity( intent, ActivityOptions.makeSceneTransitionAnimation( this ).toBundle( ) )
    }

    companion object {
        lateinit var game : Game
    }
}