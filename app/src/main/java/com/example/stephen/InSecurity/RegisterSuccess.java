package com.example.stephen.InSecurity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterSuccess extends AppCompatActivity implements View.OnClickListener{

    EditText databaseName;
    MyDBHandler dbHandler;
    Button backRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_success);
        backRegister = (Button) findViewById(R.id.backRegister);
        dbHandler = new MyDBHandler(this,null,null,2);
        databaseName = (EditText) findViewById(R.id.databaseName);
        backRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        if (v.getId() == R.id.backRegister)
        {
            setContentView(R.layout.activity_register);
        }

        if (v.getId() == R.id.backLogin)
        {
            setContentView(R.layout.activity_login);

        }
    }


}
