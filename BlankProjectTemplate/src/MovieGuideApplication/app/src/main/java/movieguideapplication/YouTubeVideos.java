package movieguideapplication;

public class YouTubeVideos {
    String videoUrl;
    public YouTubeVideos() {
    }

    /**
     * 
     * @param videoUrl
     */
    public YouTubeVideos(String videoUrl) {
        this.videoUrl = videoUrl;
    }
    public String getVideoUrl() {
        return videoUrl;
    }
    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
