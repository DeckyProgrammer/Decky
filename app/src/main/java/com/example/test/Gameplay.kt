package com.example.test

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.constraint.ConstraintLayout
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout

class Gameplay : AppCompatActivity() {
    val i = 4

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gameplay)
        val myImage1 = findViewById<ImageView>(R.id.imageView1)
        val myImage2 = findViewById<ImageView>(R.id.imageView2)
        val myImage3 = findViewById<ImageView>(R.id.imageView3)
        val imageViews = arrayListOf(myImage1, myImage2, myImage3)
        var selectedImageView: ImageView? = null


        fun setupImageViewDrag(imageView: ImageView) {
            imageView.setOnTouchListener(object : View.OnTouchListener {
                var dX = 0f
                var dY = 0f
                var maxX = 0f
                var maxY = 0f
                //val minAllowedY = 170f
                //val maxAllowedY = 0.85f


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


                            if (newY < 200f) {
                                newY = 200f
                            } else if (newY > maxY - 350f) {
                                newY = maxY - 350f
                            }
                            v.x = newX
                            v.y = newY
                        }

                        MotionEvent.ACTION_UP -> {
                            // Gérez la sélection ici (par exemple, basculez l'état de sélection)
                            v.isSelected = !v.isSelected

                            // Changez la couleur de la bordure en fonction de l'état de sélection si nécessaire
                            if (v.isSelected) {
                                v.foreground = resources.getDrawable(R.drawable.border_orange, null)
                            } else {
                                v.foreground = null
                            }
                            for (imageView in imageViews) {
                                if (imageView != v) {
                                    imageView.isSelected = false
                                    imageView.foreground = null
                                }
                            }
                            selectedImageView = imageView
                        }
                    }
                    return true
                }
            })
        }

// Appliquez la fonction aux ImageView
        for (imageView in imageViews) {
            setupImageViewDrag(imageView)
        }


        val buttonpass = findViewById<Button>(R.id.button_pass)
        buttonpass.setOnClickListener {
            val intent = Intent(this, annoncetour::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }

        val parentLayout = findViewById<ConstraintLayout>(R.id.layout_gameplay)
        val buttonjoue = findViewById<Button>(R.id.button_play)
        buttonjoue.setOnClickListener {

            if (selectedImageView != null) {
                parentLayout.removeView(selectedImageView)
            } else {
                // Aucune image n'est sélectionnée
            }
        }



        val buttonpioche = findViewById<Button>(R.id.button_draw)
        buttonpioche.setOnClickListener {
            val cartes = arrayOf(
                R.drawable.c1, R.drawable.c2, R.drawable.c3, R.drawable.c4, R.drawable.c5,
                R.drawable.c6, R.drawable.c7, R.drawable.c8, R.drawable.c9, R.drawable.c10,
                R.drawable.c11, R.drawable.c12, R.drawable.c13,

                R.drawable.d1, R.drawable.d2, R.drawable.d3, R.drawable.d4, R.drawable.d5,
                R.drawable.d6, R.drawable.d7, R.drawable.d8, R.drawable.d9, R.drawable.d10,
                R.drawable.d11, R.drawable.d12, R.drawable.d13,

                R.drawable.h1, R.drawable.h2, R.drawable.h3, R.drawable.h4, R.drawable.h5,
                R.drawable.h6, R.drawable.h7, R.drawable.h8, R.drawable.h9, R.drawable.h10,
                R.drawable.h11, R.drawable.h12, R.drawable.h13,

                R.drawable.s1, R.drawable.s2, R.drawable.s3, R.drawable.s4, R.drawable.s5,
                R.drawable.s6, R.drawable.s7, R.drawable.s8, R.drawable.s9, R.drawable.s10,
                R.drawable.s11, R.drawable.s12, R.drawable.s13,
                R.drawable.j1, R.drawable.j2
            )

            val randomIndex = (0 until cartes.size).random()
            val carteRandom = cartes[randomIndex]
            val imageViewCarte = ImageView(this)
            imageViewCarte.setImageResource(carteRandom)
            val parentContainer = findViewById<ConstraintLayout>(R.id.layout_gameplay) // Assurez-vous que l'ID correspond

            val layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            )

            // Ajoutez l'ImageView au ConstraintLayout
            parentContainer.addView(imageViewCarte)

            val imageViewLayoutParams = ConstraintLayout.LayoutParams(
                dpToPx(125), // Largeur en pixels
                dpToPx(181)  // Hauteur en pixels
            )

            val marginLeftInDP = 150
            val marginTopInDP = 100

            val density = resources.displayMetrics.density
            val marginLeftInPixels = (marginLeftInDP * density).toInt()
            val marginTopInPixels = (marginTopInDP * density).toInt()

            layoutParams.leftMargin = marginLeftInPixels
            layoutParams.topMargin = marginTopInPixels

            imageViewCarte.layoutParams = layoutParams

            imageViewCarte.layoutParams = imageViewLayoutParams
            imageViewCarte.scaleType = ImageView.ScaleType.FIT_XY

            imageViews.add(imageViewCarte)
            // Rendez l'ImageView de la carte piochée déplaçable
            setupImageViewDrag(imageViewCarte)

        }
    }
    private fun dpToPx(dp: Int): Int {
        val scale = resources.displayMetrics.density
        return (dp * scale + 0.5f).toInt()
    }
}
/* val imageViews = arrayOf(myImage1, myImage2, myImage3)

        for (imageView in imageViews) {
            imageView.setOnLongClickListener {
                imageView.isSelected = !imageView.isSelected
                // Changez la couleur de la bordure en fonction de l'état de sélection si nécessaire
                if (imageView.isSelected) {
                    imageView.foreground = resources.getDrawable(R.drawable.border_orange, null)
                } else {
                    imageView.foreground = null
                }
                true
            }
        }          */

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