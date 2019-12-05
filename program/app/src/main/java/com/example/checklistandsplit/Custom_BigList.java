package com.example.checklistandsplit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class Custom_BigList extends ArrayAdapter<BigList> {
    List<BigList> bigLists;
    Context context;
    int resource;
    public Custom_BigList(@NonNull Context context, int resource, List<BigList> bigLists) {
        super(context, resource, bigLists);
        this.context = context;
        this.resource = resource;
        this.bigLists = bigLists;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        String title = getItem(position).getTitle();
        String date = getItem(position).getDate();
        String time = getItem(position).getTime();
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(resource, parent, false);

        TextView tvTitle = convertView.findViewById(R.id.biglist_title);
        TextView tvDate = convertView.findViewById(R.id.biglist_date);
        TextView tvTime = convertView.findViewById(R.id.biglist_time);
        CheckBox isCheck = convertView.findViewById(R.id.biglist_check);

        tvTitle.setText(title);
        tvDate.setText(date);
        tvTime.setText(time);
        isCheck.setChecked(getItem(position).isCompleted());

        return convertView;
    }

}
