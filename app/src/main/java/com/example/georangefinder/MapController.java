package com.example.georangefinder;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

public class MapController implements IMapController{

    private MapView map;

    public MapController(MapView map) {
        this.map = map;
    }

    @Override
    public void showUserLocation(double lat, double lon) {
            GeoPoint currentLocalization  = new GeoPoint(lat,lon);
            map.getController().setZoom(15.0);
            map.getController().setCenter(currentLocalization);
    }

    @Override
    public void addMarker(double lat, double lon, String name, String description) {
        Marker marker = new Marker(map);
        marker.setPosition(new GeoPoint(lat,lon));
        marker.setTitle(name);
        marker.setSubDescription(description);
        map.getOverlays().add(marker);
        map.invalidate();
    }
}
