package com.chat.zenfong;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ChangePass extends AppCompatActivity {

    private TextInputEditText tieOldUser, tieOldPass, tieNewUser, tieNewPass;
    private Button btnSetNewPass;
    private SharedPreff sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);

        sharedPref = new SharedPreff(ChangePass.this);
        tieOldUser = (TextInputEditText) findViewById(R.id.tie_old_username);
        tieOldPass = (TextInputEditText) findViewById(R.id.tie_old_pass);
        tieNewUser = (TextInputEditText) findViewById(R.id.tie_new_username);
        tieNewPass = (TextInputEditText) findViewById(R.id.tie_new_pass);
        btnSetNewPass = (Button) findViewById(R.id.btn_set_new_pass);

        btnSetNewPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tieOldUser.getText().toString().trim().equalsIgnoreCase(sharedPref.getUsername())){
                    if (tieOldPass.getText().toString().trim().equalsIgnoreCase(sharedPref.getPass())){
                        //success
                        sharedPref.setUsername(tieNewUser.getText().toString().trim());
                        sharedPref.setPass(tieNewPass.getText().toString().trim());
                        Toast.makeText(ChangePass.this, "Operation successful", Toast.LENGTH_SHORT).show();
                        tieOldUser.setText("");
                        tieOldPass.setText("");
                        tieNewUser.setText("");
                        tieNewPass.setText("");
                    }else {
                        Toast.makeText(ChangePass.this, " Rong password", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(ChangePass.this, "Rong Username", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
