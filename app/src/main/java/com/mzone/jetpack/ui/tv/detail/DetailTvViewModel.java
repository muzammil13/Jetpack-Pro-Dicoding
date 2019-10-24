package com.mzone.jetpack.ui.tv.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.mzone.jetpack.data.source.Repository;
import com.mzone.jetpack.data.source.local.entity.TvEntity;

public class DetailTvViewModel extends ViewModel {

    private String tvId;
    private Repository repository;

    public DetailTvViewModel(Repository repository) {
        this.repository = repository;
    }

    LiveData<TvEntity> getTvById() {
        return repository.getTvById(tvId);
    }

    public void setTvId(String tvId) {
        this.tvId = tvId;
    }
}
