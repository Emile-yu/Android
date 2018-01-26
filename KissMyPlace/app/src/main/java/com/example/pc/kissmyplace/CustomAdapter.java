package com.example.pc.kissmyplace;

/**
 * Created by pc on 2018/1/24.
 */

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class CustomAdapter extends BaseAdapter {
    Context context;


    List<String> scoreList = new ArrayList<String>();
    List<String> lvlList = new ArrayList<String>();
    List<String> dateList = new ArrayList<String>();
    //String scoreList[];
    //String lvlList[];
    //String dateList[];
    int flags[];
    LayoutInflater inflter;

    public CustomAdapter(Context applicationContext, List<String> scoreList, List<String> lvlList, List<String> dateList) {
        this.context = context;
        this.scoreList = scoreList;
        this.lvlList = lvlList;
        this.dateList = dateList;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return scoreList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = inflter.inflate(R.layout.statistic_item, null);

        TextView score = (TextView) view.findViewById(R.id.score);
        TextView level = (TextView) view.findViewById(R.id.lvl);
        TextView date = (TextView) view.findViewById(R.id.date);

        score.setText(scoreList.get(i));
        level.setText(lvlList.get(i));
        date.setText(dateList.get(i));

        return view;
    }
}