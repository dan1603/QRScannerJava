package com.kalashnyk.denys.qrscanner.domain;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.util.Log;

import com.kalashnyk.denys.qrscanner.presentation.widget.SingleLiveEvent;
import com.kalashnyk.denys.qrscanner.repository.QRScannerRepository;
import com.kalashnyk.denys.qrscanner.repository.database.entity.ProductEntity;

import java.util.List;

public class AllProductsListViewModel extends BaseViewModel {

    private QRScannerRepository mRepository;
    private SingleLiveEvent<List<ProductEntity>> liveDataItems = new SingleLiveEvent<List<ProductEntity>>();

    public AllProductsListViewModel(Application application, QRScannerRepository repository) {
        super(application);
        mRepository = repository;
    }

    @SuppressLint("CheckResult")
    public void getAllItems(Context context) {
        mRepository.getAllProducts(context).subscribe(list -> liveDataItems.setValue(list), throwable -> {
            Log.d("CheckId" , "throwable - " + throwable.getMessage());
        });
    }

    public void updateProduct(ProductEntity productEntity) {
        mRepository.updateProduct(productEntity);
    }

    public LiveData<List<ProductEntity>> getLiveDataItems() {
        return liveDataItems;
    }

}
