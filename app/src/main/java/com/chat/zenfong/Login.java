package com.chat.zenfong;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    private TextInputEditText userName,passWord;
    private Button btnLogin, btnSettPass, btnChngPass;
    private SharedPreff sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userName = (TextInputEditText) findViewById(R.id.tie_username);
        passWord = (TextInputEditText) findViewById(R.id.tie_pass);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnSettPass = (Button) findViewById(R.id.btn_setpass);
        btnChngPass = (Button) findViewById(R.id.btn_changepass);

        sharedPref = new SharedPreff(Login.this);

        if (sharedPref.isFirstUse()){
            btnSettPass.setVisibility(View.VISIBLE);
            btnLogin.setVisibility(View.GONE);
        }else{
            btnLogin.setVisibility(View.VISIBLE);
            btnSettPass.setVisibility(View.GONE);
        }
        btnSettPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPref.setUsername(userName.getText().toString().trim());
                sharedPref.setPass(passWord.getText().toString().trim());
                sharedPref.setFirstUse(false);
                Intent intent = new Intent(Login.this, MainActivity.class);
                intent.putExtra("Username", sharedPref.getUsername());
                startActivity(intent);
                finish();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userName.getText().toString().trim().equalsIgnoreCase(sharedPref.getUsername())){
                    if (passWord.getText().toString().trim().equalsIgnoreCase(sharedPref.getPass())){
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        intent.putExtra("Username", sharedPref.getUsername());
                        startActivity(intent);
                        finish();
                    }else {
                        Toast.makeText(Login.this, "RONG PASSWORD", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(Login.this, "RONG USERNAME", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnChngPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, ChangePass.class));
            }
        });
    }
}
