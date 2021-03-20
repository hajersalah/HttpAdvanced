package com.nhcompany.httpadvanced;

public class UserDetails {

    private String name ;
    private  int likes ;
    private String ImageUrls ;

    public UserDetails(String name, int likes, String imageUrls) {
        this.name = name;
        this.likes = likes;
        this.ImageUrls = imageUrls;
    }

    public String getName() {
        return name;
    }

    public int getLikes() {
        return likes;
    }

    public String getImageUrls() {
        return ImageUrls;
    }
}
