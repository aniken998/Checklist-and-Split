package com.example.checklistandsplit;

import java.util.ArrayList;

public class BigList  {
     private String title, date, time;
    //int check;

    public BigList(String title, String date, String time) {
        this.title = title;
        //this.check = check;
        this.date = date;
        this.time = time;
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
}
