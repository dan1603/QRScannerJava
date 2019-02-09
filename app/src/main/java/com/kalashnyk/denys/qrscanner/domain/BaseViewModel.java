package com.kalashnyk.denys.qrscanner.domain;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;

import com.kalashnyk.denys.qrscanner.App;

abstract class BaseViewModel extends AndroidViewModel {

    public BaseViewModel(Application application) {
        super(application);
    }

    public App getContext() {
        return getApplication();
    }
}
