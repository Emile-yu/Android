package com.example.pc.kissmyplace;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;


/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment {


    private MapView mapView;
    private GoogleMap googleMap;

    MapsActivity mapsActivity;

    public MapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        mapView = (MapView) view.findViewById(R.id.map);

        mapView.onCreate(savedInstanceState);

        mapView.onResume();

        try {
            MapsInitializer.initialize(getActivity());
        } catch (Exception e) {
            e.printStackTrace();
        }

       mapView.getMapAsync(
               (GoogleMap map) -> {
                   map.setOnMapClickListener(mapsActivity);
                   map.resetMinMaxZoomPreference();
                   googleMap = map;
                   googleMap.setBuildingsEnabled(false);
                   googleMap.setIndoorEnabled(false);
                   googleMap.setTrafficEnabled(false);
               }
       );


        return view;
    }


}
