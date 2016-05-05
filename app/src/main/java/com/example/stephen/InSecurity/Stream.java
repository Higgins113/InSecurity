package com.example.stephen.InSecurity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class Stream extends AppCompatActivity
{
    Button streamButton;
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stream);

        streamButton = (Button) findViewById(R.id.streamButton);
    }

    public void stream(View view)
    {
        String url = "http://192.168.43.64:8080/?action=stream";
        webView = (WebView) findViewById(R.id.webView);
        streamButton.setVisibility(streamButton.GONE);

        webView.getSettings().setJavaScriptEnabled(true);
    }

}
