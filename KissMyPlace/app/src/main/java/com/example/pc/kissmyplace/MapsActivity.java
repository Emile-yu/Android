package com.example.pc.kissmyplace;


import android.content.DialogInterface;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.SupportStreetViewPanoramaFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import java.util.List;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, OnMapClickListener {

    private GoogleMap mMap;
    LatLng start = new LatLng(54.001615, -2.794561);
    List<LatLng> position = new ArrayList<LatLng>();

    int current = 0;


    private AlertDialog alert;
    private AlertDialog.Builder builder;
    private SupportStreetViewPanoramaFragment streetViewPanoramaFragment;

    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager;


    MapFragment mapFragment;
    StreetFragment streetFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_maps);




        int lvl = getIntent().getIntExtra("lvl",0);
        if(lvl == 0){
            position.add(new LatLng(40.7248846, -73.98639679));
            position.add(new LatLng(39.70718666, -99.37133789));
            position.add(new LatLng(48.63290859, 2.26318359));

        }

        if(lvl == 1){
            start = new LatLng(33.87365, 151.20689);
        }

        if(lvl == 2){
            start = new LatLng(63.87365, 151.20689);
        }



        MapFragment mapFragment = new MapFragment();
        mapFragment.mapsActivity = this;

        StreetFragment streetFragment = new StreetFragment();
        streetFragment.mapsActivity = this;


        fragmentManager = this.getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();



        fragmentTransaction.add(R.id.map, (Fragment)mapFragment);
        fragmentTransaction.add(R.id.streetviewpanorama, (Fragment)streetFragment);
        fragmentTransaction.commit();

       /* streetViewPanoramaFragment =
                (SupportStreetViewPanoramaFragment) getSupportFragmentManager().findFragmentById(R.id.streetviewpanorama);
        streetViewPanoramaFragment.getStreetViewPanoramaAsync(
                new OnStreetViewPanoramaReadyCallback() {
                    @Override
                    public void onStreetViewPanoramaReady(StreetViewPanorama panorama) {
                        panorama.setPosition(start);

                    }
                });


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        */
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Setting a click event handler for the map
       /* mMap.setOnMapClickListener(new OnMapClickListener() {

            @Override
            public void onMapClick(final LatLng latLng) {

                MarkerOptions markerOptions = new MarkerOptions();

                markerOptions.position(latLng);
                markerOptions.title(latLng.latitude + " : " + latLng.longitude);
                mMap.clear();
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

                mMap.addMarker(markerOptions);
                alert = null;
                builder = new AlertDialog.Builder(MapsActivity.this);
                alert = builder
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setMessage("You are "+getDistance(start,latLng)+"km away")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                start = new LatLng(latLng.latitude, latLng.longitude);

                                streetViewPanoramaFragment.getStreetViewPanoramaAsync(
                                        new OnStreetViewPanoramaReadyCallback() {
                                            @Override
                                            public void onStreetViewPanoramaReady(StreetViewPanorama panorama) {
                                                panorama.setPosition(latLng);

                                            }
                                        });                  }
                        })

                        .create();             //创建AlertDialog对象
                alert.show();

            }
        });

        */
    }




    public double getDistance(LatLng start,LatLng end){
        double lat1 = (Math.PI/180)*start.latitude;
        double lat2 = (Math.PI/180)*end.latitude;

        double lon1 = (Math.PI/180)*start.longitude;
        double lon2 = (Math.PI/180)*end.longitude;



        //地球半径
        double R = 6371;

        //两点间距离 km，如果想要米的话，结果*1000就可以了
        double d =  Math.acos(Math.sin(lat1)*Math.sin(lat2)+Math.cos(lat1)*Math.cos(lat2)*Math.cos(lon2-lon1))*R;

        return d;
    }


    @Override
    public void onMapClick(LatLng latLng) {

        /*MarkerOptions markerOptions = new MarkerOptions();

        markerOptions.position(latLng);
        markerOptions.title(latLng.latitude + " : " + latLng.longitude);
        mMap.clear();
        mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

        mMap.addMarker(markerOptions);
        */
        alert = null;
        builder = new AlertDialog.Builder(MapsActivity.this);
        alert = builder
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setMessage("You are "+getDistance(start,latLng)+"km away")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        start = new LatLng(latLng.latitude, latLng.longitude);
                        MarkerOptions markerOptions = new MarkerOptions();
                        markerOptions.position(latLng);
                        markerOptions.title(latLng.latitude + " : " + latLng.longitude);
                        mapFragment.googleMap.clear();
                        mapFragment.googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                        mapFragment.googleMap.addMarker(markerOptions);
                        /*streetViewPanoramaFragment.getStreetViewPanoramaAsync(
                                new OnStreetViewPanoramaReadyCallback() {
                                    @Override
                                    public void onStreetViewPanoramaReady(StreetViewPanorama panorama) {
                                        panorama.setPosition(latLng);

                                    }
                                });
                         */
                    }
                })

                .create();             //创建AlertDialog对象
        alert.show();


    }
}
