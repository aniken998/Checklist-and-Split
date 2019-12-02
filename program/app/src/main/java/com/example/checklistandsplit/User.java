package com.example.checklistandsplit;

import android.util.Pair;

import com.google.firebase.database.Exclude;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class User {
    private String email, uid;
    private HashMap<String, BigList> host = new HashMap<>();

    public User() {

    }

    public User(String email, String uid) {
        this.email = email;
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public HashMap<String, BigList> getHost() {
        return host;
    }


    public void setHost(HashMap<String, BigList> host) {
        this.host = host;
    }


    public void addHostBiglist(String name, BigList bigList) {
        host.put(name, bigList);
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("email", email);
        result.put("uid",uid);
        result.put("host",host);

        return result;
    }
}
