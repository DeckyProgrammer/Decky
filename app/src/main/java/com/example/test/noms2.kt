package com.example.test

import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.ImageView

class noms2 : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_noms2)
        val imageNormal = findViewById<ImageView>(R.id.imageButtonNormal)
        val imageUno = findViewById<ImageView>(R.id.imageButtonUno)
        val imageViews = arrayListOf(imageNormal,imageUno)
        var i=0
        fun setupImageViewDrag(imageView: ImageView) {
            imageView.setOnTouchListener(object : View.OnTouchListener {

                override fun onTouch(v: View, event: MotionEvent): Boolean {
                    when (event.action) {
                        MotionEvent.ACTION_UP -> {
                            // Gérez la sélection ici (par exemple, basculez l'état de sélection)
                            v.isSelected = !v.isSelected

                            // Changez la couleur de la bordure en fonction de l'état de sélection si nécessaire
                            if (v.isSelected) {
                                imageView.foreground = resources.getDrawable(R.drawable.border_bleu, null)
                            } else {
                                v.foreground = null
                            }
                            for (imageView in imageViews) {
                                if (imageView != v) {
                                    imageView.isSelected = false
                                    imageView.foreground = null
                                }
                            }
                            if(imageView == imageNormal){i=0}
                            if(imageView == imageUno){i=1}
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



        /* for (imageView in imageViews) {
            imageView.setOnClickListener {
                imageView.isSelected = !imageView.isSelected
                // Changez la couleur de la bordure en fonction de l'état de sélection si nécessaire
                if (imageView.isSelected) {
                    imageView.foreground = resources.getDrawable(R.drawable.border_bleu, null)
                } else {
                    imageView.foreground = null
                }
                for (imageViewZ in imageViews) {
                    if (imageViewZ != imageView) {
                        imageViewZ.isSelected = false
                        imageView.foreground = null
                    }
                }
            }
        } */

        val button = findViewById<Button>(R.id.button_valider)
        button.setOnClickListener {
            val j = i
            val intent = Intent(this, Gameplay::class.java)
            intent.putExtra("cle_i", j) // "cle_i" est une clé unique pour identifier la valeur
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        }
    }
}