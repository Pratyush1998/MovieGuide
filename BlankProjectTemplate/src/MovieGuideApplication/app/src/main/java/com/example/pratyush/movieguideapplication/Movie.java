package com.example.pratyush.movieguideapplication;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Movie {
    @SerializedName("id")
    @Expose
    int id;
    @SerializedName("video")
    @Expose
    boolean video;
    @SerializedName("Vote_Average")
    @Expose
    double vote_average;
    @SerializedName("title")
    @Expose
    String title;
    @SerializedName("Popularity")
    @Expose
    double popularity;

    public String getTitle()
    {
        return title;
    }

    public Movie(int id, boolean video, double vote_average, String title, double popularity)
    {
        this.id = id;
        this.video = video;
        this.vote_average = vote_average;
        this.title = title;
        this.popularity = popularity;
    }
}
