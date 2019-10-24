package com.mzone.jetpack.ui.movie;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.mzone.jetpack.data.source.Repository;
import com.mzone.jetpack.data.source.local.entity.MovieEntity;
import com.mzone.jetpack.utils.DataDummy;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MovieViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private MovieViewModel viewModel;
    private Repository repository = mock(Repository.class);

    @Before
    public void setUp() {
        viewModel = new MovieViewModel(repository);
    }

    @Test
    public void getMovies() {
        ArrayList<MovieEntity> dummyMovie = DataDummy.generateDummyMovies();

        MutableLiveData<List<MovieEntity>> movies = new MutableLiveData<>();
        movies.setValue(dummyMovie);

        when(repository.getAllMovie()).thenReturn(movies);

        Observer<List<MovieEntity>> observer = mock(Observer.class);

        viewModel.getMovies().observeForever(observer);

        verify(observer).onChanged(dummyMovie);
    }
}