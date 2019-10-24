package com.mzone.jetpack.utils;

import android.app.Application;

import com.mzone.jetpack.data.source.remote.response.MovieModel;
import com.mzone.jetpack.data.source.remote.response.TvModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class JsonHelper {

    private Application application;

    public JsonHelper(Application application) {
        this.application = application;
    }

    private String parsingFileToString(String fileName) {
        try {
            InputStream is = application.getAssets().open(fileName);
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            return new String(buffer);

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<MovieModel> loadMovie() {
        ArrayList<MovieModel> list = new ArrayList<>();

        try {
            JSONObject responseObject = new JSONObject(parsingFileToString("DataMovie.json"));
            JSONArray listArray = responseObject.getJSONArray("results");
            for (int i = 0; i < listArray.length(); i++) {
                JSONObject movie = listArray.getJSONObject(i);

                String popularity = movie.getString("popularity");
                String vote_count = movie.getString("vote_count");
                String video = movie.getString("video");
                String poster_path = movie.getString("poster_path");
                String id = movie.getString("id");
                String adult = movie.getString("adult");
                String backdrop_path = movie.getString("backdrop_path");
                String original_language = movie.getString("original_language");
                String original_title = movie.getString("original_title");
                String genre_ids = movie.getString("genre_ids");
                String title = movie.getString("title");
                String vote_average = movie.getString("vote_average");
                String overview = movie.getString("overview");
                String release_date = movie.getString("release_date");

                MovieModel movieData = new MovieModel(
                        popularity,
                        vote_count,
                        video,
                        poster_path,
                        id,
                        adult,
                        backdrop_path,
                        original_language,
                        original_title,
                        genre_ids,
                        title,
                        vote_average,
                        overview,
                        release_date
                );
                list.add(movieData);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<TvModel> loadTv() {
        ArrayList<TvModel> list = new ArrayList<>();

        try {
            JSONObject responseObject = new JSONObject(parsingFileToString("DataTv.json"));
            JSONArray listArray = responseObject.getJSONArray("results");
            for (int i = 0; i < listArray.length(); i++) {
                JSONObject tv = listArray.getJSONObject(i);

                String original_name = tv.getString("original_name");
                String genre_ids = tv.getString("genre_ids");
                String name = tv.getString("name");
                String popularity = tv.getString("popularity");
                String origin_country = tv.getString("origin_country");
                String vote_count = tv.getString("vote_count");
                String first_air_date = tv.getString("first_air_date");
                String backdrop_path = tv.getString("backdrop_path");
                String original_language = tv.getString("original_language");
                String id = tv.getString("id");
                String vote_average = tv.getString("vote_average");
                String overview = tv.getString("overview");
                String poster_path = tv.getString("poster_path");

                TvModel tvData = new TvModel(
                        first_air_date,
                        overview,
                        original_language,
                        genre_ids,
                        poster_path,
                        origin_country,
                        backdrop_path,
                        original_name,
                        popularity,
                        vote_average,
                        name,
                        id,
                        vote_count
                );
                list.add(tvData);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
