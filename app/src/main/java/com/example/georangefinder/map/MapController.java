package com.example.georangefinder.map;

import android.content.Context;

import com.example.georangefinder.markers.MarkerData;
import com.example.georangefinder.markers.MarkerStorage;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import java.util.ArrayList;
import java.util.List;

public class MapController implements IMapController {

    private final MapView map;
    private final List<MarkerData> savedMarkers = new ArrayList<>();

    public MapController(MapView map) {
        this.map = map;
    }

    @Override
    public void showUserLocation(double lat, double lon) {
        GeoPoint currentLocalization = new GeoPoint(lat, lon);
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

        marker.setOnMarkerClickListener((m, mapView) -> {
            removeMarker(m);
            MarkerStorage storage = new MarkerStorage(map.getContext());
            storage.saveMarkers(new ArrayList<>(savedMarkers));
            return true;
        });

        map.getOverlays().add(marker);
        map.invalidate();

        savedMarkers.add(data);
        return marker;
    }

    public List<MarkerData> getMarkersData() {
        return new ArrayList<>(savedMarkers);
    }

    public void removeLastMarker() {
        if (!savedMarkers.isEmpty()) {
            MarkerData last = savedMarkers.remove(savedMarkers.size() - 1);
            removeMarkerByData(last);
        }
    }

    public void removeMarker(Marker marker) {
        map.getOverlays().remove(marker);
        map.invalidate();

        MarkerData toRemove = null;
        for (MarkerData data : savedMarkers) {
            if (data.getLatitude() == marker.getPosition().getLatitude()
                    && data.getLongitude() == marker.getPosition().getLongitude()
                    && data.getName().equals(marker.getTitle())) {
                toRemove = data;
                break;
            }
        }
        if (toRemove != null) {
            savedMarkers.remove(toRemove);
        }
    }

    private void removeMarkerByData(MarkerData data) {
        Marker toRemove = null;
        for (Object o : new ArrayList<>(map.getOverlays())) {
            if (o instanceof Marker) {
                Marker m = (Marker) o;
                if (m.getPosition().getLatitude() == data.getLatitude()
                        && m.getPosition().getLongitude() == data.getLongitude()
                        && m.getTitle().equals(data.getName())) {
                    toRemove = m;
                    break;
                }
            }
        }
        if (toRemove != null) {
            map.getOverlays().remove(toRemove);
            map.invalidate();
        }
    }

    public MapView getMap() {
        return map;
    }

    public Context getContext() {
        return map.getContext();
    }
}
