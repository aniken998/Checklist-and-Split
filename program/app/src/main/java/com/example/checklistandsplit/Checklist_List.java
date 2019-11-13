package com.example.checklistandsplit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Checklist_List extends AppCompatActivity {
    List<BigList> bigLists;
    Mydb database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checklist_list);
        ListView listView = findViewById(R.id.checklist_list1);
        database = new Mydb(this,"big_list", null, 1);
        bigLists = database.list();
        //bigLists = new ArrayList<>();
        //bigLists.add(new BigList("123", "456", "789"));
        Custom_BigList adapter = new Custom_BigList(this,R.layout.custom_big_list, bigLists);
        listView.setAdapter(adapter);

    }

    public void float_button(View view) {
        Intent i = new Intent(this, Add_To_Do_List.class);
        startActivity(i);
    }
}
