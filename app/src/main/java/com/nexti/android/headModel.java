package com.nexti.android;

public class headModel {
    private String username;
    private String imageUrl;
    private String email;
    private String country;
    private String id;

    public headModel(String username, String imageUrl, String email, String country, String id) {
        this.username = username;
        this.imageUrl = imageUrl;
        this.email = email;
        this.country = country;
        this.id = id;
    }

    public headModel(){

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
