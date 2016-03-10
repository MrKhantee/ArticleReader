package com.ftovaro.articlereader.fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.ftovaro.articlereader.R;
import com.ftovaro.articlereader.adapters.ArticlesAdapter;
import com.ftovaro.articlereader.model.Article;
import com.ftovaro.articlereader.network.AppController;
import com.ftovaro.articlereader.network.StandardRequestListener;
import com.ftovaro.articlereader.network.VolleyRequest;
import com.ftovaro.articlereader.util.InfoShower;
import com.ftovaro.articlereader.util.JsonParser;
import com.ftovaro.articlereader.util.SetUpHighlightArticle;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeedFragment extends Fragment {

    private List<Article> articlesList = new ArrayList<>();
    private RecyclerView recyclerView;
    private Article firstArticle;
    private ArticlesAdapter articlesAdapter;
    private TextView highlightContent;
    private NetworkImageView highlightImage;
    private ImageLoader imageLoader;

    public FeedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_feed, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        articlesAdapter = new ArticlesAdapter(articlesList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(articlesAdapter);

        highlightContent = (TextView) view.findViewById(R.id.highlightContent);
        highlightImage = (NetworkImageView) view.findViewById(R.id.highlightImage);
        imageLoader = AppController.getInstance().getImageLoader();

        downloadArticle();

        return view;
    }

    private void downloadArticle(){
        InfoShower.showDialog(getActivity());
        VolleyRequest.requestArticles(getActivity(), new StandardRequestListener() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                articlesList.addAll(JsonParser.parseResponse(jsonObject));
                firstArticle = articlesList.get(0);
                updateData(firstArticle);
                articlesList.remove(0);
                articlesAdapter.notifyDataSetChanged();
                InfoShower.hideDialog();
            }

            @Override
            public void onFailure(String error) {
                InfoShower.hideDialog();
            }
        });
    }

    public void updateData(Article article) {
        highlightContent.setText(article.getContent());
        highlightImage.setImageUrl(article.getImageURL(), imageLoader);
        highlightImage.setDefaultImageResId(R.drawable.ic_image);
    }
}
