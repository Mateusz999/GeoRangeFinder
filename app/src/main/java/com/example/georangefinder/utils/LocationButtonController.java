package com.example.georangefinder.utils;

import android.location.Location;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.georangefinder.location.LocationService;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

public class LocationButtonController implements View.OnClickListener{
    public LocationButtonController(MapView mapView, LocationService locationService) {
        this.mapView = mapView;
        this.locationService = locationService;
    }

    private final MapView mapView;
    private  final LocationService locationService;



    @Override
    public void onClick(View view) {
        GeoPoint userGeoLocation = null;
        Location userLocation = locationService.getCurrentLocation();
        if(userLocation != null){
            userGeoLocation = new GeoPoint(userLocation.getLatitude(),userLocation.getLongitude());

        }
        if(userGeoLocation != null){
            mapView.getController().setCenter(userGeoLocation);
            mapView.getController().setZoom(15.0);
        } else {
            Toast.makeText(view.getContext(),"Location not available",Toast.LENGTH_SHORT).show();
        }
    }

    public void attachTo(ImageButton button){
        button.setOnClickListener(this);
    }
}
