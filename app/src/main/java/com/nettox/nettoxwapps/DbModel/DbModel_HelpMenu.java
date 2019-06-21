package com.nettox.nettoxwapps.DbModel;

public class DbModel_HelpMenu {

    private int id;
    private String title, subtitle, image, description;

    public DbModel_HelpMenu(int id, String title, String subtitle, String image, String description) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.image = image;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
