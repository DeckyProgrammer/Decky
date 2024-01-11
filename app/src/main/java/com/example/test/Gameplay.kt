package com.example.test

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.constraint.ConstraintLayout
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import interpreter
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.util.UUID
import android.graphics.Bitmap
import android.graphics.BitmapFactory

class Gameplay : AppCompatActivity() {
    val i = 4
    val nbEmplacements = 8
    var cartesMain = mutableListOf<String>("","","","","","","","")
    val ordreCartes = arrayOf("c1", "d1", "h1", "s1", "c8", "d8", "h8", "s8", "c5", "d5", "h5", "s5", "c4", "d4", "h4", "s4", "c11", "d11", "h11", "s11", "c13", "d13", "h13", "s13", "c9", "d9", "h9", "s9", "c12", "d12", "h12", "s12", "c7", "d7", "h7", "s7", "c6", "d6", "h6", "s6", "c10", "d10", "h10", "s10", "c3", "d3", "h3", "s3", "c2", "d2", "h2", "s2")
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


        /* val buttonpass = findViewById<Button>(R.id.button_pass)
        buttonpass.setOnClickListener {
            val intent = Intent(this, annoncetour::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        } */

        val parentLayout = findViewById<ConstraintLayout>(R.id.layout_gameplay)
        val buttonjoue = findViewById<Button>(R.id.button_play)
        buttonjoue.setOnClickListener {

            var positionCardToSend = -1
            for (j in 0..<nbEmplacements){
                if(cartesMain[j] == selectedImageView?.tag?.toString() ){
                    positionCardToSend = j
                }
            }
            val receivedData = connectToDeviceJoue("3C:71:BF:6F:30:94", "j_$positionCardToSend") //remplacer selectedImageView par str position carte dans boite noire
            cartesMain[positionCardToSend]=""
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
            var carte =""
            var indexCarte = 0
            var availablePosition = -1
            for (j in 0..<nbEmplacements){
                if(cartesMain[j].isEmpty()){
                    availablePosition = j
                }
            }
            //val receivedData = connectToDevicePioche("24:6F:28:7B:4F:4E","SEND_DATA")
            val resourceId = R.drawable.testia // R.drawable.testia fait référence à votre image dans le dossier drawable

            val bitmap: Bitmap = BitmapFactory.decodeResource(applicationContext.resources, resourceId)
            var max = 0
            var probaCarte = arrayOfNulls<Float>(53).toMutableList()
            val interpreter = interpreter()
            //interpreter.inputs["input_1"]=receivedData
            interpreter.inputs["input_1"]=bitmap
            interpreter.launch()
            probaCarte = interpreter.outputs["output_1"] as MutableList<Float?>

            //envoyer image dans IA
            //récupérer  les probas

            for  (j in 0..53){
                if(probaCarte[max]!! < probaCarte[j]!!){
                    max = j
                }
            }


            //envoyer ensuite receivedData dans l'ia
            //carte = getCardName(ordreCartes,receivedData)

            cartesMain[availablePosition] = ordreCartes[max]

            if(carte.contains("c")== true){
                indexCarte = carte.substringAfter("c").toInt()
            }
            else if(carte.contains("d")== true) {
                indexCarte = carte.substringAfter("d").toInt() + 13
            }
            else if(carte.contains("h")== true) {
                indexCarte = carte.substringAfter("h").toInt() + 26
            }
            else if(carte.contains("s")== true) {
                indexCarte = carte.substringAfter("s").toInt() + 39
            }
            else if(carte.contains("j")== true) {
                indexCarte = carte.substringAfter("j").toInt() + 52
            }
            /*val randomIndex = (0 until cartes.size).random()
            val carteRandom = cartes[randomIndex]
            imageViewCarte.setImageResource(carteRandom) */
            //remplacer carte random par carte renvoyée par IA
            imageViewCarte.setImageResource(cartes[indexCarte])
            imageViewCarte.tag = imageViewCarte.drawable

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

    private fun connectToDeviceJoue(deviceAddress : String, positionCardToSend : String) : String{
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
            outputStream.write(positionCardToSend.toByteArray())

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


    private fun connectToDevicePioche(deviceAddress: String, cardToPioche: String): String {
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
        var inputStream: InputStream? = null

        try {
            // Créer un socket Bluetooth sécurisé
            socket = remoteDevice.createRfcommSocketToServiceRecord(uuid)

            // Connecter le socket
            socket.connect()

            // Obtenir les flux d'entrée et de sortie du socket
            outputStream = socket.outputStream
            inputStream = socket.inputStream

            // Envoyer des données au serveur Bluetooth
            outputStream.write(cardToPioche.toByteArray())

            val TIMEOUT = 5000 // Timeout en millisecondes
            val startTime = System.currentTimeMillis()

            // Attendre la réponse
            while (System.currentTimeMillis() - startTime < TIMEOUT) {
                if (inputStream.available() > 0) {
                    val buffer = ByteArray(1024)
                    val bytesRead = inputStream.read(buffer)
                    val receivedMessage = String(buffer, 0, bytesRead)

                    // Fermer les flux et le socket après avoir reçu la réponse
                    outputStream?.close()
                    inputStream?.close()
                    socket?.close()

                    // Retourner les données reçues du serveur Bluetooth
                    return receivedMessage
                }
            }

            // Timeout atteint sans réponse
            return "Aucune réponse reçue après le délai imparti"
        } catch (e: IOException) {
            e.printStackTrace()
            return "Erreur lors de la connexion ou de l'échange de données"
        } finally {
            try {
                // Fermer les flux et le socket après avoir terminé
                outputStream?.close()
                inputStream?.close()
                socket?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun getCardName (ordreCartes : Array<String>,receivedData: String): String {
        var max = 0
        var probaCarte = arrayOfNulls<Float>(53).toMutableList()
        //convertir str en image
        var picture = pictureFromString(receivedData)
        val interpreter = interpreter()
        interpreter.inputs["input_1"]=picture
        interpreter.launch()
        probaCarte = interpreter.outputs["output_1"] as MutableList<Float?>

        //envoyer image dans IA
        //récupérer  les probas

        for  (j in 0..53){
            if(probaCarte[max]!! < probaCarte[j]!!){
                max = j
            }
        }

        return ordreCartes[max]

    }
    private fun pictureFromString (receivedData: String): Array<Array<IntArray>> {
        val height = 80
        val width = 60
        val imageRgb = Array(height) { Array(width) { IntArray(3) { 0 } } }
        val pictureData = receivedData.trimIndent()

        var index = 0
        var reading = true

        while (reading && index < pictureData.lines().size) {
            val start = pictureData.lines()[index]
            index++

            if (start.substring(2) == "start") {
                val number = getNumber(start) + "_loading_image"
                var reading2 = true

                while (reading2 && index < pictureData.lines().size) {
                    for (i in 0 until width) {
                        val ligne = pictureData.lines()[index]
                        index++
                        var point = 0
                        var test = 0

                        for (j in 0 until height) {
                            test++
                            val (color, newPoint) = getRGB(ligne, point)
                            point = newPoint
                            imageRgb[j][i] = multipleList(separateRGB(color))
                        }
                    }

                    // Assume Image.fromArray est une fonction appropriée pour convertir imageRgb en image
                    // Im.save est supposé enregistrer l'image
                    val imageName = "image_${getNumber(start)}.png"
                    // im.save(imageName, "PNG")

                    reading2 = false
                }
            }

            if (start.isEmpty()) {
                reading = false
            }
        }
        return imageRgb
    }

    private fun getNumber(stuff : String): String {
        var j =0
        while (stuff[j]!= '_'){
            j++
        }
        return stuff.substring(0,j)
    }

    fun getRGB(stuff: String, pointeur: Int): Pair<String, Int> {
        var i = pointeur
        while (stuff[i] != ';') {
            i++
        }
        return Pair(stuff.substring(pointeur, i), i + 1)
    }

    fun separateRGB(RGB: String): List<Int> {
        val list = mutableListOf<Int>()
        var i = 0
        while (RGB[i] != ',') {
            i++
        }
        list.add(RGB.substring(0, i).toInt() * 2)
        i++
        var t = i
        while (RGB[i] != ',') {
            i++
        }
        list.add(RGB.substring(t, i).toInt())
        i++
        t = i
        while (i < RGB.length) {
            i++
        }
        list.add(RGB.substring(t, i).toInt() * 2)
        return list
    }

    fun multipleList(list: List<Int>): IntArray {
        val intArray = list.toIntArray()
        for (i in intArray.indices) {
            intArray[i] *= 4
        }
        return intArray
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