package com.example.georangefinder.map;

import android.content.Context;
import android.widget.Toast;

import org.osmdroid.events.MapEventsReceiver;
import org.osmdroid.util.GeoPoint;

public class MapClickListener implements MapEventsReceiver {
    private final Context context;
    private final MapController mapController;

    public MapClickListener(Context context, MapController mapController) {
        this.context = context;
        this.mapController = mapController;
    }

    @Override
    public boolean singleTapConfirmedHelper(GeoPoint p) {
        Toast.makeText(context,"Kliknięto "+p.getLatitude()+", "+p.getLongitude(),Toast.LENGTH_SHORT).show();
        mapController.addMarker(p.getLatitude(),p.getLongitude(),"click","punkt dodany poprzez klikniecie");
        return false;
    }

    @Override
    public boolean longPressHelper(GeoPoint p) {
        return false;
    }
}
