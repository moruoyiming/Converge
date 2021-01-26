package com.example.hotfix.note.slideview;

public class Woman {
    private String pic;
    private String position;
    private String title;

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Woman{" +
                "pic='" + pic + '\'' +
                ", position='" + position + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
