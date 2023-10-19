package com.example.test

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.ActivityCompat
import android.view.Window
import android.widget.Button
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {

    private val REQUEST_BLUETOOTH_PERMISSION = 1
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bluetoothManager: BluetoothManager = getSystemService(BluetoothManager::class.java)
        val bluetoothAdapter: BluetoothAdapter? = bluetoothManager.getAdapter()
        val button = findViewById<Button>(R.id.button)
        val buttonBluetooth = findViewById<ImageButton>(R.id.imageButton2)
        button.setOnClickListener {
            val intent = Intent(this, noms2::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left); // Appliquer l'animation de translation vers la droite
            //overridePendingTransition(R.anim.rotate_in,R.anim.rotate_out);
        }
        buttonBluetooth.setOnClickListener {
            // Vérifier si l'application a l'autorisation BLUETOOTH_CONNECT
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.BLUETOOTH_CONNECT
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // Si l'autorisation n'est pas accordée, demander la permission à l'utilisateur
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.BLUETOOTH_CONNECT),
                    REQUEST_BLUETOOTH_PERMISSION
                )
            } else {
                // Si l'autorisation est accordée, activer le Bluetooth
                val intentBluetoothAdapter = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                startActivity(intentBluetoothAdapter)
            }

        }
            val buttonWifi = findViewById<ImageButton>(R.id.imageButton3)
            buttonWifi.setOnClickListener{

            }
    }
}