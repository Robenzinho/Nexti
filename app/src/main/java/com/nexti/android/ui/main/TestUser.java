package com.nexti.android.ui.main;

public class TestUser {
    private String username;
    private String imageUrl;
    private String email;
    private String country;
    private String id;
    private String bio;
    private String phone;
    private String search;
    private String badge;
    private String age;
    private String sex ;

    public TestUser(String username, String imageUrl, String email,
                    String country, String id, String bio,
                    String phone, String search,
                    String badge,
                    String age, String sex) {
        this.username = username;
        this.imageUrl = imageUrl;
        this.email = email;
        this.country = country;
        this.id = id;
        this.bio = bio;
        this.phone = phone;
        this.search = search;
        this.badge = badge;
        this.age = age;
        this.sex = sex;
    }

    public TestUser(){

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

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
