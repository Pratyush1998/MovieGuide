package movieguideapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Pratyush on 2018-11-11.
 */

public class MovieInfoView extends AppCompatActivity  {
    ImageView movieImage;
    String base_image_url = "https://image.tmdb.org/t/p/w780";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_info);
        Intent intent = getIntent();
        Movie movie = (Movie)intent.getSerializableExtra("MovieObject");

        movieImage = (ImageView)findViewById(R.id.MovieImage);
        Picasso.with(this).load(base_image_url + movie.getImagePath()).into(movieImage);
        try{displayInfo(movie);}catch(Exception e){}
    }

    private void displayInfo(Movie movie) throws java.io.IOException{

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
}


