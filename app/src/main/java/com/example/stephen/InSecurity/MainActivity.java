package com.example.stephen.InSecurity;

// Third year project for Home Security Android App.
// Group Members - Stephen Higgins, Brian Hennessy, Sean Ahearne, Isam Brahim


import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity
{

        private final int SPLASH_DISPLAY_LENGTH = 1500;

        @Override
        public void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        Intent mainIntent = new Intent(MainActivity.this,Login.class);
                        MainActivity.this.startActivity(mainIntent);
                        MainActivity.this.finish();
                    }

                }, SPLASH_DISPLAY_LENGTH);
    }

}



