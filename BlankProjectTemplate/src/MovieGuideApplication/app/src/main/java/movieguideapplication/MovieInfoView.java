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
    String base_image_url = "https://image.tmdb.org/t/p/original";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_info);
        Intent intent = getIntent();
        Movie movie = (Movie)intent.getSerializableExtra("MovieObject");

        movieImage = (ImageView)findViewById(R.id.MovieImage);
        Picasso.with(this).load(base_image_url + movie.getImagePath()).into(movieImage);
        //Bitmap bitmap_image = getBitmapfromUrl(base_image_url + movie.getImagePath());
        //movieImage.setImageBitmap(bitmap_image);
        try{displayInfo(movie);}catch(Exception e){}
    }


    public Bitmap getBitmapfromUrl(String imageUrl)
    {
        try
        {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(input);
            return bitmap;

        } catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;

        }
    }
    private void displayInfo(Movie movie) throws java.io.IOException{


        TextView rating = findViewById(R.id.Rating);
        rating.setText("Rating: " + String.valueOf(movie.getRating()));

        TextView popularity = findViewById(R.id.Popularity);
        popularity.setText("Popularity: " + String.valueOf(movie.getPopularity()));

        TextView release_date = findViewById(R.id.ReleaseDate);
        release_date.setText("Release Date: " + movie.getReleaseDate());

        TextView overview = findViewById(R.id.Overview);
        overview.setText(movie.getOverview());
    }
}


