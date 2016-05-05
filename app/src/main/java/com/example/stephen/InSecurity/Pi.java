package com.example.stephen.InSecurity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Pi extends AppCompatActivity{

    EditText powerText;
    EditText tempText;
    EditText alarmText;
    Button getData;
    String json_string;
    String username;
    Button streamButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pi);
        Intent intent = getIntent();
        username = intent.getStringExtra("user");

        powerText = (EditText) findViewById(R.id.power);
        tempText = (EditText) findViewById(R.id.temp);
        alarmText = (EditText) findViewById(R.id.alarm);
        getData = (Button) findViewById(R.id.getData);
        streamButton = (Button) findViewById(R.id.streamButton);
        new BackgroundTask().execute();

    }

    public void stream (View view)
    {
        Intent intent = new Intent(this,Stream.class);
        intent.putExtra("user", username);
        startActivity(intent);
    }

    public void getData (View view) throws JSONException
    {
        Intent intent = new Intent(this,DisplayListView.class);
        intent.putExtra("json_data",json_string);
        startActivity(intent);
    }

    public void control (View view)
    {
        Intent intent = new Intent(this,Control.class);
        intent.putExtra("user", username);
        startActivity(intent);
    }


    class BackgroundTask extends AsyncTask<Void,Void,String>
    {
        String json_url;
        String JSON_STRING;
        @Override
        protected void onPreExecute() {
            json_url = "https://webisam.eu-gb.mybluemix.net/json_get_pi.php";
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                while ((JSON_STRING = bufferedReader.readLine())!=null)
                {
                    stringBuilder.append(JSON_STRING+"\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {

            json_string = result;

        }
    }


}
