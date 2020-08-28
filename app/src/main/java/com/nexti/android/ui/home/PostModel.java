package com.nexti.android.ui.home;

public class PostModel {
    String postId;
    String postImage;
    String description;
    String author;

    public PostModel(String postId, String postImage, String description, String author) {
        this.postId = postId;
        this.postImage = postImage;
        this.description = description;
        this.author = author;
    }
    public PostModel(){

    }
    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getPostImage() {
        return postImage;
    }

    public void setPostImage(String postImage) {
        this.postImage = postImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
