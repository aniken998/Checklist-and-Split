package com.example.checklistandsplit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    Mydb database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void sign_in(View view){
        Intent i = new Intent(this, Checklist_List.class);
        database = new Mydb(this,"big_list_table", null, 1);
        database.list();
        startActivity(i);
    }

    public void sign_up(View view) {
        Intent i = new Intent(this,SignupActivity.class);
        startActivity(i);
    }

}

