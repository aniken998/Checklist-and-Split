package com.example.checklistandsplit;

public class Duty {
    private String title, executor;
    private int isCheck;
    private String parent;
    public Duty() {

    }

    public Duty(int isCheck, String title, String executor, String parent) {
        this.title = title;
        this.executor = executor;
        this.isCheck = isCheck;
        this.parent = parent;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
       return title;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    public String getExecutor() {
        return executor;
    }


    public int getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(int isCheck) {
        this.isCheck = isCheck;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public boolean isChecked() {
        if(isCheck == 0) {
            return false;
        }
        else {
            return true;
        }
    }
}
