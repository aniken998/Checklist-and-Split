package com.example.checklistandsplit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void sign_in(View view){
        Intent i = new Intent(this, Checklist_List.class);
        startActivity(i);
    }

    public void sign_up(View view) {
        Intent i = new Intent(this,SignupActivity.class);
        startActivity(i);
    }

}

