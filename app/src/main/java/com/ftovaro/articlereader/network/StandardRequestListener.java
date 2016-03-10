package com.ftovaro.articlereader.network;

import org.json.JSONObject;

/**
 * Created by ftova on 09-Mar-16.
 */
public interface StandardRequestListener {
    void onSuccess(JSONObject jsonObject);
    void onFailure(String error);
}
