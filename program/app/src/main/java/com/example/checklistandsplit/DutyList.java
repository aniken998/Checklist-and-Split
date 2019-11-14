package com.example.checklistandsplit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class DutyList extends AppCompatActivity {
    Mydb database;
    List<Duty> dutyList;
    Bundle k;
    String table_name;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.duty_list);
        ListView listview = findViewById(R.id.duty_list);
        k = getIntent().getExtras();
        table_name = k.getString("table_name");
        database = new Mydb(this, table_name, null ,1);
        dutyList = database.dutylist();
        Custom_DutyList adapter = new Custom_DutyList(this,R.layout.custom_duty_list, dutyList);
        listview.setAdapter(adapter);


    }


    public void duty_float_button(View view) {
        CardView addDuty= (CardView) findViewById(R.id.add_duty);
        addDuty.setVisibility(View.VISIBLE);
        FloatingActionButton add = (FloatingActionButton) findViewById(R.id.duty_floatingActionButton);
        add.hide();

    }

    public void duty_save(View view) {
        EditText title = findViewById(R.id.new_duty_list_title);
        EditText executor = findViewById(R.id.new_duty_list_executor);
        database = new Mydb(this,table_name, null, 1);
        database.duty_list_insert(title.getText().toString(), executor.getText().toString(), false);
        database.count();
        Intent intent = getIntent();
        finish();
        startActivity(intent);

    }

    public void duty_back(View view) {
        EditText todo = findViewById(R.id.new_duty_list_title);
        EditText date = findViewById(R.id.new_duty_list_executor);
        todo.getText().clear();
        date.getText().clear();
        CardView addDuty = (CardView) findViewById(R.id.add_duty);
        addDuty.setVisibility(View.INVISIBLE);
        FloatingActionButton add = (FloatingActionButton) findViewById(R.id.duty_floatingActionButton);
        add.show();
    }
}
