package com.example.mymovies.api;

import com.example.mymovies.pojo.MoviesResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService
{
    @GET("3/discover/movie?api_key=754fa9d8fd80b794d6d14ae8911b68a8")
    Observable<MoviesResponse>  getMovies();
}
