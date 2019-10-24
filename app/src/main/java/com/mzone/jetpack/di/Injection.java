package com.mzone.jetpack.di;

import android.app.Application;

import com.mzone.jetpack.data.source.Repository;
import com.mzone.jetpack.data.source.remote.RemoteRepository;
import com.mzone.jetpack.utils.JsonHelper;

public class Injection {

    public static Repository provideRepository(Application application) {
        RemoteRepository remoteRepository = RemoteRepository.getInstance(new JsonHelper(application));
        return Repository.getInstance(remoteRepository);
    }
}
