package com.example.georangefinder.markers;

import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MarkerStorageTest {

    private MarkerStorage storage;
    private Context context;

    @Before
    public void setup() {
        context = ApplicationProvider.getApplicationContext();
        storage = new MarkerStorage(context);

        // czyścimy zapisane markery przed każdym testem
        storage.saveMarkers(new ArrayList<>());
    }

    @Test
    public void saveAndLoad_singleMarker_works() {
        // przygotowanie danych
        List<MarkerData> input = new ArrayList<>();
        input.add(new MarkerData(50.473, 17.334, "Test marker", "Opis"));

        // zapis
        storage.saveMarkers(input);

        // odczyt
        List<MarkerData> loaded = storage.loadMarkers();

        // sprawdzenie
        assertEquals(1, loaded.size());
        MarkerData m = loaded.get(0);
        assertEquals("Test marker", m.getName());
        assertEquals("Opis", m.getDescription());
        assertEquals(50.473, m.getLatitude(), 1e-6);
        assertEquals(17.334, m.getLongitude(), 1e-6);
    }
}
