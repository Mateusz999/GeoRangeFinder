package com.example.georangefinder.map;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.georangefinder.R;
import com.example.georangefinder.markers.MarkerData;
import com.example.georangefinder.markers.MarkerStorage;

import org.osmdroid.events.MapEventsReceiver;
import org.osmdroid.util.GeoPoint;

public class MapClickListener implements MapEventsReceiver {
    private final Context context;
    private final MapController mapController;
    private final MarkerStorage markerStorage;

    private boolean addMarkerMode = false;

    public MapClickListener(Context context, MapController mapController, MarkerStorage markerStorage) {
        this.context = context;
        this.mapController = mapController;
        this.markerStorage = markerStorage;
    }

    @Override
    public boolean singleTapConfirmedHelper(GeoPoint p) {
        if (addMarkerMode) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View dialogView = inflater.inflate(R.layout.dialog_add_marker, null);

            EditText nameInput = dialogView.findViewById(R.id.markerNameInput);
            EditText descriptionInput = dialogView.findViewById(R.id.markerDescriptionInput);

            new AlertDialog.Builder(context)
                    .setTitle("Dodaj marker")
                    .setView(dialogView)
                    .setPositiveButton("Dodaj", (dialog, which) -> {
                        String name = nameInput.getText().toString().trim();
                        String description = descriptionInput.getText().toString().trim();

                        MarkerData data = new MarkerData(
                                p.getLatitude(),
                                p.getLongitude(),
                                name.isEmpty() ? "Nowy marker" : name,
                                description.isEmpty() ? "Brak opisu" : description
                        );

                        mapController.addMarker(data);
                        markerStorage.saveMarkers(mapController.getMarkersData());
                        Toast.makeText(context, "Marker dodany", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("Anuluj", null)
                    .show();

            addMarkerMode = false;
        }
        return false;
    }

    @Override
    public boolean longPressHelper(GeoPoint p) {
        return false;
    }

    public void setAddMarkerMode(boolean enabled) {
        this.addMarkerMode = enabled;
    }

    public boolean isAddMarkerMode() {
        return addMarkerMode;
    }
}
