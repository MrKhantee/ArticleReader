package com.ftovaro.articlereader.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ftovaro.articlereader.R;
import com.ftovaro.articlereader.model.Article;

import java.util.List;

/**
 * Created by ftova on 09-Mar-16.
 */
public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ViewHolderArticle> {

    private List<Article> articlesList;

    public ArticlesAdapter(List<Article> articlesList) {
        this.articlesList = articlesList;
    }

    @Override
    public ViewHolderArticle onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.article_list_row, parent, false);

        return new ViewHolderArticle(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolderArticle holder, int position) {
        Article article = articlesList.get(position);
        holder.content.setText(article.getContent());
    }

    @Override
    public int getItemCount() {
        return articlesList.size();
    }

    public class ViewHolderArticle extends RecyclerView.ViewHolder {
        public TextView content;

        public ViewHolderArticle(View view) {
            super(view);
            content = (TextView) view.findViewById(R.id.content);
        }
    }
}
