package movieguideapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.content.Intent;
import java.util.concurrent.TimeUnit;


/**
 * Parent class of the application
 */
public class MainActivity extends AppCompatActivity{

    public int page = 1;

    public int maxPages = 25; //Needs to be changed to number of pages returned on get request, set to 25 for now!

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
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        TextView toolbar_title = (TextView)toolbar.findViewById(R.id.toolbarTitle);
        toolbar_title.setText(getResources().getString(R.string.app_name));
        //setSupportActionBar(toolbar);
        listView = findViewById(R.id.movieListView);

        if(getIntent() != null){
            Intent intent = getIntent();
            page = (int)intent.getIntExtra("PageNumber", 1);
            getMovies(page);
        }else {
            getMovies(page);
        }
    }

    /**
     * Retrieves the movies from the API
     */
    private void getMovies(final int page){

        /**
         * MovieRetriever objected created using the BASE_URL from ApiUtils class
         */
        MovieRetriever movies = ApiUtils.getMovieRetirever();

        /**
         * A list of movies retrieved using the API
         */
        Call<MovieList> call = movies.getMovies(page);

        call.enqueue(new Callback<MovieList>() {

            /**
             * Puts the movies into a list
             * @param call list of movies to be extracted from API
             * @param response response from the API
             */
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                Button next = (Button)findViewById(R.id.NextButton);
                Button prev = (Button)findViewById(R.id.PrevButton);

                TextView pageNum = findViewById(R.id.PageNum);
                pageNum.setText("Page: " + page);

                next.setOnClickListener(new View.OnClickListener(){
                    int page_num = page;
                    @Override
                    public void onClick(View view){
                        if(page_num < maxPages) {
                            page_num++;
                            Intent intent = new Intent(MainActivity.this, MainActivity.class);
                            intent.putExtra("PageNumber", page_num);
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        }
                    }
                });

                prev.setOnClickListener(new View.OnClickListener(){
                    int page_num = page;
                    @Override
                    public void onClick(View view){
                        if(page_num > 1) {
                            page_num--;
                            Intent intent = new Intent(MainActivity.this, MainActivity.class);
                            intent.putExtra("PageNumber", page_num);
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                        }
                    }
                });


                final MovieList movieList = response.body();

                String[] movies = new String[movieList.getMovies().size()];

                for (int i = 0; i < movieList.getMovies().size(); i++){
                    movies[i] = movieList.getMovies().get(i).getTitle();
                }

                listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_selectable_list_item, movies){
                                        @Override
                                        public View getView(int position, View convertView, ViewGroup parent) {
                                            // Get the Item from ListView
                                            View view = super.getView(position, convertView, parent);

                                            // Initialize a TextView for ListView each Item
                                            TextView tv = (TextView) view.findViewById(android.R.id.text1);

                                            // Set the text color of TextView (ListView Item)
                                            tv.setTextColor(getResources().getColor(R.color.colorAccent));

                                            // Generate ListView Item using TextView
                                            return view;
                                        }
                });

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        Intent intent = new Intent(MainActivity.this, MovieInfoView.class);
                        String movie = adapterView.getAdapter().getItem(i).toString();
                        Movie selected_movie = movieList.getMovies().get(i);
                        intent.putExtra("MovieObject", selected_movie);
                        startActivity(intent);
                    }
                });
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




    }

}
