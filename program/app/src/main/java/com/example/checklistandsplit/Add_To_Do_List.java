package com.example.checklistandsplit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Add_To_Do_List extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_list);
    }

    public void save(View view) {
        SharedPreferences sp = this.getSharedPreferences("title", Context.MODE_PRIVATE);
        String title = sp.getString("titles", "");
        EditText todo = findViewById(R.id.todo);
        EditText date = findViewById(R.id.date);
        EditText time = findViewById(R.id.time);
        if(title.equals("")) {
            title = title + todo.getText().toString() + "`" + date.getText().toString() + "`" + time.getText().toString();
            Log.d("D", title);
            sp.edit().clear().commit();
            sp.edit().putString("titles",title).apply();
            this.finish();
        } else {
            title = title + "_" + todo.getText().toString() + "`" + date.getText().toString() + "`" + time.getText().toString();
            Log.d("D",title);
            sp.edit().clear().commit();
            sp.edit().putString("titles",title).apply();
            this.finish();
        }
        //Intent i = new Intent(this,Checklist_List.class);
        //startActivity(i);

    }
}
