package com.ftovaro.articlereader.interfaces;

/**
 * Sends a url to be loaded for the WebView.
 * Created by FelipeTovar on 10-Mar-16.
 */
public interface WebURLSenderListener {
    /**
     * Send a url that can be loaded in a WebView.
     * @param url   the url to send.
     */
    void sendURL(String url);
}
