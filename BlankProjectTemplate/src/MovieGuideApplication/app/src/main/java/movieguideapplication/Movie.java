package movieguideapplication;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Parses data extracted from the API
 */
public class Movie {

    /**
     * Contains the ID number of the movie
     */
    @SerializedName("id")
    @Expose
    int id;

    /**
     * Boolean value corresponding to the availability of the movie trailer
     */
    @SerializedName("video")
    @Expose
    boolean video;

    /**
     * Contains the average rating of the movie
     */
    @SerializedName("Vote_Average")
    @Expose
    double vote_average;

    /**
     * Title of the movie
     */
    @SerializedName("title")
    @Expose
    String title;

    /**
     * Contains a numerical value for the popularity of the movie
     */
    @SerializedName("Popularity")
    @Expose
    double popularity;

    /**
     * Gets the title of the movie
     * @return string containing the title
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Constructor for the Movie Class
     * @param id Contains the ID number of the movie
     * @param video Boolean value corresponding to the availability of the movie trailer
     * @param vote_average Contains the average rating of the movie
     * @param title Contains a numerical value for the popularity of the movie
     * @param popularity Contains a numerical value for the popularity of the movie
     */
    public Movie(int id, boolean video, double vote_average, String title, double popularity)
    {
        this.id = id;
        this.video = video;
        this.vote_average = vote_average;
        this.title = title;
        this.popularity = popularity;
    }
}
