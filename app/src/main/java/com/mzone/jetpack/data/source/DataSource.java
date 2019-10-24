package com.mzone.jetpack.data.source;

import androidx.lifecycle.LiveData;

import com.mzone.jetpack.data.source.local.entity.MovieEntity;
import com.mzone.jetpack.data.source.local.entity.TvEntity;

import java.util.List;

public interface DataSource {
    LiveData<List<MovieEntity>> getAllMovie();
    LiveData<MovieEntity> getMovieById(String courseId);

    LiveData<List<TvEntity>> getAllTv();
    LiveData<TvEntity> getTvById(String courseId);
}
