package com.mzone.jetpack.ui.movie.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.mzone.jetpack.data.source.Repository;
import com.mzone.jetpack.data.source.local.entity.MovieEntity;

public class DetailMovieViewModel extends ViewModel {

    private String movieId;
    private Repository repository;

    public DetailMovieViewModel(Repository repository) {
        this.repository = repository;
    }

    LiveData<MovieEntity> getMovieById() {
        return repository.getMovieById(movieId);
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }
}
