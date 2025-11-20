package com.example.georangefinder.markers;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class MarkerStorageTest {

    private MarkerStorage storage;
    private Context context;

    @Before
    public void setup() {
        context = RuntimeEnvironment.getApplication();
        storage = new MarkerStorage(context);
        // czyścimy dane przed każdym testem
        storage.saveMarkers(new ArrayList<>());
    }

    @Test
    public void saveAndLoad_singleMarker_works() {
        List<MarkerData> input = new ArrayList<>();
        input.add(new MarkerData(50.473, 17.334, "Test marker", "Opis"));

        storage.saveMarkers(input);
        List<MarkerData> loaded = storage.loadMarkers();

        assertEquals(1, loaded.size());
        MarkerData m = loaded.get(0);
        assertEquals("Test marker", m.getName());
        assertEquals("Opis", m.getDescription());
        assertEquals(50.473, m.getLatitude(), 1e-6);
        assertEquals(17.334, m.getLongitude(), 1e-6);
    }

    @Test
    public void load_whenEmpty_returnsEmptyList() {
        storage.saveMarkers(new ArrayList<>());
        List<MarkerData> loaded = storage.loadMarkers();
        assertNotNull(loaded);
        assertTrue(loaded.isEmpty());
    }

    @Test
    public void saveAndLoad_multipleMarkers_preservesOrderAndData() {
        List<MarkerData> input = new ArrayList<>();
        input.add(new MarkerData(50.0, 17.0, "A", "a"));
        input.add(new MarkerData(51.0, 18.0, "B", "b"));
        input.add(new MarkerData(52.0, 19.0, "C", "c"));

        storage.saveMarkers(input);
        List<MarkerData> loaded = storage.loadMarkers();

        assertEquals(3, loaded.size());

        assertEquals("A", loaded.get(0).getName());
        assertEquals(50.0, loaded.get(0).getLatitude(), 1e-6);

        assertEquals("B", loaded.get(1).getName());
        assertEquals(51.0, loaded.get(1).getLatitude(), 1e-6);

        assertEquals("C", loaded.get(2).getName());
        assertEquals(52.0, loaded.get(2).getLatitude(), 1e-6);
    }
}
