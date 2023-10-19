package com.example.test

import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
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
        for (imageView in imageViews) {
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
        }

        val button = findViewById<Button>(R.id.button_valider)
        button.setOnClickListener {
            val intent = Intent(this, Gameplay::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        }
    }
}