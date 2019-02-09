package com.kalashnyk.denys.qrscanner.di.module;

import android.app.Application;

import com.kalashnyk.denys.qrscanner.App;
import com.kalashnyk.denys.qrscanner.di.scope.ViewModelScope;
import com.kalashnyk.denys.qrscanner.domain.AllProductsListViewModel;
import com.kalashnyk.denys.qrscanner.domain.GalleryItemViewModel;
import com.kalashnyk.denys.qrscanner.domain.ItemViewModel;
import com.kalashnyk.denys.qrscanner.domain.ScannedProductsListViewModel;
import com.kalashnyk.denys.qrscanner.repository.QRScannerRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewModelModule {

    Application mApp;

    public ViewModelModule(App app) {
        mApp = app;
    }

    @ViewModelScope
    @Provides
    AllProductsListViewModel providesListViewModel(QRScannerRepository repository) {
        return new AllProductsListViewModel(mApp, repository);
    }


    @ViewModelScope
    @Provides
    ItemViewModel providesItemViewModel(QRScannerRepository repository) {
        return new ItemViewModel(mApp, repository);
    }

    @ViewModelScope
    @Provides
    GalleryItemViewModel providesGalleryItemViewModel(QRScannerRepository repository) {
        return new GalleryItemViewModel(mApp, repository);
    }

    @ViewModelScope
    @Provides
    ScannedProductsListViewModel providesScannedProductsListViewModel(QRScannerRepository repository) {
        return new ScannedProductsListViewModel(mApp, repository);
    }

}