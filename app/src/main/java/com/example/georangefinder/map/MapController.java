package com.example.georangefinder.map;

import com.example.georangefinder.markers.MarkerData;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import java.util.ArrayList;
import java.util.List;

public class MapController implements IMapController {

    private MapView map;
    private final List<MarkerData> savedMarkers = new ArrayList<>();

    public MapController(MapView map) {
        this.map = map;
    }

    @Override
    public void showUserLocation(double lat, double lon) {
            GeoPoint currentLocalization  = new GeoPoint(lat,lon);
            map.getController().setZoom(15.0);
            map.getController().setCenter(currentLocalization);
    }
    public Marker addUserLocationMarker(MarkerData data) {
        Marker marker = new Marker(map);
        marker.setPosition(data.toGeoPoint());
        marker.setTitle(data.getName());
        marker.setSnippet(data.getDescription());
        map.getOverlays().add(marker);
        map.invalidate();

        return marker;
    }
    @Override
    public Marker addMarker(MarkerData data) {
        Marker marker = new Marker(map);
        marker.setPosition(data.toGeoPoint());
        marker.setTitle(data.getName());
        marker.setSnippet(data.getDescription());
        map.getOverlays().add(marker);
        map.invalidate();

        savedMarkers.add(data);
        return marker;
    }
    public List<MarkerData> getMarkers() {
        return savedMarkers;
    }

    public MapView getMap(){
        return map;
    }
}
