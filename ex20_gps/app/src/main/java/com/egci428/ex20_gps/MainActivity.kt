package com.egci428.ex20_gps

import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var button : Button
    private var locationManager: LocationManager? = null
    private var locationListener: LocationListener? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        button = findViewById(R.id.button)
        val gpsText = findViewById<TextView>(R.id.gpsTextView)

        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                gpsText.append("\n"+ location.latitude+", "+location.longitude)
            }

        }

        requestLocation()
    }

    private fun requestLocation() {
        if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                requestPermissions(arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION), 10)
            }
            return
        }
        button.setOnClickListener {
            locationManager!!.requestLocationUpdates("gps", 5000, 0F, locationListener!!)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String?>,
        grantResults: IntArray,
        deviceId: Int
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults, deviceId)
        when(requestCode) {
            10 -> requestLocation()
            else -> {
                Log.d("Permission Result", "Fail to receive request code")
            }
        }
    }
}