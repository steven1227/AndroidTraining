package com.example.steven.app1;

import java.util.ArrayList;
import java.util.Vector;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.tomtom.lbs.sdk.TTMapView;
import com.tomtom.lbs.sdk.TTMarker;
import com.tomtom.lbs.sdk.geolocation.GeocodeData;
import com.tomtom.lbs.sdk.geolocation.GeocodeListener;
import com.tomtom.lbs.sdk.geolocation.Geocoder;
import com.tomtom.lbs.sdk.util.Coordinates;
import com.tomtom.lbs.sdk.util.SDKContext;


public class Map extends Activity implements GeocodeListener{
    private TTMapView mapView;
    private Handler myHandler = new Handler();
    private AutoCompleteTextView searchEditText;
    private Button OkButton;
    private Button clearButton;
    private ArrayList<TTMarker> markerList;
    private int zoomLevel = 14;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SDKContext.setDeveloperKey("pf7fj2795xsxjr7ryfwct289");

        Coordinates seattleCoordinates = new Coordinates(40.41669, -3.70035);

        mapView = new TTMapView(getApplicationContext(), seattleCoordinates,
                zoomLevel, true);

        // Set the current View
        setContentView(R.layout.map);

        // Get the main elements of the view.
        searchEditText = (AutoCompleteTextView) findViewById(R.id.what);
        OkButton = (Button) findViewById(R.id.OKButton);
        clearButton = (Button) findViewById(R.id.clearResults);

        // Fetch the Container for the Map
        FrameLayout parentView = (FrameLayout) findViewById(R.id.parentMapLayout);

        // And add it to the frame.
        parentView.addView((View) mapView);

        // Add the search action
        OkButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String what = searchEditText.getText().toString();
                performSearch(what);

            }
        });

        // Add the "clear all  results" action
        clearButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mapView.removeMarkers(markerList);
                mapView.invalidate();
            }
        });

        // initialize the marker list
        markerList = new ArrayList<TTMarker>();
    }

    private void performSearch(final String text) {
        Runnable ttplacesSearch = new Runnable() {
            public void run() {
                try {
                    Geocoder.geocode(text, null, Map.this, null);
                    Log.v("steven",text);
                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        };

        // The request must be in the UI thread
        myHandler.postDelayed(ttplacesSearch, 500);
    }

    @Override
    public void handleGeocode(Vector<GeocodeData> data, Object payload) {

        if (data == null || data.size() <= 0) {
            Log.v("stevendata",data.toString());
            Toast.makeText(this, R.string.noResults, Toast.LENGTH_LONG).show();
            return;
        }

        Log.v("steveniloveit",data.toString());
        mapView.removeMarkers(markerList);

        markerList.clear();

        Coordinates first = null;

        for (GeocodeData newdata : data) {
            Coordinates coords = new Coordinates(newdata.latitude,
                    newdata.longitude);
            if (first == null)
                first = coords;
            TTMarker marker = new TTMarker(null, coords, null);
            markerList.add(marker);
        }

        mapView.addMarkers(markerList);

        if (first != null)
            mapView.setMapCenter(first);

        searchEditText.setText("");
    }
}