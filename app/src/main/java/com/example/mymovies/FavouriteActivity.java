package com.example.mymovies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.mymovies.adapters.MovieAdapter;
import com.example.mymovies.data.FavouriteMovie;
import com.example.mymovies.data.MainViewModel;
import com.example.mymovies.data.Movie;

import java.util.ArrayList;
import java.util.List;

public class FavouriteActivity extends AppCompatActivity
{
    private RecyclerView recyclerViewFavouriteMovies;
    private MovieAdapter adapter;

    private MainViewModel viewModel;
    private LiveData<List<FavouriteMovie>> favouriteMovies;


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        int id = item.getItemId();
        switch (id)
        {
            case R.id.itemMain:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.itemFavourite:
                Intent intent1 = new Intent(this, FavouriteActivity.class);
                startActivity(intent1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        initViews();
    }


    private void initViews()
    {
        recyclerViewFavouriteMovies = findViewById(R.id.recyclerViewFavouriteMovies);
        recyclerViewFavouriteMovies.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new MovieAdapter();
        recyclerViewFavouriteMovies.setAdapter(adapter);

        viewModel = new MainViewModel(this.getApplication());
        favouriteMovies = viewModel.getFavouriteMovies();

        favouriteMovies.observe(this, new Observer<List<FavouriteMovie>>()
        {
            @Override
            public void onChanged(List<FavouriteMovie> favouriteMovies)
            {
                List<Movie> movies = new ArrayList<>();
                if (favouriteMovies != null)
                {
                    movies.addAll(favouriteMovies);
                    adapter.setMovieList(movies);
                }
            }
        });

        adapter.setOnPosterClickListener(new MovieAdapter.OnPosterClickListener()
        {
            @Override
            public void onPosterClick(int position)
            {
                Movie movie = adapter.getMovieList().get(position);
                Intent intent = new Intent(FavouriteActivity.this, DetailActivity.class);
                intent.putExtra("id", movie.getId());
                startActivity(intent);
            }
        });
    }

}