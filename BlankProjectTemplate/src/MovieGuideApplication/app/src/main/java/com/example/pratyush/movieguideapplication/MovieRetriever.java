package com.example.pratyush.movieguideapplication;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieRetriever {

    @GET("/3/discover/movie?sort_by=popularity.desc?&api_key=df63c842bafad96da9f702c5aaa2c5cc")
    Call <MovieList> getMovies();

    @GET("/3/discover/movie?sort_by=popularity.desc?&api_key=df63c842bafad96da9f702c5aaa2c5cc")
    Call <MovieList> getMovies(@Query("page") int page);
}
