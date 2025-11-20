package com.example.georangefinder.menu;

import android.location.Location;
import android.widget.Toast;

import com.example.georangefinder.map.MapController;
import com.example.georangefinder.markers.MarkerData;
import com.example.georangefinder.menu.MenuController;
import com.example.georangefinder.utils.CircleDrawer;

import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;
import java.util.List;

public class RangeChecker {

    private final MapController mapController;
    private final CircleDrawer circleDrawer;
    private final MenuController menuController;
    private final List<MarkerData> markersInCircle = new ArrayList<>();

    public RangeChecker(MapController mapController, CircleDrawer circleDrawer, MenuController menuController) {
        this.mapController = mapController;
        this.circleDrawer = circleDrawer;
        this.menuController = menuController;
    }

    public void checkRange(Location userLocation, int radiusKm) {
        if (userLocation != null) {
            GeoPoint center = new GeoPoint(userLocation.getLatitude(), userLocation.getLongitude());
            circleDrawer.drawCircle(center, radiusKm);

            markersInCircle.clear();
            for (MarkerData marker : mapController.getMarkersData()) {
                GeoPoint point = new GeoPoint(marker.getLatitude(), marker.getLongitude());
                if (circleDrawer.isInsideCircle(point)) {
                    markersInCircle.add(marker);
                }
            }

            menuController.updateMarkerList(markersInCircle);
        } else {
            Toast.makeText(mapController.getContext(), "Location not available", Toast.LENGTH_SHORT).show();
        }
    }

    public List<MarkerData> getMarkersInCircle() {
        return new ArrayList<>(markersInCircle);
    }
}
