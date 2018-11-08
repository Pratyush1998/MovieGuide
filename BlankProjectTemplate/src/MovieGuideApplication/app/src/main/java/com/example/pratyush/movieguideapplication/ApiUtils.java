package com.example.pratyush.movieguideapplication;


public class ApiUtils {
    public static final String BASE_URL = "http://api.themoviedb.org";

    public static MovieRetriever getMovieRetirever() {
        return RetrofitClient.getClient(BASE_URL).create(MovieRetriever.class);
    }
}
