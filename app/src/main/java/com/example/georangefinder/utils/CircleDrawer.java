package com.example.georangefinder.utils;

import android.graphics.Color;
import android.widget.Toast;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Polygon;

public class CircleDrawer {
    private final MapView mapView;
    private Polygon circleOverlay;
    public CircleDrawer(MapView mapView){
        this.mapView = mapView;
    }


    public void drawCircle(GeoPoint userLocation, int radius){
        double radiusInMeters = radius * 1000;
        if(circleOverlay != null){
            mapView.getOverlayManager().remove(circleOverlay);
        }


        if(radiusInMeters == 0){
            return;
        }
        circleOverlay = new Polygon();

        circleOverlay.setPoints(Polygon.pointsAsCircle(userLocation,radiusInMeters));
        circleOverlay.setStrokeColor(Color.BLUE);
        circleOverlay.setFillColor(0x300000FF);
        circleOverlay.setStrokeWidth(3f);

        mapView.getOverlayManager().add(circleOverlay);
        mapView.invalidate();

    }

}
