package com.ftovaro.articlereader.interfaces;

/**
 * Delivers the url to be loaded for the WebView.
 * Created by FelipeTovar on 13-Mar-16.
 */
public interface WebURLLoaderListener {
    /**
     * Load a url that can be displayed in a WebView.
     * @param url   the url to load.
     */
    void loadURL(String url);
}
