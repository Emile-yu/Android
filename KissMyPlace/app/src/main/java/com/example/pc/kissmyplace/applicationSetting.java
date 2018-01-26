package com.example.pc.kissmyplace;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by pc on 2018/1/23.
 */

 public class applicationSetting {


    private static applicationSetting INSTANCE = null;
    //public static applicationSetting applicationSetting = new applicationSetting();

    HashMap<Integer,Double> data;
    List<Date> date;
    int selectedLevel;

    private applicationSetting(){

        /*
        data = new HashMap<Integer,Double>();
        INSTANCE.data = new HashMap<Integer,Double>();
       // this.data.put(0,-1.0);
       // this.data.put(1,-1.0);
       // this.data.put(2,-1.0);

        INSTANCE.data.put(0,1.0);
        INSTANCE.data.put(1,1.0);
        INSTANCE.data.put(2,1.0);*/
    }

    public void setScore(int lvl,double score){
        INSTANCE.data.put(lvl,score);
    }

    public HashMap<Integer,Double> getStatistic(){
        return INSTANCE.data;
    }


    public int getData(int lvl) {
            return INSTANCE.data.get(lvl).intValue();
    }

    public void setDate(int lvl, Date date){
        INSTANCE.date.add(lvl, date);
    }

    public Date getDate(int lvl){
        return INSTANCE.date.get(lvl);
    }

    public static applicationSetting getInstance(){

        if(INSTANCE == null) {
            INSTANCE = new applicationSetting();
            INSTANCE.data = new HashMap<Integer, Double>();
            INSTANCE.date = new ArrayList<Date>();

            INSTANCE.date.add(0, new Date());
            INSTANCE.date.add(1, new Date());
            INSTANCE.date.add(2, new Date());

            INSTANCE.data.put(0,-1.0);
            INSTANCE.data.put(1,-1.0);
            INSTANCE.data.put(2,-1.0);

        }

        return INSTANCE;
    }


    public int getSelectedLevel() {
        return selectedLevel;
    }

    public void setSelectedLevel(int selectedLevel) {
        this.selectedLevel = selectedLevel;
    }
}
