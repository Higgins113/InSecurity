package com.example.stephen.InSecurity;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class   Register extends AppCompatActivity implements View.OnClickListener
{

    EditText registerUsername;
    EditText registerPass;
    EditText registerFirstName;
    EditText registerLastName;
    EditText registerMobile;
    Button registerButton;
    TextView deleteAll;
    MyDBHandler dbHandler;
    String firstName,lastName,username,password,mobile;
    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    boolean check;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerUsername = (EditText) findViewById(R.id.registerUsername);
        registerPass = (EditText) findViewById(R.id.registerPass);
        registerFirstName = (EditText) findViewById(R.id.registerFirstName);
        registerLastName = (EditText) findViewById(R.id.registerLastName);
        registerMobile = (EditText) findViewById(R.id.registerMobile);
        registerButton = (Button) findViewById(R.id.registerButton);
        deleteAll = (TextView) findViewById(R.id.deleteAll);
        dbHandler = new MyDBHandler(this,null,null,2);

        deleteAll.setOnClickListener(this);

    }


    public void addUser(View view)
    {
        username = registerUsername.getText().toString();


        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Are you sure you wish to register?");
        builder1.setCancelable(true);

        builder1.setPositiveButton
                (
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();

                            reg();

                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();

                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();

    }

    public void reg() {

        firstName = registerFirstName.getText().toString();
        lastName = registerLastName.getText().toString();
        password = registerPass.getText().toString();
        mobile = registerMobile.getText().toString();
        User user = new User(username, password);
        dbHandler.addUser(user);

        BackgroundTask backgroundTask = new BackgroundTask();
        backgroundTask.execute(firstName, lastName, username, password, mobile);
        finish();

    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.deleteAll) {
            check = userexists(jsonArray,username);
            if (check==true)
            {
                Toast.makeText(getApplicationContext(), "Username exists", Toast.LENGTH_LONG).show();
            }

            else
            {
                Toast.makeText(getApplicationContext(), "Username doesn't exist", Toast.LENGTH_LONG).show();
            }
        }

    }




    class BackgroundTask extends AsyncTask<String,Void,String>
    {

        String add_info_url;
        @Override
        protected void onPreExecute() {
            add_info_url= "https://webisam.eu-gb.mybluemix.net/add_info.php";
        }

        @Override
        protected String doInBackground(String... args) {

            String firstName,lastName,username,password,mobile;
            firstName = args[0];
            lastName = args[1];
            username = args[2];
            password = args[3];
            mobile = args[4];

            try {
                URL url = new URL(add_info_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data_string = URLEncoder.encode("fname", "UTF-8") + "=" + URLEncoder.encode(firstName, "UTF-8") + "&" +
                        URLEncoder.encode("lname", "UTF-8") + "=" + URLEncoder.encode(lastName, "UTF-8") + "&" +
                        URLEncoder.encode("mobile", "UTF-8") + "=" + URLEncoder.encode(mobile, "UTF-8") + "&" +
                        URLEncoder.encode("username","UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&" +
                        URLEncoder.encode("pass","UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
                ;
                bufferedWriter.write(data_string);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                inputStream.close();
                httpURLConnection.disconnect();
                return "Registered Succesfully";

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


    private boolean userexists(JSONArray jsonArray, String usernameToFind)
    {
        return jsonArray.toString().contains("\"username\":\""+usernameToFind+"\"");
    }

}


