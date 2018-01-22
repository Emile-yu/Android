package com.example.pc.kissmyplace;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.StreetViewPanoramaView;
import com.google.android.gms.maps.SupportStreetViewPanoramaFragment;
import com.google.android.gms.maps.model.LatLng;


/**
 * A simple {@link Fragment} subclass.
 */
public class StreetFragment extends Fragment {

    private SupportStreetViewPanoramaFragment streetViewPanoramaFragment;

    private StreetViewPanoramaView streetViewPanoramaView;

    StreetViewPanorama streetViewPanorama;

    MapsActivity mapsActivity;


    public StreetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_street, container, false);

        streetViewPanoramaView = (StreetViewPanoramaView) view.findViewById(R.id.streetviewpanorama);


        streetViewPanoramaView.onCreate(savedInstanceState);
        streetViewPanoramaView.onResume();


        streetViewPanoramaView.getStreetViewPanoramaAsync(
                new OnStreetViewPanoramaReadyCallback() {
                    @Override
                    public void onStreetViewPanoramaReady(StreetViewPanorama panorama) {
                        // Do what you want to do here.
                        //LatLng l = mapsActivity.start;
                        LatLng start = new LatLng(54.001615, -2.794561);

                        panorama.setPosition(start);
                    }
                }
        );
        /*streetViewPanoramaView.getStreetViewPanoramaAsync(
                (StreetViewPanorama panorama) -> {
                    //LatLng l = new LatLng(mapsActivity.position.get(mapsActivity.current).latitude, mapsActivity.position.get(mapsActivity.current).longitude);
                    LatLng l = mapsActivity.start;
                    panorama.setPosition(l);
                    streetViewPanorama = panorama;
                    //streetViewPanorama.setOnStreetViewPanoramaChangeListener(mapsActivity);
                    streetViewPanorama.setStreetNamesEnabled(false);
                    streetViewPanorama.setUserNavigationEnabled(true);
                    streetViewPanorama.setPanningGesturesEnabled(true);
                    streetViewPanorama.setZoomGesturesEnabled(true);
                }
        );
*/


        return view;
    }

}
