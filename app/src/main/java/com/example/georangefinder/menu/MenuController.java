package com.example.georangefinder.menu;

import androidx.drawerlayout.widget.DrawerLayout;
import android.widget.ImageButton;

import com.example.georangefinder.markers.MarkerData;

import java.util.List;

public class MenuController {

    private final DrawerLayout drawerLayout;
    private final ImageButton hamburgerButton;
    private MarkerAdapter markerAdapter;

    public MenuController(DrawerLayout drawerLayout, ImageButton hamburgerButton) {
        this.drawerLayout = drawerLayout;
        this.hamburgerButton = hamburgerButton;

        hamburgerButton.setOnClickListener(v -> {
            drawerLayout.openDrawer(androidx.core.view.GravityCompat.START);
        });
    }

    public void setMarkerAdapter(MarkerAdapter adapter) {
        this.markerAdapter = adapter;
    }

    public void updateMarkerList(List<MarkerData> markers) {
        if (markerAdapter != null) {
            markerAdapter.setData(markers);
            markerAdapter.notifyDataSetChanged();
        }
    }
}
