package com.example.georangefinder.utils;

import android.graphics.Color;
import android.location.Location;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Polygon;

public class CircleDrawer {
    private final MapView mapView;
    private Polygon circleOverlay;
    private GeoPoint center;
    private double radiusInMeters;

    public CircleDrawer(MapView mapView) {
        this.mapView = mapView;
    }

    public void drawCircle(GeoPoint userLocation, int radiusKm) {
        radiusInMeters = radiusKm * 1000.0;
        center = userLocation;

        if (circleOverlay != null) {
            mapView.getOverlayManager().remove(circleOverlay);
        }
        if (radiusInMeters == 0) return;

        circleOverlay = new Polygon();
        circleOverlay.setPoints(Polygon.pointsAsCircle(userLocation, radiusInMeters));
        circleOverlay.setStrokeColor(Color.BLUE);
        circleOverlay.setFillColor(0x300000FF);
        circleOverlay.setStrokeWidth(3f);

        mapView.getOverlayManager().add(circleOverlay);
        mapView.invalidate();
    }

    public boolean isInsideCircle(GeoPoint point) {
        if (center == null || radiusInMeters == 0) return false;
        float[] results = new float[1];
        Location.distanceBetween(
                center.getLatitude(), center.getLongitude(),
                point.getLatitude(), point.getLongitude(),
                results
        );
        return results[0] <= radiusInMeters;
    }
}
