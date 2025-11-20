package com.example.georangefinder.markers;

import org.osmdroid.util.GeoPoint;

public class MarkerData {
    private double latitude;
    private double longitude;
    private String name;
    private String description;

    public MarkerData(double latitude, double longitude, String name, String description) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.description = description;
    }

    public GeoPoint toGeoPoint() {
        return new GeoPoint(latitude, longitude);
    }

    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }
    public String getName() { return name; }
    public String getDescription() { return description; }
}
