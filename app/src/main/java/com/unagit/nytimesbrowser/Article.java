package com.unagit.nytimesbrowser;

public class Article {
    private String name;
    private String url;

    Article(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return this.name;
    }

    public String getUrl() {
        return this.url;
    }
}
