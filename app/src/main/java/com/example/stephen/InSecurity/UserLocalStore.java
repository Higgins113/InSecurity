package com.example.stephen.InSecurity;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Stephen on 20/03/2016.
 */
public class UserLocalStore
{
    public static final String SP_NAME = "userDetails";
    SharedPreferences userLocalDatabase;

    public UserLocalStore(Context context)
    {
        userLocalDatabase = context.getSharedPreferences(SP_NAME,0);
    }

    public void storeUserData(User user)
    {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putString("name",user.name);
        spEditor.putString("username",user.username);
        spEditor.putString("password",user.password);
        spEditor.putString("address",user.address);
        spEditor.commit();
    }

    public User getUser()
    {
        String name = userLocalDatabase.getString("name", "");
        String username = userLocalDatabase.getString("username","");
        String password = userLocalDatabase.getString("password","");
        String address = userLocalDatabase.getString("address","");

        User storedUser = new User(name,address,username,password);
        return storedUser;
    }

    public void setUserLoggedIn(boolean loggedIn)
    {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("LoggedIn", loggedIn);
        spEditor.commit();
    }

    public void clearUserData()
    {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.clear();
        spEditor.commit();
    }

    public boolean getUserLoggedIn(){
        if (userLocalDatabase.getBoolean("LoggedIn",false))
        {
            return true;
        }

        else
        {
            return false;
        }
    }



}
