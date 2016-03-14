package com.ftovaro.articlereader.interfaces;

import org.json.JSONObject;

/**
 * Manage the state of a request.
 * Created by FelipeTovar on 09-Mar-16.
 */
public interface StandardRequestListener {
    /**
     * Success in a volley request.
     * @param jsonObject    the response of the request.
     */
    void onSuccess(JSONObject jsonObject);

    /**
     * Failure in a volley request.
     * @param error      the error of the request.
     */
    void onFailure(String error);
}
