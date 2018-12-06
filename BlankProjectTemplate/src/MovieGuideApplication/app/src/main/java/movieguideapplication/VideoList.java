package movieguideapplication;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Contains getter and setter methods for the list of movies loaded from the API
 */
public class VideoList {

    /**
     * Contains a list of videos
     */
    @SerializedName("results")
    @Expose
    private List<Video> videos = null;

    /**
     * Gets the list of loads video
     * @return videos
     */
    public List<Video> getVideos()
    {
        return videos;
    }

    /**
     * Updates the list of videos
     * @param videos
     */
    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

}
