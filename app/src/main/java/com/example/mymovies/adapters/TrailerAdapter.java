package com.example.mymovies.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymovies.R;
import com.example.mymovies.data.Trailer;

import java.util.ArrayList;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.TrailerViewHolder>
{
    private ArrayList<Trailer> trailers;

    private OnTrailerClickListener onTrailerClickListener;

    public interface OnTrailerClickListener
    {
        void onTrailerClick(String url);
    }
    @NonNull
    @Override
    public TrailerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.trailer_item, viewGroup, false);
        return new TrailerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrailerViewHolder trailerViewHolder, int position)
    {
        Trailer trailer = trailers.get(position);
        trailerViewHolder.textViewNameOfVideo.setText(trailer.getName());
    }

    @Override
    public int getItemCount()
    {
        return trailers.size();
    }



    class TrailerViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView imageViewPlay;
        private TextView textViewNameOfVideo;
        public TrailerViewHolder(@NonNull View itemView)
        {
            super(itemView);
            //imageViewPlay = itemView.findViewById(R.id.imageViewPlay);
            textViewNameOfVideo = itemView.findViewById(R.id.textViewNameOfVideo);
            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    if (onTrailerClickListener != null)
                    {
                        onTrailerClickListener.onTrailerClick(trailers.get(getAdapterPosition()).getKey());
                    }
                }
            });
        }
    }

    public void setTrailers(ArrayList<Trailer> trailers)
    {
        this.trailers = trailers;
        notifyDataSetChanged();
    }

    public void setOnTrailerClickListener(OnTrailerClickListener onTrailerClickListener)
    {
        this.onTrailerClickListener = onTrailerClickListener;
    }
}
