package com.kalashnyk.denys.qrscanner;

import android.app.Application;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;

import com.kalashnyk.denys.qrscanner.di.component.DaggerRepositoryComponent;
import com.kalashnyk.denys.qrscanner.di.component.DaggerViewModelComponent;
import com.kalashnyk.denys.qrscanner.di.component.RepositoryComponent;
import com.kalashnyk.denys.qrscanner.di.module.RepositoryModule;
import com.kalashnyk.denys.qrscanner.di.module.ViewModelModule;
import com.kalashnyk.denys.qrscanner.di.component.ApiComponent;
import com.kalashnyk.denys.qrscanner.di.component.DAOComponent;
import com.kalashnyk.denys.qrscanner.di.component.DaggerApiComponent;
import com.kalashnyk.denys.qrscanner.di.component.DaggerDAOComponent;
import com.kalashnyk.denys.qrscanner.di.component.ViewModelComponent;
import com.kalashnyk.denys.qrscanner.di.module.ApiModule;
import com.kalashnyk.denys.qrscanner.di.module.DAOModule;
import com.kalashnyk.denys.qrscanner.repository.database.QRScannerDatabase;

public class App extends Application {

    private ViewModelComponent mViewModelComponent;
    private QRScannerDatabase mDatabase;

    public void onCreate() {
        super.onCreate();
        initRoom();
        initDagger();

    }

    private void initRoom() {
        mDatabase = Room.databaseBuilder(this, QRScannerDatabase.class, "qrscanner")
                .allowMainThreadQueries()
                .build();
    }

    public ViewModelComponent getViewModelComponent() {
        return mViewModelComponent;
    }

    private void initDagger() {
        ApiComponent apiComponent = DaggerApiComponent.builder()
                .apiModule(new ApiModule())
                .build();

        DAOComponent daoComponent = DaggerDAOComponent.builder()
                .dAOModule(new DAOModule(mDatabase))
                .build();

        RepositoryComponent repositoryComponent = DaggerRepositoryComponent.builder()
                .apiComponent(apiComponent)
                .dAOComponent(daoComponent)
                .repositoryModule(new RepositoryModule())
                .build();

        mViewModelComponent = DaggerViewModelComponent.builder()
                .repositoryComponent(repositoryComponent)
                .viewModelModule(new ViewModelModule(this))
                .build();
    }
}
