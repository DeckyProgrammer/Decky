package com.example.test

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.widget.Button
import android.widget.ImageView

class Gameplay : AppCompatActivity() {
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gameplay)
        val myImage1 = findViewById<ImageView>(R.id.imageView1)
        val myImage2 = findViewById<ImageView>(R.id.imageView2)
        val myImage3 = findViewById<ImageView>(R.id.imageView3)

        /* myImage1.setOnTouchListener(object : OnTouchListener {
            var dX = 0f
            var dY = 0f
            var maxX = 0f
            var maxY = 0f // Les coordonnées maximales de l'écran
            override fun onTouch(v: View, event: MotionEvent): Boolean {
                val screenWidth = resources.displayMetrics.widthPixels
                val screenHeight = resources.displayMetrics.heightPixels
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        dX = v.x - event.rawX
                        dY = v.y - event.rawY
                    }

                    MotionEvent.ACTION_MOVE -> {
                        var newX = event.rawX + dX
                        var newY = event.rawY + dY

                        // Assurez-vous que l'ImageView ne dépasse pas les limites de l'écran
                        maxX = (screenWidth - v.width).toFloat()
                        maxY = (screenHeight - v.height).toFloat()
                        if (newX < 0) {
                            newX = 0f
                        } else if (newX > maxX) {
                            newX = maxX
                        }
                        if (newY < 0) {
                            newY = 0f
                        } else if (newY > maxY) {
                            newY = maxY
                        }
                        v.x = newX
                        v.y = newY
                    }
                }
                return true
            }
        }) */

          fun setupImageViewDrag(imageView: ImageView) {
            imageView.setOnTouchListener(object : View.OnTouchListener {
                var dX = 0f
                var dY = 0f
                var maxX = 0f
                var maxY = 0f

                override fun onTouch(v: View, event: MotionEvent): Boolean {
                    val screenWidth = resources.displayMetrics.widthPixels
                    val screenHeight = resources.displayMetrics.heightPixels

                    when (event.action) {
                        MotionEvent.ACTION_DOWN -> {
                            dX = v.x - event.rawX
                            dY = v.y - event.rawY
                        }
                        MotionEvent.ACTION_MOVE -> {
                            var newX = event.rawX + dX
                            var newY = event.rawY + dY

                            // Assurez-vous que l'ImageView ne dépasse pas les limites de l'écran
                            maxX = (screenWidth - v.width).toFloat()
                            maxY = (screenHeight - v.height).toFloat()

                            if (newX < 0) {
                                newX = 0f
                            } else if (newX > maxX) {
                                newX = maxX
                            }

                            if (newY < 0) {
                                newY = 0f
                            } else if (newY > maxY) {
                                newY = maxY
                            }
                            v.x = newX
                            v.y = newY
                        }
                    }
                    return true
                }
            })
        }

// Appliquez la fonction aux ImageView
        setupImageViewDrag(myImage1)
        setupImageViewDrag(myImage2)
        setupImageViewDrag(myImage3)
/*
        val imageViews = arrayOf(myImage1, myImage2,myImage3)

        var selectedImageView: ImageView? = null

        for (imageView in imageViews) {
            imageView.setOnClickListener {
                if (selectedImageView != null) {
                    // Désélectionnez l'ImageView précédemment sélectionnée
                    selectedImageView!!.isSelected = false
                    selectedImageView!!.background.clearColorFilter()
                }

                imageView.isSelected = true
                imageView.background.setColorFilter(resources.getColor(R.color.orange), PorterDuff.Mode.SRC_ATOP)
                selectedImageView = imageView
            }
        } */

        val buttonpass = findViewById<Button>(R.id.button_pass)
        buttonpass.setOnClickListener {
            val intent = Intent(this, annoncetour::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        }
    }
}