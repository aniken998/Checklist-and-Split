package com.example.checklistandsplit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Add_To_Do_List extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_list);
    }

    public void save(View view) {
        Intent i = new Intent(this,Checklist_List.class);
        startActivity(i);

    }
}
