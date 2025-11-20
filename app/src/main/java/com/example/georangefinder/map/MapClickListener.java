package com.example.georangefinder.map;

import android.content.Context;
import android.widget.Toast;

import com.example.georangefinder.markers.MarkerData;
import com.example.georangefinder.markers.MarkerStorage;

import org.osmdroid.events.MapEventsReceiver;
import org.osmdroid.util.GeoPoint;

public class MapClickListener implements MapEventsReceiver {
    private final Context context;
    private final MapController mapController;
    private MarkerStorage markerStorage;

    private boolean addMarkerMode = false;

    public MapClickListener(Context context, MapController mapController, MarkerStorage markerStorage) {
        this.context = context;
        this.markerStorage = markerStorage;
        this.mapController = mapController;
    }

    @Override
    public boolean singleTapConfirmedHelper(GeoPoint p) {

        if(addMarkerMode){
            MarkerData data = new MarkerData(
                    p.getLatitude(),
                    p.getLongitude(),
                    "Click marker",
                    "punkt dodany poprzez kliknięcie"
            );

            mapController.addMarker(data);
            markerStorage.saveMarkers(mapController.getMarkers());
            addMarkerMode = false;
        }
        return false;
    }

    @Override
    public boolean longPressHelper(GeoPoint p) {
        return false;
    }

    public void setAddMarkerMode(boolean enabled){
        this.addMarkerMode = enabled;
    }

    public boolean isAddMarkerMode(){
        return addMarkerMode;
    }
}
