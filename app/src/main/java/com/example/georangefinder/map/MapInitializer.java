package com.example.georangefinder.map;

import android.content.Context;
import android.preference.PreferenceManager;

import org.osmdroid.config.Configuration;
import org.osmdroid.views.MapView;

public class MapInitializer {

    public static MapView  initMap(Context context, MapView mapView){
        Configuration.getInstance().load(context, PreferenceManager.getDefaultSharedPreferences(context));
        Configuration.getInstance().setUserAgentValue(context.getPackageName());

        mapView.setMultiTouchControls(true);
        mapView.setBuiltInZoomControls(true);

        return mapView;
    }

}
