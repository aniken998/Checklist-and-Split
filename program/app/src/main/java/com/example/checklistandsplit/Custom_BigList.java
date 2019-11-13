package com.example.checklistandsplit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class Custom_BigList extends ArrayAdapter<BigList> {
    List<BigList> bigLists;
    Context context;
    int resource;
    Mydb database;
    public Custom_BigList(@NonNull Context context, int resource, List<BigList> bigLists) {
        super(context, resource, bigLists);
        this.context = context;
        this.resource = resource;
        this.bigLists = bigLists;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(resource,null,false);

        TextView title = view.findViewById(R.id.biglist_title);
        TextView date = view.findViewById(R.id.biglist_date);
        TextView time = view.findViewById(R.id.biglist_time);
        ImageView isCheck = view.findViewById(R.id.biglist_check);
        ImageButton delete = view.findViewById(R.id.biglist_delete);

        BigList list = bigLists.get(position);

        title.setText(list.getTitle());
        date.setText(list.getDate());
        time.setText(list.getTime());


        return view;
    }
}
