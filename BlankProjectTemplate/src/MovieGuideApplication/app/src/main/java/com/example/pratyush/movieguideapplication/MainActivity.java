package com.example.pratyush.movieguideapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.movieListView);


        getMovies();

    }

    private void getMovies(){

        MovieRetriever movies = ApiUtils.getMovieRetirever();

        Call<MovieList> call = movies.getMovies(2);

        call.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {

                MovieList movieList = response.body();

                String[] movies = new String[movieList.getMovies().size()];

                for (int i = 0; i < movieList.getMovies().size(); i++){
                    movies[i] = movieList.getMovies().get(i).getTitle();
                }

                listView.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, movies));
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {

                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }

}
