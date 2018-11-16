package movieguideapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.squareup.picasso.Picasso;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Pratyush on 2018-11-11.
 */

public class MovieInfoView extends AppCompatActivity  {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_info);
        Intent intent = getIntent();
        Movie movie = (Movie)intent.getSerializableExtra("MovieObject");


        try{displayInfo(movie);}catch(Exception e){}
    }

    private void displayInfo(Movie movie) throws java.io.IOException{

        getVideos(Integer.toString(movie.getId()));
        // RecyclerView videos = findViewById(R.id.recycler);

        TextView title = findViewById(R.id.Title);
        title.setText(movie.getTitle());

        RatingBar rating_bar = findViewById(R.id.RatingBar);
        rating_bar.setRating((float)movie.getRating() / 2);


        TextView rating = findViewById(R.id.Rating);
        rating.setText("Rating: " + String.valueOf(movie.getRating()) + "/10");


        TextView release_date = findViewById(R.id.ReleaseDate);
        release_date.setText("Release Date: " + movie.getReleaseDate() );

        TextView popularity = findViewById(R.id.Popularity);
        popularity.setText("Popularity: " + String.valueOf(movie.getPopularity()));

        TextView overview_title = findViewById(R.id.OverviewTitle);
        overview_title.setText(R.string.Summary);

        TextView overview = findViewById(R.id.Overview);
        overview.setText(movie.getOverview());


    }

    private void getVideos(String movieId){

        MovieRetriever video_retriever = ApiUtils.getVideoRetriever(movieId);
        System.out.println(movieId);
        Call<VideoList> call = video_retriever.getVideos();

        call.enqueue(new Callback<VideoList>() {

            @Override
            public void onResponse(Call<VideoList> call, Response<VideoList> response) {

                final VideoList videoList = response.body();

                //DEBUGGING
                Log.e(" main ", " apt " + response.body());
                System.out.println(response.body());
                if(videoList == null){
                    System.out.println("VIDEO LIST NULL");}

                Video[] videos = new Video[videoList.getVideos().size()];

                for(int i=0;i < videoList.getVideos().size();i++){
                    System.out.println(videoList.getVideos().get(i).getTitle());
                    videos[i] = videoList.getVideos().get(i);
                            //copy the Video objects from videoList into videos
                }

                RecyclerViewAdapter adapter = new RecyclerViewAdapter(getApplicationContext(), videos);
                recyclerView = (RecyclerView)findViewById(R.id.recycler);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<VideoList> call, Throwable t) {
                System.out.println("FAILLLLL TEST");
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}


