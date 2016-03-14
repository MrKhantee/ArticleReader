package com.ftovaro.articlereader.util;

import android.util.Log;

import com.ftovaro.articlereader.model.Article;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Manage the parse of information.
 * Created by FelipeTovar on 09-Mar-16.
 */
public class JsonParser {

    /** Part of the URL necessary for download images from the server **/
    private static final String URL_HEADER = "http://www.kogimobile.com/applicant-test";

    /**
     * Parse the response of a service in an article.
     * @param jsonObject    response from a service.
     * @return  a list of articles.
     */
    public static List<Article> parseResponse(JSONObject jsonObject){
        List<Article> articles = new ArrayList<>();
        Article article;
        try {
            JSONArray jsonArrayData = jsonObject.getJSONArray("data");
            for(int i = 0; i < jsonArrayData.length(); i++){
                JSONObject articleObject = jsonArrayData.getJSONObject(i);
                article = new Article.ArticleBuilder()
                        .content(articleObject.getString("article_content"))
                        .url(articleObject.getString("article_url"))
                        .imageURL(URL_HEADER + articleObject.getString("article_imageurl"))
                        .build();
                articles.add(article);
            }
        }catch (JSONException error){
            Log.d("Error", error.getMessage());
        }
        return articles;
    }
}
