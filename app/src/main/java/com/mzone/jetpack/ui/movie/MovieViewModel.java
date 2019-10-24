package com.mzone.jetpack.ui.movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.mzone.jetpack.data.source.Repository;
import com.mzone.jetpack.data.source.local.entity.MovieEntity;

import java.util.List;

public class MovieViewModel extends ViewModel {

    private Repository repository;

    public MovieViewModel(Repository repository) {
        this.repository = repository;
    }

    LiveData<List<MovieEntity>> getMovies() {
        return repository.getAllMovie();
    }
}
