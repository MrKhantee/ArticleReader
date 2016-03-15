package com.ftovaro.articlereader.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.ftovaro.articlereader.R;
import com.ftovaro.articlereader.adapters.ArticlesAdapter;
import com.ftovaro.articlereader.interfaces.CommunicatorFragmentListener;
import com.ftovaro.articlereader.interfaces.WebURLSenderListener;
import com.ftovaro.articlereader.model.Article;
import com.ftovaro.articlereader.interfaces.StandardRequestListener;
import com.ftovaro.articlereader.network.VolleyRequest;
import com.ftovaro.articlereader.util.InfoShower;
import com.ftovaro.articlereader.util.JsonParser;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Fragment that manage and download the articles.
 * Created by FelipeTovar on 09-Mar-16.
 */
public class FeedFragment extends Fragment implements CommunicatorFragmentListener {

    /** A list of articles. **/
    private List<Article> articlesList = new ArrayList<>();
    /** Interface to send data to the parent activity. **/
    private WebURLSenderListener urlSenderListener;
    /** Adapter for the list of articles. **/
    private ArticlesAdapter articlesAdapter;

    public FeedFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_feed, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        articlesAdapter = new ArticlesAdapter(articlesList, getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(articlesAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(),
                recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Article article = articlesList.get(position);
                urlSenderListener.sendURL(article.getUrl());
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));



        downloadArticle();

        return view;
    }

    @Override
    public void changeCardsColors(boolean state) {
        articlesAdapter.setIsCustomColorChecked(state);
        articlesAdapter.notifyDataSetChanged();
    }

    private void downloadArticle(){
        InfoShower.showDialog(getActivity());
        VolleyRequest.requestArticles(getActivity(), new StandardRequestListener() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                articlesList.addAll(JsonParser.parseResponse(jsonObject));
                articlesAdapter.notifyDataSetChanged();
                InfoShower.hideDialog();
            }

            @Override
            public void onFailure(String error) {
                InfoShower.hideDialog();
            }
        });
    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private FeedFragment.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView,
                                     final FeedFragment.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context,
                    new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            urlSenderListener = (WebURLSenderListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString()
                    + " must implement WebURLSenderListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        urlSenderListener = null;
    }
}
