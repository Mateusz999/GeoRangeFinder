package com.example.georangefinder.markers;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;
import java.util.List;

public class MarkerStorage {

    private static final String PREF_NAME = "markers";
    private static final String KEY_MARKERS = "markers_json";

    private final SharedPreferences prefs;

    public MarkerStorage(Context context){
        prefs = context.getSharedPreferences(PREF_NAME,context.MODE_PRIVATE);
    }

    public void saveMarkers(List<MarkerData> markers){
        JSONArray array = new JSONArray();

        for(MarkerData m : markers){
            JSONObject obj = new JSONObject();
            try{
                obj.put("lat",m.getLatitude());
                obj.put("lon",m.getLongitude());
                obj.put("name",m.getName());
                obj.put("description",m.getDescription());
                array.put(obj);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
        prefs.edit().putString(KEY_MARKERS, array.toString()).apply();
    }

    public List<MarkerData> loadMarkers(){
        List<MarkerData> result = new ArrayList<>();
        String json = prefs.getString(KEY_MARKERS,null);
        if(json != null){
            try{
                JSONArray array = new JSONArray(json);
                for( int i = 0 ; i<array.length();i++){
                    JSONObject obj = array.getJSONObject(i);
                    double lat = obj.getDouble("lat");
                    double lon = obj.getDouble("lon");
                    String name = obj.getString("name");
                    String description = obj.getString("description");

                    result.add(new MarkerData(lat, lon, name, description));

                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
        return  result;
    }




}
