package com.example.android_project_14_web_view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_open_web_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        btn_open_web_view = findViewById(R.id.btn_open_web_view);
        btn_open_web_view.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_open_web_view:
                Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                intent.putExtra("url", "http:www.google.com");
                startActivity(intent);
                break;
        }
    }
}