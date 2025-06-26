package com.egci428.ex21_mapmarker

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.egci428.ex21_mapmarker.databinding.ActivityMapsBinding
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.Polygon
import com.google.android.gms.maps.model.PolygonOptions
import com.google.android.gms.maps.model.Polyline
import com.google.android.gms.maps.model.PolylineOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        /*val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))*/
        val mahidol = LatLng(13.786200, 100.324509)
        mMap.addMarker(MarkerOptions().position(mahidol).title("Marker in Mahidol"))
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(mahidol))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(15.0, 100.45),9F))

        /*mMap.setOnMapClickListener {
            latlon ->
                mMap.addMarker(MarkerOptions()
                    .position(latlon)
                    .title("lat:" + latlon.latitude.toString()+" lon: "+latlon.longitude.toString())
                    .snippet("egci428"))
        }*/
        mMap.setOnMapClickListener {
            latlon ->
            mMap.addMarker(MarkerOptions()
                .position(latlon)
                //.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.image10101))
                .title("lat:" + latlon.latitude.toString()+" lon: "+latlon.longitude.toString())
                .snippet("egci428"))
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlon,9F))
        }

        /*mMap.addPolyline(PolylineOptions()
            .add(LatLng(15.0, 100.45), LatLng(15.432, 100.456), LatLng(15.5, 100.7))
            .width(5F)
            .color(Color.RED)
        )*/

        mMap.addPolygon(PolygonOptions()
            .add(LatLng(15.0, 100.45), LatLng(15.432, 100.456), LatLng(15.5, 100.7), LatLng(15.0, 100.45))
            .strokeColor(Color.BLUE)
            .fillColor(Color.YELLOW)
        )
    }
}