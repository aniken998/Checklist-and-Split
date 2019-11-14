package com.example.checklistandsplit;

public class Duty {
    private String title, executor;
    private boolean isCheck;

    public Duty(boolean isCheck, String title, String executor) {
        this.title = title;
        this.executor = executor;
        this.isCheck = isCheck;
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

    public void setCheck(boolean isCheck) {
        this.isCheck = isCheck;
    }

    public boolean getCheck() {
        return isCheck;
    }

}
