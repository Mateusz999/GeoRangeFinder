package com.example.georangefinder.map;

import com.example.georangefinder.markers.MarkerData;

import org.osmdroid.views.overlay.Marker;

public interface IMapController {
    void showUserLocation(double lat, double lon);
    Marker addMarker(MarkerData data);
    Marker addUserLocationMarker(MarkerData data);
}
