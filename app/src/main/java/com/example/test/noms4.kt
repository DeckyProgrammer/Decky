package com.example.test

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class noms4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_noms4)

        val button = findViewById<Button>(R.id.button_valider)
        button.setOnClickListener {
            val intent = Intent(this, Gameplay::class.java)
            startActivity(intent)
    }
}