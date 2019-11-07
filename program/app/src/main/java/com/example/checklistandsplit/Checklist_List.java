package com.example.checklistandsplit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Checklist_List extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checklist_list);
    }

    public void float_button(View view) {
        Intent i = new Intent(this, Add_To_Do_List.class);
        startActivity(i);
    }
}
