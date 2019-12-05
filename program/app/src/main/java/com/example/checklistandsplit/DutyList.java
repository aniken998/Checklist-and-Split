package com.example.checklistandsplit;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.util.Pair;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DutyList extends AppCompatActivity {
    List<Duty> dutyList;
    Bundle k;
    private String thisListName;
    TextView current_member;
    static String added_members;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.duty_list);
        current_member = findViewById(R.id.current_mebmer);
        current_member.setText(added_members);
        final ListView listview = findViewById(R.id.duty_list);
        final DatabaseReference mReference = FirebaseDatabase.getInstance().getReference();
        final FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        k = getIntent().getExtras();
        if(k.getBoolean("isHost")) {
            mReference.child("Users").child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    k = getIntent().getExtras();
                    thisListName = k.getString("list_name");
                    User mUser = (User) dataSnapshot.getValue(User.class);
                    ArrayList<Duty> dutyList = mUser.getHost().get(thisListName).getDuties();
                    Custom_DutyList adapter = new Custom_DutyList(DutyList.this, R.layout.custom_duty_list, dutyList,k.getString("hostID"));
                    listview.setAdapter(adapter);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.w("Save Button", "loadPost:onCancelled", databaseError.toException());
                }
            });
        } else {
            mReference.child("Users").child(k.getString("hostID")).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    k = getIntent().getExtras();
                    thisListName = k.getString("list_name");
                    User mUser = (User) dataSnapshot.getValue(User.class);
                    ArrayList<Duty> dutyList = mUser.getHost().get(thisListName).getDuties();
                    Custom_DutyList adapter = new Custom_DutyList(DutyList.this, R.layout.custom_duty_list, dutyList, k.getString("hostID"));
                    listview.setAdapter(adapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.w("Save Button", "loadPost:onCancelled", databaseError.toException());
                }
            });
        }
    }

    public void duty_float_button(View view) {
        CardView addDuty= (CardView) findViewById(R.id.add_duty);
        addDuty.setVisibility(View.VISIBLE);
        FloatingActionButton add = (FloatingActionButton) findViewById(R.id.duty_floatingActionButton);
        FloatingActionButton member = (FloatingActionButton) findViewById(R.id.add_member_floatingActionButton);
        add.hide();
        member.hide();

    }
    public void add_member_float_button(View view) {
        CardView addMember = (CardView)findViewById(R.id.add_member);
        addMember.setVisibility(View.VISIBLE);
        FloatingActionButton member = (FloatingActionButton) findViewById(R.id.add_member_floatingActionButton);
        FloatingActionButton add = (FloatingActionButton) findViewById(R.id.duty_floatingActionButton);
        member.hide();
        add.hide();
    }
    public void duty_refresh(View view) {
        Intent i = getIntent();
        finish();
        startActivity(i);
    }
    public void member_add_button(View view) {
        final EditText member = findViewById(R.id.member_email);
        if(member.getText().toString().indexOf(".") < 0) {
            Toast.makeText(DutyList.this, "Not an Email", Toast.LENGTH_LONG).show();
            return;
        }
        final DatabaseReference mReference = FirebaseDatabase.getInstance().getReference();
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String username = member.getText().toString().substring(0, member.getText().toString().indexOf("."));
        mReference.child("Users-info").child(username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String newUser = dataSnapshot.getValue(String.class);
                //Toast.makeText(DutyList.this, newUser, Toast.LENGTH_LONG).show();
                if(newUser != null) {
                    mReference.child("Users").child(newUser).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            k = getIntent().getExtras();
                            thisListName = k.getString("list_name");
                            User mUser = (User) dataSnapshot.getValue(User.class);
                            mUser.addCollaborator(k.getString("hostID"), thisListName);
                            writeNewPost(mUser);
                            Toast.makeText(DutyList.this, "Add Scuess", Toast.LENGTH_LONG).show();
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Toast.makeText(DutyList.this, "Error-1", Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    Toast.makeText(DutyList.this, "No Such User", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(DutyList.this, "Error-2", Toast.LENGTH_LONG).show();
            }
        });
        added_members += member.getText().toString() + " ,";
        current_member.setText(added_members);
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }


    public void duty_save(View view) {
        final DatabaseReference mReference = FirebaseDatabase.getInstance().getReference();
        final FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        final EditText title = findViewById(R.id.new_duty_list_title);
        final EditText executor = findViewById(R.id.new_duty_list_executor);
        mReference.child("Users").child(k.getString("hostID")).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                k = getIntent().getExtras();
                thisListName = k.getString("list_name");
                User mUser = (User) dataSnapshot.getValue(User.class);
                mUser.getHost().get(thisListName).addDuty(
                        new Duty(0, title.getText().toString(),executor.getText().toString(), thisListName));
                writeNewPost(mUser);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("Save Button", "loadPost:onCancelled", databaseError.toException());
            }
        });
        Intent intent = getIntent();
        finish();
        startActivity(intent);

    }

    public void member_back(View view) {
        EditText member = findViewById(R.id.member_email);
        member.getText().clear();
        CardView addMember = (CardView) findViewById(R.id.add_member);
        addMember.setVisibility(view.INVISIBLE);
        FloatingActionButton add = (FloatingActionButton) findViewById(R.id.duty_floatingActionButton);
        FloatingActionButton member_button = (FloatingActionButton) findViewById(R.id.add_member_floatingActionButton);
        add.show();
        member_button.show();

    }

    public void duty_back(View view) {
        EditText todo = findViewById(R.id.new_duty_list_title);
        EditText date = findViewById(R.id.new_duty_list_executor);
        todo.getText().clear();
        date.getText().clear();
        CardView addDuty = (CardView) findViewById(R.id.add_duty);
        addDuty.setVisibility(View.INVISIBLE);
        FloatingActionButton add = (FloatingActionButton) findViewById(R.id.duty_floatingActionButton);
        FloatingActionButton member = (FloatingActionButton) findViewById(R.id.add_member_floatingActionButton);
        add.show();
        member.show();
    }

    public void writeNewPost(User user) {
        DatabaseReference mReference = FirebaseDatabase.getInstance().getReference();
        String key = mReference.child("Users").child(user.getUid()).getKey();
        Map<String, Object> postValues = user.toMap();
        Map<String, Object> childUpdates = new HashMap<>();

        childUpdates.put("/Users/" + key, postValues);
        mReference.updateChildren(childUpdates);
    }
}
