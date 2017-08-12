package com.chat.zenfong;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by pc4 on 8/11/2017.
 */

public class SharedPreff {
    private SharedPreferences sp;
    private SharedPreferences.Editor spe;
    private Context c;

    public SharedPreff(Context c) {
        this.c = c;
        this.sp = c.getSharedPreferences("MyChatPref", Context.MODE_PRIVATE);
        this.spe = sp.edit();
    }

    public boolean isLogin(){
        return sp.getBoolean("Login", false);
    }

    public void setLogin(boolean login){
        spe.putBoolean("Login", login);
        spe.commit();
    }

    public boolean isFirstUse(){
        return sp.getBoolean("FirstUse", true);
    }

    public void setFirstUse(boolean firstUse){
        spe.putBoolean("FirstUse", firstUse);
        spe.commit();
    }

    public String getUsername(){
        return sp.getString("Username", "user");
    }

    public void setUsername(String username){
        spe.putString("Username", username);
        spe.commit();
    }

    public String getPass(){
        return sp.getString("Pass","password");
    }

    public void setPass(String pass){
        spe.putString("Pass", pass);
        spe.commit();
    }


}
