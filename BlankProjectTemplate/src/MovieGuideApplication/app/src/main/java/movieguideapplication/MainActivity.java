package movieguideapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;



/**
 * Parent class of the application
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Variable of type ListView
     */
    ListView listView;

    /**
     * Creates the user interface when the application is run
     * @param savedInstanceState Holds the saved state of the application
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.movieListView);


        getMovies();

    }

    /**
     * Retrieves the movies from the API
     */
    private void getMovies(){

        /**
         * MovieRetriever objected created using the BASE_URL from ApiUtils class
         */
        MovieRetriever movies = ApiUtils.getMovieRetirever();

        /**
         * A list of movies retrieved using the API
         */
        Call<MovieList> call = movies.getMovies(2);

        call.enqueue(new Callback<MovieList>() {

            /**
             * Puts the movies into a list
             * @param call list of movies to be extracted from API
             * @param response response from the API
             */
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {

                MovieList movieList = response.body();

                String[] movies = new String[movieList.getMovies().size()];

                for (int i = 0; i < movieList.getMovies().size(); i++){
                    movies[i] = movieList.getMovies().get(i).getTitle();
                }

                listView.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_selectable_list_item, movies));
            }

            /**
             * Displays the error message
             * @param call list of movies to be extracted from API
             * @param t error which occurred while retrieving movies from API
             */
            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {

                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, MovieInfoView.class);
                String movie = adapterView.getAdapter().getItem(i).toString();
                intent.putExtra("MovieTitle", movie);
                startActivity(intent);
            }
        });


    }

}
