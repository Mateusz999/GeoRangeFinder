package com.example.georangefinder.menu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.georangefinder.R;
import com.example.georangefinder.markers.MarkerData;

import java.util.ArrayList;
import java.util.List;

public class MarkerAdapter extends RecyclerView.Adapter<MarkerAdapter.ViewHolder> {
    private List<MarkerData> markers = new ArrayList<>();

    public void setData(List<MarkerData> markers) {
        this.markers = markers;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_marker, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MarkerData marker = markers.get(position);
        holder.name.setText(marker.getName());
        holder.description.setText(marker.getDescription());

        String coords = String.format("Lat: %.5f, Lon: %.5f",
                marker.getLatitude(), marker.getLongitude());
        holder.coordinates.setText(coords);
    }

    @Override
    public int getItemCount() {
        return markers.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, description, coordinates;
        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.markerName);
            description = itemView.findViewById(R.id.markerDescription);
            coordinates = itemView.findViewById(R.id.markerCoordinates);
        }
    }
}
