package com.example.checklistandsplit;

public class ListName {
    private String name, date;
    private boolean isCheck;

    public ListName(String name, String date, boolean isCheck) {
        this.name = name;
        this.date = date;
        this.isCheck = isCheck;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCheck(boolean isCheck) {
        this.isCheck = isCheck;
    }

    public String getName() {
        return  name;
    }

    public String getDate(){
        return date;
    }

    public boolean getCheck() {
        return isCheck;
    }
}
