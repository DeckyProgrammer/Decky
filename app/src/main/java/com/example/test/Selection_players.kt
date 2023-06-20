package com.example.test

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Selection_players : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selection_players)

        val button2p = findViewById<Button>(R.id.button_2p)
        button2p.setOnClickListener {
            val intent = Intent(this, noms2::class.java)
            startActivity(intent)
        }

        val button3p = findViewById<Button>(R.id.button_3p)
        button3p.setOnClickListener {
            val intent = Intent(this, noms3::class.java)
            startActivity(intent)
        }

         val button4p = findViewById<Button>(R.id.button_4p)
        button4p.setOnClickListener {
            val intent = Intent(this, noms4::class.java)
            startActivity(intent)
        }
/*
        val button5p = findViewById<Button>(R.id.button_5p)
        button5p.setOnClickListener {
            val intent = Intent(this, players5::class.java)
            startActivity(intent)
        }

        val button6p = findViewById<Button>(R.id.button_6p)
        button6p.setOnClickListener {
            val intent = Intent(this, players6::class.java)
            startActivity(intent)
        }

        */
    }
}