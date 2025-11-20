package com.example.georangefinder.map;

import com.example.georangefinder.location.LocationService;

import org.osmdroid.views.MapView;

public class MapLifecycleHandler {

    private final MapView mapView;
    private final LocationService locationService;


    public MapLifecycleHandler(MapView mapView, LocationService locationService) {
        this.mapView = mapView;
        this.locationService = locationService;
    }

    public void onResume(){
        if(mapView !=null)
            mapView.onResume();
    }

    public void onPause(){
        if(mapView !=null)
            mapView.onPause();
        if(locationService != null){
            locationService.stopLocationUpdates();
        }
    }

}
