package com.mzone.jetpack.ui.tv;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.mzone.jetpack.data.source.Repository;
import com.mzone.jetpack.data.source.local.entity.TvEntity;
import com.mzone.jetpack.utils.DataDummy;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TvViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private TvViewModel viewModel;
    private Repository repository = mock(Repository.class);

    @Before
    public void setUp() {
        viewModel = new TvViewModel(repository);
    }

    @Test
    public void getTv() {
        ArrayList<TvEntity> dummyMovie = DataDummy.generateDummyTv();

        MutableLiveData<List<TvEntity>> movies = new MutableLiveData<>();
        movies.setValue(dummyMovie);

        when(repository.getAllTv()).thenReturn(movies);

        Observer<List<TvEntity>> observer = mock(Observer.class);

        viewModel.getTv().observeForever(observer);

        verify(observer).onChanged(dummyMovie);
    }
}