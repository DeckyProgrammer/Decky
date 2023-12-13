package com.example.test

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.Intent
import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.v7.app.AppCompatActivity
import android.transition.TransitionManager
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.util.UUID

class Gameplay : AppCompatActivity() {
    val i = 4
    val liste = arrayOf("","","","","","","","","","","","","","","","","","","")
    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gameplay)
        /* val myImage1 = findViewById<ImageView>(R.id.imageView1)
        val myImage2 = findViewById<ImageView>(R.id.imageView2)
        val myImage3 = findViewById<ImageView>(R.id.imageView3) */
        val imageViews = mutableListOf<ImageView>()
        val i = intent.getIntExtra("cle_i", 2)
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
                                v.foreground = resources.getDrawable(R.drawable.border_bleu, null)
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


        /* val buttonpass = findViewById<Button>(R.id.button_pass)
        buttonpass.setOnClickListener {
            val intent = Intent(this, annoncetour::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        } */

        val parentLayout = findViewById<ConstraintLayout>(R.id.layout_gameplay)
        val buttonjoue = findViewById<Button>(R.id.button_play)
        buttonjoue.setOnClickListener {
            val receivedData = connectToDeviceSend("24:6F:28:7B:4F:4E", selectedImageView) //remplacer selectedImageView par str position carte dans boite noire
            if (selectedImageView != null) {

                parentLayout.removeView(selectedImageView)
            } else {
                // Aucune image n'est sélectionnée
            }
        }
        val cartes: ArrayList<Int> = when (i) {
            0 -> arrayListOf(R.drawable.c1, R.drawable.c2, R.drawable.c3, R.drawable.c4, R.drawable.c5,
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
            1 -> arrayListOf(
                // Cartes bleues
                R.drawable.blue_0, R.drawable.blue_1, R.drawable.blue_2, R.drawable.blue_3, R.drawable.blue_4,
                R.drawable.blue_5, R.drawable.blue_6, R.drawable.blue_7, R.drawable.blue_8, R.drawable.blue_9,
                R.drawable.blue_reverse, R.drawable.blue_skip, R.drawable.blue_take2,

                // Cartes vertes
                R.drawable.green_0, R.drawable.green_1, R.drawable.green_2, R.drawable.green_3, R.drawable.green_4,
                R.drawable.green_5, R.drawable.green_6, R.drawable.green_7, R.drawable.green_8, R.drawable.green_9,
                R.drawable.green_reverse, R.drawable.green_skip, R.drawable.green_take2,

                // Cartes rouges
                R.drawable.red_0, R.drawable.red_1, R.drawable.red_2, R.drawable.red_3, R.drawable.red_4,
                R.drawable.red_5, R.drawable.red_6, R.drawable.red_7, R.drawable.red_8, R.drawable.red_9,
                R.drawable.red_reverse, R.drawable.red_skip, R.drawable.red_take2,

                // Cartes jaunes
                R.drawable.yellow_0, R.drawable.yellow_1, R.drawable.yellow_2, R.drawable.yellow_3, R.drawable.yellow_4,
                R.drawable.yellow_5, R.drawable.yellow_6, R.drawable.yellow_7, R.drawable.yellow_8, R.drawable.yellow_9,
                R.drawable.yellow_reverse, R.drawable.yellow_skip, R.drawable.yellow_take2, R.drawable.change_colour1, R.drawable.take_four1
                // Continuez avec d'autres types de cartes spéciales si nécessaire
            )
            else -> arrayListOf() // Liste par défaut si choixUtilisateur n'est ni 1 ni 2
        }
        if(i==2){val intent = Intent(this, noms5::class.java)
                 startActivity(intent)
                 overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);}

        val buttonpioche = findViewById<Button>(R.id.button_draw)
        buttonpioche.setOnClickListener {
            val imageViewCarte = ImageView(this)
            val receivedData = connectToDeviceReceive("24:6F:28:7B:4F:4E")
            //envoyer ensuite receivedData dans l'ia
            val randomIndex = (0 until cartes.size).random()
            val carteRandom = cartes[randomIndex]
            imageViewCarte.setImageResource(carteRandom) //remplacer carte random par carte renvoyée par IA

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

            // TransitionManager pour gérer l'apparition
            /* val constraintSet = ConstraintSet()
            constraintSet.clone(parentContainer)

            // Définissez les contraintes pour placer l'ImageView au centre
            constraintSet.centerHorizontally(imageViewCarte.id, ConstraintSet.PARENT_ID)
            constraintSet.centerVertically(imageViewCarte.id, ConstraintSet.PARENT_ID)

            // Appliquez la transition pour l'apparition en douceur
            TransitionManager.beginDelayedTransition(parentContainer)
            constraintSet.applyTo(parentContainer) */

            /* val marginLeftInDP = 150
            val marginTopInDP = 100

            val density = resources.displayMetrics.density
            val marginLeftInPixels = (marginLeftInDP * density).toInt()
            val marginTopInPixels = (marginTopInDP * density).toInt()

            layoutParams.leftMargin = marginLeftInPixels
            layoutParams.topMargin = marginTopInPixels

            imageViewCarte.layoutParams = layoutParams */

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

    private fun connectToDeviceSend(deviceAddress : String, cardToSend : String) : String{
        val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()

        // Vérifier si l'appareil supporte le Bluetooth
        if (bluetoothAdapter == null) {
            return "Bluetooth non supporté sur cet appareil"
        }

        // Vérifier si le Bluetooth est activé, sinon demander à l'utilisateur de l'activer
        if (!bluetoothAdapter.isEnabled) {
            return "Veuillez activer le Bluetooth"
        }

        // Rechercher le périphérique Bluetooth avec l'adresse spécifiée
        val remoteDevice: BluetoothDevice? = bluetoothAdapter.getRemoteDevice(deviceAddress)

        // Vérifier si le périphérique existe
        if (remoteDevice == null) {
            return "Périphérique Bluetooth introuvable"
        }

        val uuid: UUID = UUID.fromString("BA37C98E-943B-11EE-B9D1-0242AC120002") // UUID  pour le service SPP (Serial Port Profile)
        var socket: BluetoothSocket? = null
        var outputStream: OutputStream? = null

        try {
            // Créer un socket Bluetooth sécurisé
            socket = remoteDevice.createRfcommSocketToServiceRecord(uuid)

            // Connecter le socket
            socket.connect()

            // Obtenir les flux de sortie du socket
            outputStream = socket.outputStream

            // Envoyer des données au serveur Bluetooth
            outputStream.write(cardToSend.toByteArray())

            return "1"
        } catch (e: IOException) {
            e.printStackTrace()
            return "Erreur lors de la connexion ou de l'échange de données"
        } finally {
            try {
                // Fermer les flux et le socket après avoir terminé
                outputStream?.close()
                socket?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

    }


    private fun connectToDeviceReceive(deviceAddress: String): String {
        val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()

        // Vérifier si l'appareil supporte le Bluetooth
        if (bluetoothAdapter == null) {
            return "Bluetooth non supporté sur cet appareil"
        }

        // Vérifier si le Bluetooth est activé, sinon demander à l'utilisateur de l'activer
        if (!bluetoothAdapter.isEnabled) {
            return "Veuillez activer le Bluetooth"
        }

        // Rechercher le périphérique Bluetooth avec l'adresse spécifiée
        val remoteDevice: BluetoothDevice? = bluetoothAdapter.getRemoteDevice(deviceAddress)

        // Vérifier si le périphérique existe
        if (remoteDevice == null) {
            return "Périphérique Bluetooth introuvable"
        }

        val uuid: UUID = UUID.fromString("BA37C98E-943B-11EE-B9D1-0242AC120002") // UUID  pour le service SPP (Serial Port Profile)
        var socket: BluetoothSocket? = null
        var inputStream: InputStream? = null

        try {
            // Créer un socket Bluetooth sécurisé
            socket = remoteDevice.createRfcommSocketToServiceRecord(uuid)

            // Connecter le socket
            socket.connect()

            // Obtenir les flux d'entrée du socket
            inputStream = socket.inputStream


            // Lire les données provenant du serveur Bluetooth
            val buffer = ByteArray(1024)
            val bytesRead = inputStream.read(buffer)
            val receivedMessage = String(buffer, 0, bytesRead)

            // Retourner les données reçues du serveur Bluetooth
            return receivedMessage
        } catch (e: IOException) {
            e.printStackTrace()
            return "Erreur lors de la connexion ou de l'échange de données"
        } finally {
            try {
                // Fermer les flux et le socket après avoir terminé
                inputStream?.close()
                socket?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
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