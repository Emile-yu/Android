package com.example.pc.kissmyplace;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button b1;
    Button b2;
    Button b3;
    Button data;
    private applicationSetting appSetting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appSetting = applicationSetting.getInstance();

        b1 = (Button) findViewById(R.id.lvl0);
        b1.setOnClickListener(this);

        b2 = (Button) findViewById(R.id.lvl1);
        b2.setOnClickListener(this);

        b3 = (Button) findViewById(R.id.lvl2);
        b3.setOnClickListener(this);

        data = (Button) findViewById(R.id.data);
        data.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Button b = (Button) view;
        if(b.getId() == R.id.lvl0){
            Intent intent = new Intent(MainActivity.this,MapsActivity.class);
            appSetting.setSelectedLevel(0);
            startActivity(intent);

        }
        if(b.getId() == R.id.lvl1){
            Intent intent = new Intent(MainActivity.this,MapsActivity.class);
            appSetting.setSelectedLevel(1);

            startActivity(intent);


        }
        if(b.getId() == R.id.lvl2){
            Intent intent = new Intent(MainActivity.this,MapsActivity.class);
            appSetting.setSelectedLevel(2);
            startActivity(intent);

        }

        if(b.getId() == R.id.data){
            Intent intent = new Intent(MainActivity.this,StatisticActivity.class);
            startActivity(intent);
        }


    }
}
