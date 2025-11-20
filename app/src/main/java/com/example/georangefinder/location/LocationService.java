package com.example.georangefinder.location;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

public class LocationService {

    public interface LocationCallback {
        void onLocationChanged(Location location);
    }

    private final Context context;
    private final LocationManager locationManager;


    private final LocationListener listener;

    public LocationService(Context context, LocationCallback callback) {
        this.context = context;
        this.locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        this.listener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                callback.onLocationChanged(location);
            };

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras){

            }

            @Override
            public void onProviderEnabled(@NonNull String provider){

            };

            @Override
            public void onProviderDisabled(@NonNull String provider){

            }


        };
    }

    public void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10, listener);
        }
    }

    public void stopLocationUpdates() {
        locationManager.removeUpdates(listener);
    }
}
