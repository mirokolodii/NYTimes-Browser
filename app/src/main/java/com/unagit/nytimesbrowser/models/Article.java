package com.unagit.nytimesbrowser.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Article {
    private String title;
    private String url;

    @SerializedName("published_date")
    private String publishedDate;

    @SerializedName("byline")
    private String author;

    public Article() {

    }

    public String getTitle() {
        return this.title;
    }

    public String getUrl() {
        return this.url;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public String getAuthor() {
        return author;
    }
}
