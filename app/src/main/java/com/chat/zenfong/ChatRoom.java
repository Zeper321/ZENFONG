package com.chat.zenfong;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ChatRoom extends AppCompatActivity {
    private TextView tvMsg;
    private Button btnSnd;
    private EditText edtMsg;
    private String userName, roomName;
    private DatabaseReference root;
    private String temp_key;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        tvMsg = (TextView) findViewById(R.id.textView);
        btnSnd = (Button) findViewById(R.id.button2);
        edtMsg = (EditText) findViewById(R.id.editText);

        userName = getIntent().getStringExtra("user_name");
        roomName = getIntent().getStringExtra("room_name");

        root = FirebaseDatabase.getInstance().getReference().child(roomName);

        btnSnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> map = new HashMap<String, Object>();
                temp_key = root.push().getKey();
                root.updateChildren(map);
                DatabaseReference msg_root = root.child(temp_key);


                Map<String, Object> msgMap = new HashMap<>();
                msgMap.put("name", userName);
                msgMap.put("msg", edtMsg.getText().toString());
                msg_root.updateChildren(msgMap);
            }
        });

        root.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                broadCastMsg(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                broadCastMsg(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                broadCastMsg(dataSnapshot);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                broadCastMsg(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void broadCastMsg(DataSnapshot dataSnapshot) {
        Iterator i = dataSnapshot.getChildren().iterator();
        String name, msg;
        while (i.hasNext()){

            name = (String) ((DataSnapshot)i.next()).getValue();
            msg = (String) ((DataSnapshot)i.next()).getValue();
            tvMsg.append(name + " : " + msg + "\n");
        }

    }

}
