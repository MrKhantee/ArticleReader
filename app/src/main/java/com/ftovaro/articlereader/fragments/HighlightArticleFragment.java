package com.ftovaro.articlereader.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.ftovaro.articlereader.R;
import com.ftovaro.articlereader.model.Article;
import com.ftovaro.articlereader.network.AppController;
import com.ftovaro.articlereader.util.Communicator;
import com.ftovaro.articlereader.util.SetUpHighlightArticle;

/**
 * A simple {@link Fragment} subclass.
 */
public class HighlightArticleFragment extends Fragment  {

    private TextView highlightContent;
    private NetworkImageView highlightImage;
    private ImageLoader imageLoader;

    public HighlightArticleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_highlight_article, container, false);
        highlightContent = (TextView) view.findViewById(R.id.highlightContent);
        highlightImage = (NetworkImageView) view.findViewById(R.id.highlightImage);
        imageLoader = AppController.getInstance().getImageLoader();

        return view;
    }

    public void updateData(Article article) {
        highlightContent.setText(article.getContent());
        highlightImage.setImageUrl(article.getImageURL(), imageLoader);
        highlightImage.setDefaultImageResId(R.drawable.ic_image);
    }

}
