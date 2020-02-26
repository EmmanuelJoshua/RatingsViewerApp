package com.example.spark.ratingsapp;

public class RatingsModel {

    private String rating;
    private String movie;
    private String review;

    public RatingsModel(){

    }

    public RatingsModel(String rating, String movie, String review){
        this.rating = rating;
        this.movie = movie;
        this.review = review;
    }

    public String getRating() {
        return rating;
    }

    public String getMovie() {
        return movie;
    }

    public String getReview() {
        return review;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
