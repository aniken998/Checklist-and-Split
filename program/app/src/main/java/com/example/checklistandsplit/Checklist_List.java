package com.example.checklistandsplit;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
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
import java.util.Map;

public class Checklist_List extends AppCompatActivity {
    private static final String TAG = "Checklist_List";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checklist_list);

        final ListView listView = findViewById(R.id.checklist_list);
        final DatabaseReference mReference = FirebaseDatabase.getInstance().getReference();
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        mReference.child("Users").child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Log.d(TAG, dataSnapshot.getValue(User.class).toString());
                User mUser = dataSnapshot.getValue(User.class);
                HashMap<String, BigList> hashList = mUser.getHost();
                final ArrayList<BigList> bigLists = new ArrayList<>(hashList.values());
                for(int i = 0; i < mUser.getCollaborator_list().size(); i++) {
                    final String listUser = mUser.getCollaborator_host().get(i);
                    final String listName = mUser.getCollaborator_list().get(i);
                    mReference.child("Users").child(listUser).child("host").child(listName).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            BigList list = dataSnapshot.getValue(BigList.class);
                            Log.d(TAG, list.toString());
                            bigLists.add(list);
                            Log.d(TAG, "Inner Size: " + bigLists.size());
                            Custom_BigList adapter = new Custom_BigList(Checklist_List.this, R.layout.custom_big_list, bigLists);
                            listView.setAdapter(adapter);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                if(mUser.getCollaborator_list().size() <= 0) {
                    Custom_BigList adapter = new Custom_BigList(Checklist_List.this, R.layout.custom_big_list, bigLists);
                    listView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("get List", "loadPost:onCancelled", databaseError.toException());
            }
        });
        //final Intent i = new Intent(this, DutyList.class);
        listView.setFocusable(false);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                BigList thisList = (BigList) listView.getItemAtPosition(position);
                String listName = thisList.getTitle();
                Intent i = new Intent(getApplicationContext(), DutyList.class);
                //Log.d("postion", "" + position);
                Bundle k = new Bundle();
                k.putString("list_name", listName);
                k.putBoolean("isHost",thisList.getHost() == user.getUid());
                k.putString("hostID", thisList.getHost());
                i.putExtras(k);
                startActivity(i);
            }
        });

    }

    public void float_button(View view) {
        CardView addJob = (CardView) findViewById(R.id.add_job);
        addJob.setVisibility(View.VISIBLE);
        FloatingActionButton add = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        add.hide();

    }

    public void refresh(View view) {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
    public void save(View view) {
        DatabaseReference mReference = FirebaseDatabase.getInstance().getReference();
        final FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        final EditText todo = findViewById(R.id.todo);
        final EditText date = findViewById(R.id.date);
        final EditText time = findViewById(R.id.time);
        mReference.child("Users").child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User mUser = (User) dataSnapshot.getValue(User.class);
                mUser.addHostBiglist(todo.getText().toString(),
                        new BigList(todo.getText().toString(), date.getText().toString(), time.getText().toString(),
                                    user.getUid()));
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

    public void back(View view) {
        EditText todo = findViewById(R.id.todo);
        EditText date = findViewById(R.id.date);
        EditText time = findViewById(R.id.time);
        todo.getText().clear();
        date.getText().clear();
        time.getText().clear();
        CardView addJob = (CardView) findViewById(R.id.add_job);
        addJob.setVisibility(View.INVISIBLE);
        FloatingActionButton add = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        add.show();
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
