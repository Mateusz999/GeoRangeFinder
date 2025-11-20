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

    public void  attachTo(ImageButton button){
        button.setOnClickListener( v -> {
            boolean newState = !mapClickListener.isAddMarkerMode();
            mapClickListener.setAddMarkerMode(newState);
            if (newState) {
                //button.setImageResource(R.drawable.ic_add_marker_active);
                Toast toast = Toast.makeText(v.getContext(), "Add marker mode enabled", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 100);
                toast.show();
            } else {
                //button.setImageResource(R.drawable.ic_add_marker_inactive);
                Toast toast = Toast.makeText(v.getContext(), "Add marker mode disabled", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 100);
                toast.show();
            }
        });
    }
}
