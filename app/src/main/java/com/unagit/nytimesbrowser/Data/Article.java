package com.unagit.nytimesbrowser.Data;

public class Article {
    private String title;
    private String url;

    public Article(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return this.title;
    }

    public String getUrl() {
        return this.url;
    }
}
