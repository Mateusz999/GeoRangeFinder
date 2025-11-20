package com.example.georangefinder;


import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.georangefinder.location.LocationService;
import com.example.georangefinder.location.LocationUpdateHandler;
import com.example.georangefinder.map.MapClickListener;
import com.example.georangefinder.map.MapController;
import com.example.georangefinder.map.MapInitializer;
import com.example.georangefinder.map.MapLifecycleHandler;
import com.example.georangefinder.markers.MarkerData;
import com.example.georangefinder.markers.MarkerStorage;
import com.example.georangefinder.permissions.PermissionManager;
import com.example.georangefinder.markers.AddMarkerButtonController;
import com.example.georangefinder.utils.CircleDrawer;
import com.example.georangefinder.location.LocationButtonController;
import com.example.georangefinder.utils.SeekBarController;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.MapEventsOverlay;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_LOCATION = 1;

    private MapView mapView;
    private MapController mapController;
    private LocationService locationService;
    private MapLifecycleHandler lifecycleHandler;

    private MapEventsOverlay overlay;
    private SeekBarController seekBarController;
    private LocationButtonController locationButtonController;
    private AddMarkerButtonController addMarkerButtonController;
    private MapClickListener clickListener;
    private MarkerStorage markerStorage;
    private CircleDrawer circleDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SeekBar seekBar = findViewById(R.id.rangeSeekBar);
        TextView rangeValueText = findViewById(R.id.rangeValueText);
        Button changeRangeButton = findViewById(R.id.changeRangeButton);
        ImageButton currentLocationButton = findViewById(R.id.currentLocationButton);
        ImageButton addMarkerButton = findViewById(R.id.addMarkerButton);


        markerStorage = new MarkerStorage(this);


        seekBarController = new SeekBarController(this, rangeValueText);
        seekBar.setOnSeekBarChangeListener(seekBarController);

        mapView = findViewById(R.id.map);
        MapInitializer.initMap(this, mapView);

        mapController = new MapController(mapView);
        mapController.showUserLocation(50.473, 17.334);


        List<MarkerData> savedMarkers = markerStorage.loadMarkers();
        for (MarkerData data : savedMarkers) {
            mapController.addMarker(data);
        }

        locationService = new LocationService(this,new LocationUpdateHandler(mapController));
        lifecycleHandler = new MapLifecycleHandler(mapView, locationService);


        clickListener = new MapClickListener(this,mapController,markerStorage);
        overlay = new MapEventsOverlay(clickListener);
        mapView.getOverlays().add(overlay);


        locationButtonController = new LocationButtonController(mapView,locationService);
        locationButtonController.attachTo(currentLocationButton);

        circleDrawer = new CircleDrawer(mapView);
        addMarkerButtonController = new AddMarkerButtonController(clickListener);
        addMarkerButtonController.attachTo(addMarkerButton);



        changeRangeButton.setOnClickListener( v -> {
            Location userLocation = locationService.getCurrentLocation();
            if(userLocation != null){
                GeoPoint center = new GeoPoint(userLocation.getLatitude(),userLocation.getLongitude());
                circleDrawer.drawCircle(center,seekBarController.getCurrentRadius());
            } else {
                Toast.makeText(this, "Location not available", Toast.LENGTH_SHORT).show();
            }
        });
        if(PermissionManager.hasLocationPermission(this)){
            locationService.startLocationUpdates();
        }else {
            PermissionManager.requestLocationPermission(this);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PermissionManager.getRequestCode() && grantResults.length > 0 &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            locationService.startLocationUpdates();
        } else {
            Toast.makeText(this, "Brak zgody na lokalizację", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        lifecycleHandler.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        lifecycleHandler.onPause();

    }
}
