package com.kalashnyk.denys.qrscanner.domain;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.kalashnyk.denys.qrscanner.presentation.widget.SingleLiveEvent;
import com.kalashnyk.denys.qrscanner.repository.QRScannerRepository;
import com.kalashnyk.denys.qrscanner.repository.database.entity.ProductEntity;

public class ItemViewModel extends BaseViewModel {

    private QRScannerRepository mRepository;
    private SingleLiveEvent<ProductEntity> liveDataItems = new SingleLiveEvent<ProductEntity>();

    public ItemViewModel(Application application, QRScannerRepository repository) {
        super(application);
        mRepository = repository;
    }

    @SuppressLint("CheckResult")
    public void getItem(Context context, int id) {
        mRepository.getProduct(context, id).subscribe(product -> liveDataItems.setValue(product), Throwable::printStackTrace);
    }

    public LiveData<ProductEntity> getLiveDataItem() {
        return liveDataItems;
    }
}
