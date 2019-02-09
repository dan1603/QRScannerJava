package com.kalashnyk.denys.qrscanner.di.component;

import com.kalashnyk.denys.qrscanner.di.module.ViewModelModule;
import com.kalashnyk.denys.qrscanner.di.scope.ViewModelScope;
import com.kalashnyk.denys.qrscanner.presentation.activities.detail.DetailActivity;
import com.kalashnyk.denys.qrscanner.presentation.activities.gallery_detail.GalleryDetailActivity;
import com.kalashnyk.denys.qrscanner.presentation.fragment.AllProductsFragment;
import com.kalashnyk.denys.qrscanner.presentation.fragment.ScannedProductsFragment;

import dagger.Component;

@ViewModelScope
@Component(modules = {ViewModelModule.class}, dependencies = {RepositoryComponent.class})
public interface ViewModelComponent {

    void inject(DetailActivity activity);
    void inject(GalleryDetailActivity activity);
    void inject(AllProductsFragment fragment);
    void inject(ScannedProductsFragment fragment);
}
