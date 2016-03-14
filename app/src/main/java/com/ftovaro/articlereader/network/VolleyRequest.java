package com.ftovaro.articlereader.network;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ftovaro.articlereader.interfaces.StandardRequestListener;

import org.json.JSONObject;

/**
 * Class that manage the request of information from services.
 * Created by FelipeTovar on 09-Mar-16.
 */
public class VolleyRequest {

    private static final String URL = "http://www.kogimobile.com/applicant-test/applicant%20test" +
            "%20feed.json";
    /**
     * Request a JSONObject from an URL.
     * @param listener  allows to capture the result of the request.
     */
    public static void requestArticles(final Context context, final StandardRequestListener listener){
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest request = new JsonObjectRequest(URL,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        listener.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listener.onFailure(error.toString());
                    }
                });
        queue.add(request);
    }
}
