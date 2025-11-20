package com.example.georangefinder.permissions;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PermissionManager {
    private static final int REQUEST_LOCATION = 1;

    public static boolean hasLocationPermission(Context context){

      return ContextCompat
              .checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
              == PackageManager.PERMISSION_GRANTED;
    };

    public static void requestLocationPermission(Activity activity){
        ActivityCompat.requestPermissions(activity,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_LOCATION);
    }

    public static int getRequestCode(){
        return REQUEST_LOCATION;
    }

}
