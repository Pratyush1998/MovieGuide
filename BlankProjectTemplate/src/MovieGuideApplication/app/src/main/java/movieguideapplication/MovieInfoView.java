package movieguideapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Pratyush on 2018-11-11.
 */

public class MovieInfoView extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_info);

        Intent intent = getIntent();

        String movie = intent.getStringExtra("MovieTitle");



        getSummary(movie);
    }

    private void getSummary(String movieTitle) {

        MovieRetriever movie = ApiUtils.getMovieRetirever();


        Call<MovieList> call = movie.getMovie(movieTitle);

        call.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {

                MovieList movie = response.body();

                //int size = movie.getMovies().size();

                String overview = movie.getMovies().get(0).getOverview();

                TextView textview = findViewById(R.id.MovieInfo);

                textview.setText(overview);





            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
