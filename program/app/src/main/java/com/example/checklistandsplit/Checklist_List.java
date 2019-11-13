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

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Checklist_List extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checklist_list);
    }

    public void float_button(View view) {
        CardView addJob = (CardView) findViewById(R.id.add_job);
        addJob.setVisibility(View.VISIBLE);
        FloatingActionButton add = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        add.hide();

    }

    public void save(View view) {
        CardView addJob = (CardView) findViewById(R.id.add_job);
        addJob.setVisibility(View.INVISIBLE);
        FloatingActionButton add = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        add.show();
    }
}
