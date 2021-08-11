package com.example.android_project_14_web_view;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class WebViewActivity extends AppCompatActivity implements View.OnClickListener {

    private String url;
    private ViewGroup container;
    private WebView webView;
    private ImageView img_back;
    private ProgressBar progress_bar;
    private TextView txt_title;
    private TextView txt_sub_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        initViews();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            url = bundle.getString("url");
            setupWebView();
        }
    }

    private void setupWebView() {
        webView.loadUrl(url);
        txt_sub_title.setText(webView.getUrl());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progress_bar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progress_bar.setVisibility(View.GONE);
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                progress_bar.setProgress(newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                txt_sub_title.setText(title);
            }
        });
    }

    private void initViews() {
        container = findViewById(R.id.container);
        progress_bar = findViewById(R.id.progress_bar);
        img_back = findViewById(R.id.img_back);
        txt_title = findViewById(R.id.txt_title);
        txt_sub_title = findViewById(R.id.txt_sub_title);

        webView = new WebView(this);
        container.addView(webView);
        img_back.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;
        }
    }
}