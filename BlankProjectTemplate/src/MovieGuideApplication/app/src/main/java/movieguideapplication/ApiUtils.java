package movieguideapplication;

/**
 * API Utils Class
 * This class primes the URL for a get request to be made using the Retrofit client
 */
public class ApiUtils {

    /**
     * The URL to which the API get request will be made
     */
    public static final String BASE_URL = "http://api.themoviedb.org";

    /**
     * This method creates an object of the MovieRetriever class using the Retrofit Client
     * @return A MovieRetriever object using the BASE_URL variable
     */
    public static MovieRetriever getMovieRetirever() {
        return RetrofitClient.getClient(BASE_URL).create(MovieRetriever.class);
    }
}
