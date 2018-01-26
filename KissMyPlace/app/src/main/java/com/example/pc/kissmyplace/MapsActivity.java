package com.example.pc.kissmyplace;


import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import android.support.v7.app.AlertDialog;

import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.SupportStreetViewPanoramaFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback{

    private GoogleMap mMap;

    private LatLng start ;

    private citySetting citySetting;

    private applicationSetting applicationSetting;

    private GoogleApiClient googleApiClient;

    double tmpScoreLevel = 0;

    int current = 0;

    double highScore = 1000000;

    int lvl;

    List<LatLng> ListCity = new ArrayList<LatLng>();

    private AlertDialog dialog;
    private AlertDialog.Builder builder;
    private SupportStreetViewPanoramaFragment streetViewPanoramaFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_maps);


        //lvl = getIntent().getIntExtra("lvl",0);
        lvl = applicationSetting.getInstance().getSelectedLevel();

        citySetting = new citySetting(lvl);

        ListCity = citySetting.cityData;

        start = ListCity.get(current);


       // applicationSetting = new applicationSetting();






        //StreetView
        streetViewPanoramaFragment =
                (SupportStreetViewPanoramaFragment) getSupportFragmentManager().findFragmentById(R.id.streetviewpanorama);
        streetViewPanoramaFragment.getStreetViewPanoramaAsync(
                new OnStreetViewPanoramaReadyCallback() {
                    @Override
                    public void onStreetViewPanoramaReady(StreetViewPanorama panorama) {
                        panorama.setPosition(start);

                    }
                });


        //Mapview
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Setting a click event handler for the map
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(final LatLng latLng) {

                MarkerOptions markerOptions = new MarkerOptions();

                markerOptions.position(latLng);

                MarkerOptions markerOptionsdest = new MarkerOptions();

                markerOptionsdest.position(ListCity.get(current));

                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                mMap.clear();
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                mMap.addMarker(markerOptions);
                mMap.addMarker(markerOptionsdest);

                PolylineOptions lineOptions = new PolylineOptions();
                lineOptions.color(Color.RED);
                lineOptions.width(5);
                lineOptions.add(latLng);
                lineOptions.add(ListCity.get(current));
                mMap.addPolyline(lineOptions);

                dialog = null;
                builder = new AlertDialog.Builder(MapsActivity.this);
                dialog = builder
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setMessage("You are "+(int)getDistance(ListCity.get(current),latLng)+"km away")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                mMap.clear();

                                current++;
                                if (current > ListCity.size()-1) {

                                    EndGame();
                                } else {

                                    if(getDistance(ListCity.get(current),latLng) < 1000){
                                        tmpScoreLevel += (highScore - (1000 * getDistance(ListCity.get(current),latLng)));

                                        if(tmpScoreLevel <0 ){
                                            tmpScoreLevel = 0;
                                        }
                                    }

                                    streetViewPanoramaFragment.getStreetViewPanoramaAsync(
                                            (panorama) -> {
                                                panorama.setPosition(ListCity.get(current));
                                            });
                                }

                            }


                        })
                        .create();
                /*Window dialogWindow = alert.getWindow();
                WindowManager.LayoutParams lp = dialogWindow.getAttributes();
                dialogWindow.setGravity(Gravity.CENTER);
                */
                dialog.show();

            }
        });

    }

    public void EndGame(){

        //applicationSetting.setScore(lvl,tmpScoreLevel);
        dialog = null;
        builder = new AlertDialog.Builder(MapsActivity.this);
        dialog = builder
                .setTitle("Congratulation!!!")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setMessage("You finished the game ! ( Score : "+(int)tmpScoreLevel+" )")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        applicationSetting.getInstance().setScore(lvl,tmpScoreLevel);
                        applicationSetting.getInstance().setDate(lvl, Calendar.getInstance().getTime());
                        finish();
                    }


                })
                .create();
        dialog.show();

    }



    public double getDistance(LatLng start,LatLng end){
        double lat1 = (Math.PI/180)*start.latitude;
        double lat2 = (Math.PI/180)*end.latitude;

        double lon1 = (Math.PI/180)*start.longitude;
        double lon2 = (Math.PI/180)*end.longitude;

        double R = 6371;

        double d =  Math.acos(Math.sin(lat1)*Math.sin(lat2)+Math.cos(lat1)*Math.cos(lat2)*Math.cos(lon2-lon1))*R;

        return d;

    }



}
