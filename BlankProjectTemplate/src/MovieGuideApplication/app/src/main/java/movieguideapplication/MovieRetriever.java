package movieguideapplication;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Makes get requests to the API using the Retrofit Client annotations
 */
public interface MovieRetriever {

    /**
     * Get request for movies based by popularity
     * @return list of call responses from the API
     */
    @GET("/3/discover/movie?sort_by=popularity.desc?&api_key=df63c842bafad96da9f702c5aaa2c5cc")
    Call <MovieList> getMovies();

    /**
     * Get request for movies based by popularity and specified page
     * @param page Page to extract movies from
     * @return list of call responses from the API
     */
    @GET("/3/discover/movie?sort_by=popularity.desc?&api_key=df63c842bafad96da9f702c5aaa2c5cc")
    Call <MovieList> getMovies(@Query("page") int page);
}
