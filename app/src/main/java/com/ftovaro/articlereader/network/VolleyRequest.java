package com.ftovaro.articlereader.network;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

/**
 * Created by ftova on 09-Mar-16.
 */
public class VolleyRequest {

    public static void requestArticles(final Context context, final StandardRequestListener listener){
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "http://www.kogimobile.com/applicant-test/applicant%20test%20feed.json";
        JsonObjectRequest request = new JsonObjectRequest(url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("response", response.toString());
                        listener.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("response", error.toString());
                        listener.onFailure(error.toString());
                    }
                });
        queue.add(request);
        //AppController.getInstance().addToRequestQueue(jsonObjReq);
    }
}
