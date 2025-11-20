package com.example.georangefinder.map;

import org.osmdroid.views.overlay.Marker;

public interface IMapController {
    void showUserLocation(double lat, double lon);
    Marker addMarker(double lat, double lon, String name, String description);
}
