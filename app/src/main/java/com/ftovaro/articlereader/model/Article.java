package com.ftovaro.articlereader.model;

/**
 * Represents an Article.
 * Created by FelipeTovar on 09-Mar-16.
 */
public class Article {

    /** Represents the content of the article **/
    private final String content;

    /** The url of the article **/
    private final String url;

    /** The url of the image that will be show **/
    private final String imageURL;

    private Article (ArticleBuilder builder){
        this.content = builder.content;
        this.url = builder.url;
        this.imageURL = builder.imageURL;
    }

    public String getContent() {
        return content;
    }

    public String getUrl() {
        return url;
    }

    public String getImageURL() {
        return imageURL;
    }

    public static class ArticleBuilder{
        private String content;
        private String url;
        private String imageURL;

        public ArticleBuilder(){

        }

        public ArticleBuilder content (String content){
            this.content = content;
            return this;
        }

        public ArticleBuilder url (String url){
            this.url = url;
            return this;
        }

        public ArticleBuilder imageURL (String imageURL){
            this.imageURL = imageURL;
            return this;
        }

        public Article build(){
            return new Article(this);
        }

    }
}
