package com.example.stephen.InSecurity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class Control extends AppCompatActivity {

    Button alarmButton;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);

        Intent intent = getIntent();
        username = intent.getStringExtra("user");
        alarmButton = (Button) findViewById(R.id.buttonAlarm);
    }

    public void alarmOn(View view)
    {
           String alarm = "on";
           AlarmTask alarmTask = new AlarmTask();
            alarmTask.execute(username,alarm);
            finish();
    }

    public void alarmOff(View view)
    {

        String alarm = "off";
        AlarmTask alarmTask = new AlarmTask();
        alarmTask.execute(username, alarm);
        finish();

    }


    public void powerOn(View view)
    {

        String power = "on";
        PowerTask powerTask = new PowerTask();
        powerTask.execute(username, power);
        finish();
    }

    public void powerOff(View view)
    {

        String power = "off";
        PowerTask powerTask = new PowerTask();
        powerTask.execute(username, power);
        finish();
    }

    public void heatingOn(View view)
    {
        String heating = "on";
        HeatingTask heatingTask = new HeatingTask();
        heatingTask.execute(username, heating);
        finish();
    }

    public void heatingOff(View view)
    {
        String heating = "off";
        HeatingTask heatingTask = new HeatingTask();
        heatingTask.execute(username, heating);;
        finish();
    }

    class HeatingTask extends AsyncTask<String,Void,String>
    {
        String alarm_url;
        @Override
        protected void onPreExecute() {
            alarm_url= "https://webisam.eu-gb.mybluemix.net/update_heating.php";
        }

        @Override
        protected String doInBackground(String... args) {
            String heatingTest,user;
            user = args[0];
            heatingTest= args[1];

            try {
                URL url = new URL(alarm_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data_string = URLEncoder.encode("heating", "UTF-8") + "=" + URLEncoder.encode(heatingTest, "UTF-8") + "&" +
                        URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(user, "UTF-8");
                bufferedWriter.write(data_string);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                inputStream.close();
                httpURLConnection.disconnect();
                return "Heating changed";

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
            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
        }


    }

    class PowerTask extends AsyncTask<String,Void,String>
    {
        String alarm_url;
        @Override
        protected void onPreExecute() {
            alarm_url= "https://webisam.eu-gb.mybluemix.net/update_power.php";
        }


        @Override
        protected String doInBackground(String... args) {
            String powerTest,user;
            user = args[0];
            powerTest= args[1];

            try {
                URL url = new URL(alarm_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data_string = URLEncoder.encode("power", "UTF-8") + "=" + URLEncoder.encode(powerTest, "UTF-8") + "&" +
                        URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(user, "UTF-8");
                bufferedWriter.write(data_string);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                inputStream.close();
                httpURLConnection.disconnect();
                return "Power changed";

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
            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
        }


    }

    class AlarmTask extends AsyncTask<String,Void,String>
    {
        String alarm_url;
        @Override
        protected void onPreExecute() {
            alarm_url= "https://webisam.eu-gb.mybluemix.net/update_alarm.php";
        }

        @Override
        protected String doInBackground(String... args) {
            String alarmTest,user;
            user = args[0];
            alarmTest= args[1];

            try {
                URL url = new URL(alarm_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data_string =  URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(user, "UTF-8") + "&" +
                        URLEncoder.encode("alarm", "UTF-8") + "=" + URLEncoder.encode(alarmTest, "UTF-8");

                bufferedWriter.write(data_string);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                inputStream.close();
                httpURLConnection.disconnect();
                return "Alarm changed";

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
            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
        }


    }

}
