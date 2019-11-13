package com.example.checklistandsplit;

public class BigList  {
    String title, date, time;
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

    //public int getCheck() {
     //   return check;
    //}

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}
