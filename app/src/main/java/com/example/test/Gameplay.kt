package com.example.test

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.app.Activity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.Toast
import android.view.GestureDetector
import android.widget.Button

class Gameplay : AppCompatActivity() {
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gameplay)
        val myImage = findViewById<ImageView>(R.id.imageView)
        val gestureDetector = GestureDetector(this, object : GestureDetector.SimpleOnGestureListener() {
            override fun onScroll(
                e1: MotionEvent,
                e2: MotionEvent,
                distanceX: Float,
                distanceY: Float
            ): Boolean {
                // Manipulez les propriétés de l'image en fonction des mouvements de défilement
                myImage.x -= distanceX
                myImage.y -= distanceY
                return true
            }
        })
        myImage.setOnTouchListener { _, event ->
            gestureDetector.onTouchEvent(event)
            true
        }
        val buttonpass = findViewById<Button>(R.id.button_pass)
        buttonpass.setOnClickListener {
            val intent = Intent(this, annoncetour::class.java)
            startActivity(intent)
        }
    }
}