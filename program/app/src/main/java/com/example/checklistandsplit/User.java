package com.example.checklistandsplit;

import androidx.core.util.Pair;

import com.google.firebase.database.Exclude;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class User {
    private String email, uid;
    private HashMap<String, BigList> host = new HashMap<>();
    private ArrayList<String> collaborator_host = new ArrayList<>();
    private ArrayList<String> collaborator_list = new ArrayList<>();

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

    public ArrayList<String> getCollaborator_host() {
        return collaborator_host;
    }

    public void setCollaborator_host(ArrayList<String> collaborator_host) {
        this.collaborator_host = collaborator_host;
    }

    public ArrayList<String> getCollaborator_list() {
        return collaborator_list;
    }

    public void setCollaborator_list(ArrayList<String> collaborator_list) {
        this.collaborator_list = collaborator_list;
    }

    public void addHostBiglist(String name, BigList bigList) {
        host.put(name, bigList);
    }

    public void addCollaborator(String name, String list) {
        collaborator_host.add(name);
        collaborator_list.add(list);
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
        result.put("collaborator_host", collaborator_host);
        result.put("collaborator_list", collaborator_list);
        return result;
    }
}
