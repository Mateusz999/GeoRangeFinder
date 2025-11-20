package com.example.georangefinder.utils;

import android.content.Context;
import android.widget.SeekBar;
import android.widget.TextView;

public class SeekBarController implements SeekBar.OnSeekBarChangeListener{
    private final Context context;
    private int currentRadius = 1;

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    private final TextView rangeValue;


    public SeekBarController(Context context,TextView rangeValue) {
        this.context = context;
        this.rangeValue = rangeValue;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
        rangeValue.setText("Radius: " + progress + " km");
        currentRadius = progress;
    }

    public int getCurrentRadius(){
        return currentRadius;
    }

}
