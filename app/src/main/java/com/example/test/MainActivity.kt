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
import android.widget.Button
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bluetoothManager: BluetoothManager = getSystemService(BluetoothManager::class.java)
        val bluetoothAdapter: BluetoothAdapter? = bluetoothManager.getAdapter()
        val button = findViewById<Button>(R.id.button)
        val buttonBluetooth = findViewById<ImageButton>(R.id.imageButton2)
        button.setOnClickListener {
            val intent = Intent(this, Selection_players::class.java)
            startActivity(intent)
        }
        buttonBluetooth.setOnClickListener {
            val intentBluetoothAdapter = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivity(intentBluetoothAdapter)
        }
    }
}