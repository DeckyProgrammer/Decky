package com.example.test

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.MacAddress
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.net.wifi.WifiNetworkSpecifier
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.ActivityCompat
import android.widget.Button
import android.widget.ImageButton

<<<<<<< Updated upstream

class MainActivity : AppCompatActivity() {
=======
open class networkCallback

class MainActivity : AppCompatActivity() {
    private lateinit var networkCallback: ConnectivityManager.NetworkCallback
    private val REQUEST_BLUETOOTH_PERMISSION = 1
>>>>>>> Stashed changes
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bluetoothManager: BluetoothManager = getSystemService(BluetoothManager::class.java)
        val bluetoothAdapter: BluetoothAdapter? = bluetoothManager.getAdapter()
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
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
        //wifi
        val isWpa3 = false //on a un wifi avec wpa2
        val specifier = WifiNetworkSpecifier.Builder()
            .setSsid("Esp_32_Test") //id du wifi
            .setBssid(MacAddress.fromString(macAddressString))
            .apply {
                if (isWpa3 != null && isWpa3) {
                    setWpa3Passphrase("12345678910")
                } else {
                    setWpa2Passphrase("12345678910")
                    //mot de passe du WpA2 ou Wpa3
                }
            }
            .build()

        var request = NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .removeCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .setNetworkSpecifier(specifier)
            .build()



        this.networkCallback = object : ConnectivityManager.NetworkCallback() {

            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                connectivityManager.bindProcessToNetwork(network)

                // Success
            }

            override fun onUnavailable() {
                super.onUnavailable()

                // Unavailable

                // Always end up here with display off
            }

        }
        // Defined elsewhere
        connectivityManager.requestNetwork(request, networkCallback)
    }
}

