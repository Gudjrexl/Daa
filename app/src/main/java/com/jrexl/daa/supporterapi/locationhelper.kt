package com.jrexl.daa.supporterapi

import android.annotation.SuppressLint
import android.content.Context
import android.location.Geocoder
import android.location.Location
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.tasks.await
import java.util.Locale

class locationhelper(private val context: Context) {

    private val fusedClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    @SuppressLint("MissingPermission")
    suspend fun getlocation(): String {
        Log.d("LocationHelper", "Getting last known location...")

        val location: Location? = try {
            fusedClient.lastLocation.await()
        } catch (e: Exception) {
            Log.e("LocationHelper", "Error getting last location", e)
            null
        }

        if (location == null) {
            Log.w("LocationHelper", "⚠️ lastLocation is NULL")
            return "Location unavailable"
        }

        Log.d("LocationHelper", "✅ Location received: lat=${location.latitude}, lon=${location.longitude}")

        return try {
            val geocoder = Geocoder(context, Locale.getDefault())
            val address = geocoder.getFromLocation(location.latitude, location.longitude, 1)
            if (!address.isNullOrEmpty()) {
                val city = address[0].locality ?: "Unknown City"
                val country = address[0].countryName ?: "Unknown Country"
                Log.d("LocationHelper", "📍 Address found: $city, $country")
                "$city, $country"
            } else {
                Log.w("LocationHelper", "⚠️ Geocoder returned empty list")
                "Unknown location"
            }
        } catch (e: Exception) {
            Log.e("LocationHelper", "Error in Geocoder lookup", e)
            "Can't fetch location"
        }
    }
}
