package com.mzone.jetpack.ui.tv.detail;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.mzone.jetpack.data.source.Repository;
import com.mzone.jetpack.data.source.local.entity.TvEntity;
import com.mzone.jetpack.utils.DataDummy;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DetailTvViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private DetailTvViewModel viewModel;
    private Repository repository = mock(Repository.class);
    private TvEntity dummyTv = DataDummy.generateDummyTv().get(3);
    private String tvId = dummyTv.getIdTv();

    @Before
    public void setUp() {
        viewModel = new DetailTvViewModel(repository);
        viewModel.setTvId(tvId);
    }

    @Test
    public void getTvById() {
        MutableLiveData<TvEntity> tvEntities = new MutableLiveData<>();
        tvEntities.setValue(dummyTv);

        when(repository.getTvById(tvId)).thenReturn(tvEntities);

        Observer<TvEntity> observer = mock(Observer.class);

        viewModel.getTvById().observeForever(observer);

        verify(observer).onChanged(dummyTv);
    }
}