package com.example.georangefinder.markers;

import android.view.Gravity;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.georangefinder.map.MapClickListener;

public class AddMarkerButtonController {

    private final MapClickListener mapClickListener;

    public AddMarkerButtonController(MapClickListener mapClickListener) {
        this.mapClickListener = mapClickListener;
    }

    public void attachTo(ImageButton button) {
        button.setOnClickListener(v -> {
            mapClickListener.setAddMarkerMode(true);
            Toast toast = Toast.makeText(v.getContext(), "Kliknij na mapę aby dodać marker", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 100);
            toast.show();
        });
    }


}
