package com.example.checklistandsplit;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.List;

public class Custom_DutyList extends ArrayAdapter<Duty> {
    List<Duty> dutyList;
    Context context;
    int resource;
    Duty list;
    CheckBox isCheck;
    Bundle k;
    Mydb database;
    String table_name;
    public Custom_DutyList(@NonNull Context context, int resource, List<Duty> dutyList) {
        super(context, resource, dutyList);
        this.context = context;
        this.resource = resource;
        this.dutyList = dutyList;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        SharedPreferences preferences = context.getSharedPreferences("MyPref", 0);
        SharedPreferences.Editor editor = preferences.edit();
        View view = convertView;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        if (view == null) {
            view = layoutInflater.inflate(resource, null, false);
        }
            //View view = layoutInflater.inflate(resource, null, false);
            TextView title = view.findViewById(R.id.duty_list_title);
            TextView executor = view.findViewById(R.id.duty_list_executor);
            isCheck = view.findViewById(R.id.duty_list_checkBox);
            database = new Mydb(context, preferences.getString("tableName", null), null, 1);
            //Log.d("table", preferences.getString("tableName", null));
            editor.clear();
            editor.commit();

            list = dutyList.get(position);
            title.setText(list.getTitle());
            executor.setText(list.getExecutor());
            Log.d("D", ""+ list.getCheck());
            isCheck.setChecked(list.getCheck());
            isCheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //
                    database.duty_list_insert(getItem(position).getTitle(), getItem(position).getExecutor(), isCheck.isChecked());
                    Toast.makeText(context, getItem(position).getTitle()+ " " + getItem(position).getExecutor() +" "+ isCheck.isChecked(), Toast.LENGTH_LONG).show();


                    //isCheck.setChecked(true);
                }
            });
            /*isCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    Log.d("D","" + getItem(position).getTitle());
                    database.duty_list_insert(getItem(position).getTitle(), getItem(position).getExecutor(), isCheck.isChecked());
                    Toast.makeText(context, "" + isCheck.isChecked(),Toast.LENGTH_LONG).show();
                    notifyDataSetChanged();
                }
            });*/




        return view;
    }

}
