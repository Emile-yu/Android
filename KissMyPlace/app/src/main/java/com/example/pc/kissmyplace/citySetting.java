package com.example.pc.kissmyplace;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Created by pc on 2018/1/24.
 */

public class citySetting {
    ArrayList<LatLng> cityData = new ArrayList<LatLng>();

    public citySetting(int lvl){
        if(lvl == 0){
            // Paris
            cityData.add(new LatLng(48.846550, 2.354547));
            cityData.add(new LatLng(51.507130, -0.127557));
            cityData.add(new LatLng(40.677743, -73.937062));

        }
        if(lvl == 1){
            // London
            cityData.add(new LatLng(48.846550, 2.354547));
            cityData.add(new LatLng(51.507130, -0.127557));
            cityData.add(new LatLng(40.677743, -73.937062));

        }
        if(lvl == 2){
            // New York City
            cityData.add(new LatLng(48.846550, 2.354547));
            cityData.add(new LatLng(51.507130, -0.127557));
            cityData.add(new LatLng(40.677743, -73.937062));

        }
    }

}
