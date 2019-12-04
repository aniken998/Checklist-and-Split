package com.example.checklistandsplit;

import java.util.ArrayList;

public class BigList  {
     private String title, date, time;
     private int complete = 0;
     private ArrayList<Duty> duties = new ArrayList<>();
     private String host;
    //int check;

    public BigList() {
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public BigList(String title, String date, String time, String host) {
        this.title = title;
        //this.check = check;
        this.date = date;
        this.time = time;
        this.host = host;
    }

    public String getTitle(){
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getComplete() {
        return complete;
    }

    public void setComplete(int complete) {
        this.complete = complete;
    }

    public void addComplete() {
        ++complete;
    }

    public void minusComplete() {
        --complete;
    }

    public boolean isCompleted() {
        if(complete == duties.size() && complete != 0) {
            return true;
        }
        return false;
    }

    public ArrayList<Duty> getDuties() {
        return duties;
    }

    public void setDuties(ArrayList<Duty> duties) {
        this.duties = duties;
    }

    public void addDuty(Duty duty) {
        duties.add(duty);
    }

}
