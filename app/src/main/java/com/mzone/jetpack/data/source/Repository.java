package com.mzone.jetpack.data.source;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mzone.jetpack.data.source.local.entity.MovieEntity;
import com.mzone.jetpack.data.source.local.entity.TvEntity;
import com.mzone.jetpack.data.source.remote.RemoteRepository;
import com.mzone.jetpack.data.source.remote.response.MovieModel;
import com.mzone.jetpack.data.source.remote.response.TvModel;

import java.util.ArrayList;
import java.util.List;

public class Repository implements DataSource {
    private volatile static Repository INSTANCE = null;

    private final RemoteRepository remoteRepository;

    public Repository(@NonNull RemoteRepository remoteRepository) {
        this.remoteRepository = remoteRepository;
    }

    public static Repository getInstance(RemoteRepository remoteData) {
        if (INSTANCE == null) {
            synchronized (Repository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Repository(remoteData);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public LiveData<List<MovieEntity>> getAllMovie() {
        MutableLiveData<List<MovieEntity>> movieResults = new MutableLiveData<>();

        remoteRepository.getMovie(new RemoteRepository.LoadMovieCallback() {
            @Override
            public void onMovieReceived(List<MovieModel> movieResponse) {
                ArrayList<MovieEntity> movieList = new ArrayList<>();
                for (int i = 0; i < movieResponse.size(); i++) {
                    MovieModel response = movieResponse.get(i);
                    MovieEntity movie = new MovieEntity(response.getId(),
                            response.getTitle(),
                            response.getOriginal_title(),
                            response.getRelease_date(),
                            response.getOverview(),
                            response.getPoster_path()
                    );

                    movieList.add(movie);
                }
                movieResults.postValue(movieList);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
        return movieResults;
    }

    @Override
    public LiveData<MovieEntity> getMovieById(String movieId) {
        MutableLiveData<MovieEntity> movieById = new MutableLiveData<>();

        remoteRepository.getMovie(new RemoteRepository.LoadMovieCallback() {
            @Override
            public void onMovieReceived(List<MovieModel> movieResponse) {
                for (int i = 0; i < movieResponse.size(); i++) {
                    MovieModel response = movieResponse.get(i);
                    if (response.getId().equals(movieId)) {
                        MovieEntity movie = new MovieEntity(response.getId(),
                                response.getTitle(),
                                response.getOriginal_title(),
                                response.getRelease_date(),
                                response.getOverview(),
                                response.getPoster_path()
                        );
                        movieById.postValue(movie);
                    }
                }
            }

            @Override
            public void onDataNotAvailable() {

            }
        });

        return movieById;
    }

    @Override
    public LiveData<List<TvEntity>> getAllTv() {
        MutableLiveData<List<TvEntity>> tvResults = new MutableLiveData<>();

        remoteRepository.getTv(new RemoteRepository.LoadTvCallback() {
            @Override
            public void onTvReceived(List<TvModel> tvResponse) {
                ArrayList<TvEntity> movieList = new ArrayList<>();
                for (int i = 0; i < tvResponse.size(); i++) {
                    TvModel response = tvResponse.get(i);
                    TvEntity tv = new TvEntity(response.getId(),
                            response.getName(),
                            response.getOriginalName(),
                            response.getFirstAirDate(),
                            response.getOverview(),
                            response.getPosterPath()
                    );

                    movieList.add(tv);
                }
                tvResults.postValue(movieList);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
        return tvResults;
    }

    @Override
    public LiveData<TvEntity> getTvById(String movieId) {
        MutableLiveData<TvEntity> tvById = new MutableLiveData<>();

        remoteRepository.getTv(new RemoteRepository.LoadTvCallback() {
            @Override
            public void onTvReceived(List<TvModel> tvResponse) {
                for (int i = 0; i < tvResponse.size(); i++) {
                    TvModel response = tvResponse.get(i);
                    if (response.getId().equals(movieId)) {
                        TvEntity tv = new TvEntity(response.getId(),
                                response.getName(),
                                response.getOriginalName(),
                                response.getFirstAirDate(),
                                response.getOverview(),
                                response.getPosterPath()
                        );
                        tvById.postValue(tv);
                    }
                }
            }

            @Override
            public void onDataNotAvailable() {

            }
        });

        return tvById;
    }
}
