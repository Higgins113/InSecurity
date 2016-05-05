package com.example.stephen.InSecurity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;


public class Login extends AppCompatActivity implements View.OnClickListener
{

    EditText loginUsername;
    EditText loginPass;
    Button loginButton;
    Button registerButton;
    UserLocalStore userLocalStore;
    MyDBHandler dbHandler;
    Boolean check;
    String username,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        registerButton = (Button) findViewById(R.id.registerButton);
        loginUsername = (EditText) findViewById(R.id.registerUsername);
        loginPass = (EditText) findViewById(R.id.registerPass);
        loginButton = (Button) findViewById(R.id.loginButton);
        dbHandler = new MyDBHandler(this,null,null,2);
        loginButton.setOnClickListener(this);
        userLocalStore = new UserLocalStore(this);
    }


    public void register(View view)
    {
        startActivity(new Intent(this,Register.class));
    }

    @Override
    public void onClick(View v)
    {
        if (v.getId() == R.id.loginButton)
        {

            username = loginUsername.getText().toString();
            pass = loginPass.getText().toString();
            User user = new User(username, pass);
            check = authenticate(user);
            if (check == true)
            {
                Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_LONG).show();
                Intent i = new Intent(this, Pi.class);
                i.putExtra("user", username);
                startActivity(i);
            }

        }

    }
    private boolean authenticate(User user)
    {
        String username = dbHandler.databaseToString(user);
        String password = dbHandler.databaseToStringPassword(user);

        if(username!="" && password!="")
        {
            return true;
        }

        else
        {
            return false;
        }
    }

}
