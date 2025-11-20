package com.example.georangefinder.location;

import android.graphics.drawable.Drawable;
import android.location.Location;

import androidx.core.content.ContextCompat;

import com.example.georangefinder.R;
import com.example.georangefinder.map.MapController;
import com.example.georangefinder.markers.MarkerData;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.overlay.Marker;

public class LocationUpdateHandler implements LocationService.LocationCallback {
    private final MapController mapController;
    private Marker userMarker;

    public LocationUpdateHandler(MapController mapController) {
        this.mapController = mapController;
    }

    @Override
    public void onLocationChanged(Location newlocation) {
        double lat = newlocation.getLatitude();
        double lon = newlocation.getLongitude();

        mapController.showUserLocation(lat, lon);

        if (userMarker == null) {
            MarkerData data = new MarkerData(lat, lon, "Moja lokalizacja", "Aktualna pozycja");
            userMarker = mapController.addUserLocationMarker(data);

            Drawable icon = ContextCompat.getDrawable(
                    mapController.getContext(),
                    R.drawable.current_location
            );
            userMarker.setIcon(icon);
            userMarker.setInfoWindow(null);
        } else {
            userMarker.setPosition(new GeoPoint(lat, lon));
        }

        mapController.getMap().invalidate();
    }
}
