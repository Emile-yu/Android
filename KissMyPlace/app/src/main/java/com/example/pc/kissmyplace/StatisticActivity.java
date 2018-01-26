package com.example.pc.kissmyplace;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class StatisticActivity extends ListActivity implements View.OnClickListener {

    List<String> scoreList = new ArrayList<String>();
    List<String> lvlList = new ArrayList<String>();
    List<String> dateList = new ArrayList<String>();
    String score = null;
    String lvl = null;
    String date = null;

    Button save;
    Button charger;
    ListView listView;
    CustomAdapter customAdapter;

    AlertDialog dialog;
    AlertDialog.Builder builder;
    //List lvlList[];
   // List dateList[];

    //applicationSetting appSetting = new applicationSetting();
    //applicationSetting test = appSetting.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);

        save = (Button) findViewById(R.id.save);
        save.setOnClickListener(this);

        charger = (Button) findViewById(R.id.charger);
        charger.setOnClickListener(this);

        int idx = 0;
        for(idx=0; idx<3; idx++){
            if(applicationSetting.getInstance().getData(idx) >= 0) {

                scoreList.add(Integer.toString(applicationSetting.getInstance().getData(idx)));

                score = Integer.toString(applicationSetting.getInstance().getData(idx));
                switch(idx){
                    case 0:
                        lvlList.add("novice");
                        lvl = "novice";
                        break;
                    case 1:
                        lvlList.add("medium");
                        lvl = "medium";
                        break;
                    case 2:
                        lvlList.add("expert");
                        lvl = "expert";
                        break;
                    default:break;
                }

                SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                dateList.add(df.format(applicationSetting.getInstance().getDate(idx)));
            }
        }

        listView = (ListView) findViewById(android.R.id.list);
        customAdapter = new CustomAdapter(getApplicationContext(), scoreList, lvlList, dateList);
        listView.setAdapter(customAdapter);


        /*try {
            InputStream input = openFileInput("score.txt");
            if(input != null){
                InputStreamReader inputSR = new InputStreamReader(input);
                BufferedReader bufferedReader = new BufferedReader(inputSR);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        */

    }

    @Override
    public void onClick(View view) {
        Button v = (Button) view;
        if(v.getId() == R.id.save) {
            dialog = null;
            builder = new AlertDialog.Builder(StatisticActivity.this);
            dialog = builder
                    .setMessage("Save data!!!")
                    .setIcon(android.R.drawable.ic_dialog_info)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            try {
                                FileOutputStream fos = openFileOutput("score.txt", Context.MODE_PRIVATE);
                                String info = score + " " + lvl + " " + date + " ";
                                fos.write(info.getBytes());
                                fos.close();
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    })
                    .create();
            dialog.show();
        }else{
            try {
                InputStream input = openFileInput("score.txt");
                if(input != null){
                    InputStreamReader inputSR = new InputStreamReader(input);
                    BufferedReader bufferedReader = new BufferedReader(inputSR);
                    String receiveString="";
                    StringBuilder stringBuilder = new StringBuilder();
                    while((receiveString=bufferedReader.readLine())!=null){
                        stringBuilder.append(receiveString);
                    }
                    input.close();
                    save.setText(stringBuilder.toString());

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
