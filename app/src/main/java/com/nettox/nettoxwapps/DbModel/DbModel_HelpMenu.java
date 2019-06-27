package com.nettox.nettoxwapps.DbModel;

public class DbModel_HelpMenu {

    private int id, image;
    private String title, subtitle, description;

    public DbModel_HelpMenu(int id, String title, int image, String subtitle, String description) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.subtitle = subtitle;
        this.description = description;
    }

    public DbModel_HelpMenu () {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
