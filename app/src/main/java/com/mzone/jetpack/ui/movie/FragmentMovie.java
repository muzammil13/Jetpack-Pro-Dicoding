package com.mzone.jetpack.ui.movie;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mzone.jetpack.R;
import com.mzone.jetpack.data.source.local.entity.MovieEntity;
import com.mzone.jetpack.viewmodel.ViewModelFactory;

import java.util.List;

public class FragmentMovie extends Fragment {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private List<MovieEntity> movies;

    public FragmentMovie() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rvMovies);
        progressBar = view.findViewById(R.id.pb_movie);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            MovieAdapter adapter = new MovieAdapter(getActivity());
            adapter.setDataMovies(movies);

            MovieViewModel viewModel = obtainViewModel(getActivity());
            if (savedInstanceState == null) {
                progressBar.setVisibility(View.VISIBLE);
                viewModel.getMovies().observe(this, movieEntities -> {
                    if (movieEntities != null) {
                        progressBar.setVisibility(View.GONE);
                        adapter.setDataMovies(movieEntities);
                        movies = movieEntities;
                    }else {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getActivity(), getResources().getString(R.string.something_wrong), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(adapter);
        }
    }

    private static MovieViewModel obtainViewModel(FragmentActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(MovieViewModel.class);
    }
}
