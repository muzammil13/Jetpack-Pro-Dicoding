package com.mzone.jetpack.ui.movie.detail;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.mzone.jetpack.data.source.Repository;
import com.mzone.jetpack.data.source.local.entity.MovieEntity;
import com.mzone.jetpack.utils.DataDummy;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DetailMovieViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private DetailMovieViewModel viewModel;
    private Repository repository = mock(Repository.class);
    private MovieEntity dummyMovie = DataDummy.generateDummyMovies().get(1);
    private String movieId = dummyMovie.getIdMovie();

    @Before
    public void setUp() {
        viewModel = new DetailMovieViewModel(repository);
        viewModel.setMovieId(movieId);
    }

    @Test
    public void getMovieById() {
        MutableLiveData<MovieEntity> movieEntities = new MutableLiveData<>();
        movieEntities.setValue(dummyMovie);

        when(repository.getMovieById(movieId)).thenReturn(movieEntities);

        Observer<MovieEntity> observer = mock(Observer.class);

        viewModel.getMovieById().observeForever(observer);

        verify(observer).onChanged(dummyMovie);
    }
}