package com.example.checklistandsplit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class Custom_DutyList extends ArrayAdapter<Duty> {
    List<Duty> dutyList;
    Context context;
    int resource;

    public Custom_DutyList(@NonNull Context context, int resource, List<Duty> dutyList) {
        super(context, resource, dutyList);
        this.context = context;
        this.resource = resource;
        this.dutyList = dutyList;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(resource, null, false);
        TextView title = view.findViewById(R.id.duty_list_title);
        TextView executor = view.findViewById(R.id.duty_list_executor);
        //CheckBox isCheck = view.findViewById(R.id.duty_list_checkBox);

        Duty list = dutyList.get(position);
        title.setText(list.getTitle());
        executor.setText(list.getExecutor());
       // isCheck.setlist.getCheck();

        return view;
    }



}
