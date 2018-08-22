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

//import java.util.List;

//import com.google.gson.annotations.Expose;
//import com.google.gson.annotations.SerializedName;

//public class Article {

//    @SerializedName("url")
//    @Expose
//    private String url;
//    @SerializedName("count_type")
//    @Expose
//    private String countType;
//    @SerializedName("column")
//    @Expose
//    private Object column;
//    @SerializedName("section")
//    @Expose
//    private String section;
//    @SerializedName("byline")
//    @Expose
//    private String byline;
//    @SerializedName("title")
//    @Expose
//    private String title;
//    @SerializedName("abstract")
//    @Expose
//    private String _abstract;
//    @SerializedName("published_date")
//    @Expose
//    private String publishedDate;
//    @SerializedName("source")
//    @Expose
//    private String source;
//    @SerializedName("des_facet")
//    @Expose
//    private List<String> desFacet = null;
//    @SerializedName("org_facet")
//    @Expose
//    private String orgFacet;
//    @SerializedName("per_facet")
//    @Expose
//    private String perFacet;
//    @SerializedName("geo_facet")
//    @Expose
//    private String geoFacet;
////    @SerializedName("media")
////    @Expose
////    private List<Medium> media = null;
//
//    public String getUrl() {
//        return url;
//    }
//
//    public void setUrl(String url) {
//        this.url = url;
//    }
//
//    public String getCountType() {
//        return countType;
//    }
//
//    public void setCountType(String countType) {
//        this.countType = countType;
//    }
//
//    public Object getColumn() {
//        return column;
//    }
//
//    public void setColumn(Object column) {
//        this.column = column;
//    }
//
//    public String getSection() {
//        return section;
//    }
//
//    public void setSection(String section) {
//        this.section = section;
//    }
//
//    public String getByline() {
//        return byline;
//    }
//
//    public void setByline(String byline) {
//        this.byline = byline;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getAbstract() {
//        return _abstract;
//    }
//
//    public void setAbstract(String _abstract) {
//        this._abstract = _abstract;
//    }
//
//    public String getPublishedDate() {
//        return publishedDate;
//    }
//
//    public void setPublishedDate(String publishedDate) {
//        this.publishedDate = publishedDate;
//    }
//
//    public String getSource() {
//        return source;
//    }
//
//    public void setSource(String source) {
//        this.source = source;
//    }
//
//    public List<String> getDesFacet() {
//        return desFacet;
//    }
//
//    public void setDesFacet(List<String> desFacet) {
//        this.desFacet = desFacet;
//    }
//
//    public String getOrgFacet() {
//        return orgFacet;
//    }
//
//    public void setOrgFacet(String orgFacet) {
//        this.orgFacet = orgFacet;
//    }
//
//    public String getPerFacet() {
//        return perFacet;
//    }
//
//    public void setPerFacet(String perFacet) {
//        this.perFacet = perFacet;
//    }
//
//    public String getGeoFacet() {
//        return geoFacet;
//    }
//
//    public void setGeoFacet(String geoFacet) {
//        this.geoFacet = geoFacet;
//    }

//    public List<Medium> getMedia() {
//        return media;
//    }
//
//    public void setMedia(List<Medium> media) {
//        this.media = media;
//    }

//}