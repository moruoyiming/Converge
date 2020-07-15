package com.example.hotfix.note.class10.json;

public class HomeBannerModel {
    private String title;
    private String url;
    private boolean status;

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public boolean isStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "HomeBannerModel{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", status=" + status +
                '}';
    }
}
