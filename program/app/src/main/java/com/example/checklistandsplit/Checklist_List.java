package com.example.checklistandsplit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Checklist_List extends AppCompatActivity {
    List<BigList> bigLists;
    Mydb database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checklist_list);
        ListView listView = findViewById(R.id.checklist_list);
        database = new Mydb(this,"big_list", null, 1);
        bigLists = database.list();
//        bigLists = new ArrayList<>();
//        bigLists.add(new BigList("123", "456", "789"));
        Log.d("bigList size", bigLists.size() + "");
        Custom_BigList adapter = new Custom_BigList(this,R.layout.custom_big_list, bigLists);
        listView.setAdapter(adapter);



    }

    public void float_button(View view) {
        CardView addJob = (CardView) findViewById(R.id.add_job);
        addJob.setVisibility(View.VISIBLE);
        FloatingActionButton add = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        add.hide();

    }

    public void save(View view) {
        EditText todo = findViewById(R.id.todo);
        EditText date = findViewById(R.id.date);
        EditText time = findViewById(R.id.time);
        database = new Mydb(this,"big_list", null, 1);
        database.insert(todo.getText().toString(), date.getText().toString(), time.getText().toString());
        database.count();
        Intent intent = getIntent();
        finish();
        startActivity(intent);

    }

    public void back(View view) {
        EditText todo = findViewById(R.id.todo);
        EditText date = findViewById(R.id.date);
        EditText time = findViewById(R.id.time);
        todo.getText().clear();
        date.getText().clear();
        time.getText().clear();
        CardView addJob = (CardView) findViewById(R.id.add_job);
        addJob.setVisibility(View.INVISIBLE);
        FloatingActionButton add = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        add.show();
    }
}
