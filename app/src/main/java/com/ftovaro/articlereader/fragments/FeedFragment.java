package com.ftovaro.articlereader.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ftovaro.articlereader.R;
import com.ftovaro.articlereader.adapters.ArticlesAdapter;
import com.ftovaro.articlereader.model.Article;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeedFragment extends Fragment {

    private List<Article> articlesList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ArticlesAdapter articlesAdapter;

    String lorem = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. " +
            "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when " +
            "an unknown printer took a galley of type and scrambled it to make a type specimen " +
            "book. It has survived not only five centuries, but also the leap into electronic " +
            "typesetting, remaining essentially unchanged. It was popularised in the 1960s with " +
            "the release of Letraset sheets containing Lorem Ipsum passages, and more recently" +
            " with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";

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

        prepareArticlesData();

        return view;
    }

    public void prepareArticlesData(){
        Article article;
        article = new Article.ArticleBuilder()
                .content(lorem)
                .build();
        articlesList.add(article);
        article = new Article.ArticleBuilder()
                .content(lorem)
                .build();
        articlesList.add(article);
        article = new Article.ArticleBuilder()
                .content(lorem)
                .build();
        articlesList.add(article);
        articlesAdapter.notifyDataSetChanged();
    }

}
