package com.chat.zenfong;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {
    private ListView cineGroupChat;
    private EditText editText;
    private Button btnzenfong;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> array321 = new ArrayList<>();
    private String username;

    private DatabaseReference root = FirebaseDatabase.getInstance().getReference().getRoot();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = getIntent().getStringExtra("Username");

        cineGroupChat = (ListView) findViewById(R.id.zen);
        editText = (EditText) findViewById(R.id.fong);
        btnzenfong = (Button) findViewById(R.id.zenfong);

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,array321);
        cineGroupChat.setAdapter(arrayAdapter);

        btnzenfong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String,Object> map = new HashMap<String, Object>();
                map.put(editText.getText().toString(),"");
                root.updateChildren(map);
                editText.setText("");
            }
        });
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Set<String> set = new HashSet<String>();
                Iterator terirator = dataSnapshot.getChildren().iterator();
                while (terirator.hasNext()){
                    set.add(((DataSnapshot)terirator.next()).getKey());
                }
                array321.clear();
                array321.addAll(set);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        cineGroupChat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, ChatRoom.class);
                intent.putExtra("room_name", ((TextView)view).getText().toString());
                intent.putExtra("user_name", username);
                startActivity(intent);
            }
        });
    }
}
