package com.example.mymovies.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymovies.R;
import com.example.mymovies.data.Movie;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>
{

    private List<Movie> movieList;
    private OnPosterClickListener onPosterClickListener;
    private OnReachEndListener onReachEndListener;


    public MovieAdapter()                           //  Конструктор
    {
        movieList = new ArrayList<>();
    }

    public interface OnPosterClickListener             // Интерфейс 1
    {
        void onPosterClick(int position);
    }


    public void setOnPosterClickListener(OnPosterClickListener onPosterClickListener)    // Setter интерфейса 1
    {
        this.onPosterClickListener = onPosterClickListener;
    }


    public interface OnReachEndListener               //  Интерфейс 2
    {
        void onReachEnd();
    }

    public void setOnReachEndListener(OnReachEndListener onReachEndListener)           // Setter интерфейса 2
    {
        this.onReachEndListener = onReachEndListener;
    }


    public void clear()
    {
        this.movieList.clear();
        notifyDataSetChanged();
    }


    public void setMovieList(List<Movie> movieList)    // Setter
    {
        this.movieList = movieList;
        notifyDataSetChanged();
    }


    public void addMovies(List<Movie> movieList)
    {
        this.movieList.addAll(movieList);
        notifyDataSetChanged();
    }


    public List<Movie> getMovieList()            //  Getter
    {
        return movieList;
    }


    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_item, viewGroup, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position)
    {
        if (movieList.size() >= 20 && position > movieList.size() - 4 && onReachEndListener != null)
        {
            onReachEndListener.onReachEnd();
        }
        Movie movie = movieList.get(position);
        Picasso.get().load(movie.getPosterPath()).into(holder.imageViewSmallPoster);
    }

    @Override
    public int getItemCount()
    {
        return movieList.size();
    }



    class MovieViewHolder extends RecyclerView.ViewHolder      //  Вложенный класс
    {
        private ImageView imageViewSmallPoster;
        public MovieViewHolder(@NonNull View itemView)
        {
            super(itemView);
            imageViewSmallPoster = itemView.findViewById(R.id.imageViewSmallPoster);
            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    if (onPosterClickListener != null)
                    {
                        onPosterClickListener.onPosterClick(getAdapterPosition());
                    }
                }
            });
        }
    }
}
