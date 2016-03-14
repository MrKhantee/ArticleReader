package com.ftovaro.articlereader.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ftovaro.articlereader.R;
import com.ftovaro.articlereader.interfaces.WebURLLoaderListener;

/**
 * Fragment that manage the feature to navigate on Internet.
 * Created by FelipeTovar on 09-Mar-16.
 */
public class WebFragment extends Fragment implements WebURLLoaderListener {

    /** WebView that allows to navigate through Internet.**/
    private WebView webView;
    /** Default URL that is loaded **/
    private static final String DEFAULT_URL = "http://www.google.com";

    public WebFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_web, container, false);

        webView = (WebView) view.findViewById(R.id.webview);

        webView.setWebViewClient(new MyWebViewClient());
        webView.getSettings().setBuiltInZoomControls(false);
        webView.getSettings().setSupportZoom(false);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.loadUrl(DEFAULT_URL);

        return view;
    }

    public class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }
    }
    @Override
    public void loadURL(String url) {
        webView.loadUrl(url);
    }
}
