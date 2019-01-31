package com.example.williamrudwall.dogodex;

// Klass för "cardsen" i TabFragment2
// Håller värden om cardsen

public class Card {

    private String imageURL;
    private String title;

    public Card(String imageURL, String title){
        this.imageURL = imageURL;
        this.title = title;
    }

    public String getImageURL(){
        return imageURL;
    }

    public void setImageURL(String imageURL){
    this.imageURL = imageURL;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }


}
