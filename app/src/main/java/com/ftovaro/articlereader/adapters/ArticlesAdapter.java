package com.ftovaro.articlereader.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.ftovaro.articlereader.R;
import com.ftovaro.articlereader.model.Article;
import com.ftovaro.articlereader.network.AppController;

import java.util.List;

/**
 * Adapter that fills the recycler view with articles.
 * Created by FelipeTovar on 09-Mar-16.
 */
public class ArticlesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /** List of articles **/
    private List<Article> articlesList;
    /** Loader for images that uses LRU Caché **/
    private ImageLoader imageLoader;
    /** Determine the color of cards depending of its state **/
    private boolean isCustomColorChecked;
    /** True for odds numbers and false for even numbers **/
    private boolean isOdd = true;
    /** Context of the activity that calls the adapter **/
    private Context context;
    /** Represents a type of view for a header **/
    private static final int TYPE_HEADER = 0;
    /** Represents a type of view for an item **/
    private static final int TYPE_ITEM = 1;

    public ArticlesAdapter(List<Article> articlesList, Context context) {
        this.articlesList = articlesList;
        imageLoader = AppController.getInstance().getImageLoader();
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.article_list_row, parent, false);
            return new ViewHolderArticle(itemView);
        } else if (viewType == TYPE_HEADER) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.content_highlight_article, parent, false);
            return new ViewHolderArticleHeader(itemView);
        }
        throw new RuntimeException("there is no type that matches the type " + viewType +
                " + make sure your using types correctly");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolderArticle) {
            Article article = articlesList.get(position);
            ((ViewHolderArticle) holder).content.setText(article.getContent());
            ((ViewHolderArticle) holder).image.setImageUrl(article.getImageURL(), imageLoader);
            ((ViewHolderArticle) holder).image.setDefaultImageResId(R.drawable.image_placeholder);
            if(isCustomColorChecked){
                setCardColor(((ViewHolderArticle) holder).relativeLayoutCard);
            }else{
                ((ViewHolderArticle) holder).relativeLayoutCard.
                        setBackgroundColor(ContextCompat.getColor(context,
                        R.color.defaultCardColor));
            }
        } else if (holder instanceof ViewHolderArticleHeader) {
            Article article = articlesList.get(position);
            ((ViewHolderArticleHeader) holder).content.setText(article.getContent());
            ((ViewHolderArticleHeader) holder).image.setImageUrl(article.getImageURL(), imageLoader);
            ((ViewHolderArticleHeader) holder).image.setDefaultImageResId(R.drawable.image_placeholder);
        }
    }

    /**
     * Set the color of card. The color changes if the card correspond to an odd or and even number.
     * @param relativeLayout    a relative that contains each part of the card.
     */
    private void setCardColor(RelativeLayout relativeLayout){
        try{
            if(isOdd){
                relativeLayout.setBackgroundColor(ContextCompat.getColor(context,
                        R.color.colorOddCard));
            } else {
                relativeLayout.setBackgroundColor(ContextCompat.getColor(context,
                        R.color.colorEvenCard));
            }
            isOdd = !isOdd;
        }catch (NullPointerException e){
            Log.e("Error", e.getMessage());
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;

        return TYPE_ITEM;
    }

    /**
     * Evaluate of the current position is the first of the list.
     * @param position  current position.
     * @return  true if the current position is the first, otherwise, returns false.
     */
    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    @Override
    public int getItemCount() {
        return articlesList.size();
    }

    /**
     * Set the state of isCustomColorChecked.
     * @param isCustomColorChecked  determine if the adapter needs to paint some cards different.
     */
    public void setIsCustomColorChecked(boolean isCustomColorChecked) {
        this.isCustomColorChecked = isCustomColorChecked;
    }

    /**
     * A class that represents each card of the recycler view.
     */
    public class ViewHolderArticle extends RecyclerView.ViewHolder {
        public TextView content;
        public NetworkImageView image;
        public RelativeLayout relativeLayoutCard;

        public ViewHolderArticle(View view) {
            super(view);
            content = (TextView) view.findViewById(R.id.content);
            image = (NetworkImageView) view.findViewById(R.id.image);
            relativeLayoutCard = (RelativeLayout) view.findViewById(R.id.relative_layout_card);
        }
    }

    /**
     * A class that represents the header of the recycler view.
     */
    public class ViewHolderArticleHeader extends RecyclerView.ViewHolder {
        public TextView content;
        public NetworkImageView image;

        public ViewHolderArticleHeader(View view) {
            super(view);
            content = (TextView) view.findViewById(R.id.highlightContent);
            image = (NetworkImageView) view.findViewById(R.id.highlightImage);
        }
    }

}
