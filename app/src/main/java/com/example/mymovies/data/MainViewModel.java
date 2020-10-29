package com.example.mymovies.data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainViewModel extends AndroidViewModel
{

    private static MovieDatabase database;
    private LiveData<List<Movie>> movies;
    private LiveData<List<FavouriteMovie>> favouriteMovies;


    public MainViewModel(@NonNull Application application)
    {
        super(application);
        database = MovieDatabase.getInstance(getApplication());
        movies = database.movieDao().getAllMovies();
        favouriteMovies = database.movieDao().getAllFavouriteMovies();
    }

    public LiveData<List<Movie>> getMovies()
    {
        return movies;
    }


    public Movie getMovieById(int id)     //1
    {
        try
        {
            return new GetMovieTask().execute(id).get();
        } catch (ExecutionException e)
        {
            e.printStackTrace();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public LiveData<List<FavouriteMovie>> getFavouriteMovies()
    {
        return favouriteMovies;
    }



    public void deleteAllMovies()     //2
    {
        new DeleteMoviesTask().execute();
    }


    public void deleteMovie(Movie movie)   //3
    {
        new DeleteTask().execute(movie);
    }


    public void insertMovie(Movie movie)      //4
    {
        try
        {
            new InsertTask().execute(movie).get();
        } catch (ExecutionException e)
        {
            e.printStackTrace();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }


    public FavouriteMovie getFavouriteMovieById(int id)
    {
        try
        {
            return new GetFavouriteMovieTask().execute(id).get();
        } catch (ExecutionException e)
        {
            e.printStackTrace();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        return null;
    }


    public void deleteFavouriteMovie(FavouriteMovie movie)
    {
        new DeleteFavouriteTask().execute(movie);
    }


    public void insertFavouriteMovie(FavouriteMovie movie)
    {
        try
        {
            new InsertFavouriteTask().execute(movie).get();
        } catch (ExecutionException e)
        {
            e.printStackTrace();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }


    private static class GetMovieTask extends AsyncTask<Integer, Void, Movie>
    {
        @Override
        protected Movie doInBackground(Integer... integers)
        {
            if (integers != null && integers.length > 0)
            {
                return database.movieDao().getMovieById(integers[0]);
            }
            return null;
        }
    }


    private static class DeleteTask extends AsyncTask<Movie, Void, Void>
    {
        @Override
        protected Void doInBackground(Movie... movies)
        {
            if (movies != null && movies.length > 0)
            {
                database.movieDao().deleteMovie(movies[0]);
            }
            return null;
        }
    }


    private static class DeleteMoviesTask extends AsyncTask<Void, Void, Void>
    {
        @Override
        protected Void doInBackground(Void... integers)
        {


            database.movieDao().deleteAllMovies();

            return null;
        }
    }

    private static class InsertTask extends AsyncTask<Movie, Void, Void>
    {
        @Override
        protected Void doInBackground(Movie... movies)
        {
            if (movies != null && movies.length > 0)
            {
                database.movieDao().insertMovie(movies[0]);
            }
            return null;
        }
    }


    private static class GetFavouriteMovieTask extends AsyncTask<Integer, Void, FavouriteMovie>
    {
        @Override
        protected FavouriteMovie doInBackground(Integer... integers)
        {
            if (integers != null && integers.length > 0)
            {
                return database.movieDao().getFavouriteMovieById(integers[0]);
            }
            return null;
        }
    }


    private static class DeleteFavouriteTask extends AsyncTask<FavouriteMovie, Void, Void>
    {
        @Override
        protected Void doInBackground(FavouriteMovie... movies)
        {
            if (movies != null && movies.length > 0)
            {
                database.movieDao().deleteFavouriteMovie(movies[0]);
            }
            return null;
        }
    }


    private static class InsertFavouriteTask extends AsyncTask<FavouriteMovie, Void, Void>
    {
        @Override
        protected Void doInBackground(FavouriteMovie... movies)
        {
            if (movies != null && movies.length > 0)
            {
                database.movieDao().insertFavouriteMovie(movies[0]);
            }
            return null;
        }
    }
}

