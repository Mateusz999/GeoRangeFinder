package com.example.georangefinder;

public interface IMapController {
    void showUserLocation(double lat, double lon);
    void addMarker(double lat, double lon, String name, String description);
}
