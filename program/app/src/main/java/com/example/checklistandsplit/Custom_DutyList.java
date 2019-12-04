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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Custom_DutyList extends ArrayAdapter<Duty> {
    List<Duty> dutyList;
    Context context;
    int resource;
    Duty thisDuty;
    CheckBox isCheck;
    String hostID;
    public Custom_DutyList(@NonNull Context context, int resource, List<Duty> dutyList, String hostID) {
        super(context, resource, dutyList);
        this.context = context;
        this.resource = resource;
        this.dutyList = dutyList;
        this.hostID = hostID;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        if (view == null) {
            view = layoutInflater.inflate(resource, null, false);
        }
            //View view = layoutInflater.inflate(resource, null, false);
            TextView title = view.findViewById(R.id.duty_list_title);
            TextView executor = view.findViewById(R.id.duty_list_executor);
            isCheck = view.findViewById(R.id.duty_list_checkBox);
            //Log.d("table", preferences.getString("tableName", null));
            thisDuty = dutyList.get(position);
            title.setText(thisDuty.getTitle());
            executor.setText(thisDuty.getExecutor());
            isCheck.setChecked(thisDuty.isChecked());
            isCheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    updateCheck(position);
                }
            });


        return view;
    }

    public void writeNewPost(User user) {
        DatabaseReference mReference = FirebaseDatabase.getInstance().getReference();
        String key = mReference.child("Users").child(user.getUid()).getKey();
        Map<String, Object> postValues = user.toMap();
        Map<String, Object> childUpdates = new HashMap<>();

        childUpdates.put("/Users/" + key, postValues);
        mReference.updateChildren(childUpdates);
    }

    public void updateCheck(final int position) {
        final DatabaseReference mReference = FirebaseDatabase.getInstance().getReference();
        final FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        mReference.child("Users").child(hostID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User mUser = (User) dataSnapshot.getValue(User.class);
                BigList thisList = mUser.getHost().get(thisDuty.getParent());
                int check =  thisList.getDuties().get(position).getIsCheck();
                if(check == 0) {
                    thisList.getDuties().get(position).setIsCheck(1);
                    thisList.addComplete();
                } else {
                    thisList.getDuties().get(position).setIsCheck(0);
                    thisList.minusComplete();
                }
                writeNewPost(mUser);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("Save Button", "loadPost:onCancelled", databaseError.toException());
            }
        });
    }

}
