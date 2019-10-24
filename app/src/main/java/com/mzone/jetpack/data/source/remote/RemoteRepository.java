package com.mzone.jetpack.data.source.remote;

import android.os.Handler;

import com.mzone.jetpack.data.source.remote.response.MovieModel;
import com.mzone.jetpack.data.source.remote.response.TvModel;
import com.mzone.jetpack.utils.JsonHelper;

import java.util.List;

public class RemoteRepository {

    private static RemoteRepository INSTANCE;
    private JsonHelper jsonHelper;
    private final long SERVICE_LATENCY_IN_MILLIS = 2000;

    private RemoteRepository(JsonHelper jsonHelper) {
        this.jsonHelper = jsonHelper;
    }

    public static RemoteRepository getInstance(JsonHelper helper) {
        if (INSTANCE == null) {
            INSTANCE = new RemoteRepository(helper);
        }
        return INSTANCE;
    }

    public void getMovie(LoadMovieCallback callback) {
        Handler handler = new Handler();
        handler.postDelayed(() -> callback.onMovieReceived(jsonHelper.loadMovie()), SERVICE_LATENCY_IN_MILLIS);
    }
    public void getTv(LoadTvCallback callback) {
        Handler handler = new Handler();
        handler.postDelayed(() -> callback.onTvReceived(jsonHelper.loadTv()), SERVICE_LATENCY_IN_MILLIS);
    }

    public interface LoadMovieCallback {
        void onMovieReceived(List<MovieModel> movieModels);

        void onDataNotAvailable();
    }
    public interface LoadTvCallback {
        void onTvReceived(List<TvModel> tvShowModels);

        void onDataNotAvailable();
    }
}
