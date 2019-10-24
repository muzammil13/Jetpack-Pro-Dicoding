package com.mzone.jetpack.ui.tv;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.mzone.jetpack.data.source.Repository;
import com.mzone.jetpack.data.source.local.entity.TvEntity;

import java.util.List;

public class TvViewModel extends ViewModel {

    private Repository repository;

    public TvViewModel(Repository repository) {
        this.repository = repository;
    }

    LiveData<List<TvEntity>> getTv() {
        return repository.getAllTv();
    }
}
