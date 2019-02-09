package com.kalashnyk.denys.qrscanner.domain;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.util.Log;
import android.util.Pair;

import com.kalashnyk.denys.qrscanner.presentation.widget.SingleLiveEvent;
import com.kalashnyk.denys.qrscanner.repository.QRScannerRepository;

import java.util.List;

public class GalleryItemViewModel extends BaseViewModel{

    private QRScannerRepository mRepository;
    private SingleLiveEvent<Pair<String, List<String>>> liveDataItems = new SingleLiveEvent<>();

    public GalleryItemViewModel(Application application, QRScannerRepository repository) {
        super(application);
        mRepository = repository;
    }

    @SuppressLint("CheckResult")
    public void getItem(Context context, int id) {
        mRepository.getProductGallery(context, id).subscribe(product -> liveDataItems.setValue(product), throwable -> Log.d("CheckId", "GalleryItemViewModel throwable - " + throwable.getMessage()));
    }

    public LiveData<Pair<String, List<String>>> getLiveDataItem() {
        return liveDataItems;
    }
}
