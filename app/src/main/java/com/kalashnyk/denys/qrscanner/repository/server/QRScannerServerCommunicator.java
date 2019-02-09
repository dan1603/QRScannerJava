package com.kalashnyk.denys.qrscanner.repository.server;

import android.content.Context;

import com.kalashnyk.denys.qrscanner.repository.database.entity.ProductEntity;
import com.kalashnyk.denys.qrscanner.utils.MokHelper;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.CompletableTransformer;
import io.reactivex.ObservableTransformer;
import io.reactivex.Single;
import io.reactivex.SingleTransformer;
import io.reactivex.schedulers.Schedulers;

public class QRScannerServerCommunicator {
    private static final int DEFAULT_TIMEOUT = 10;
    private static final int DEFAULT_RETRY_ATTEMPTS = 4;
    private ApiService mService;

    public QRScannerServerCommunicator(ApiService service) {
        mService = service;
    }

    public Single<List<ProductEntity>> getAllProducts(Context context){
        return Single.just(new MokHelper(context).getProducts());
    }

    public Single<ProductEntity> getProduct(Context context, int id){
        return Single.just(new MokHelper(context).getProduct(id));
    }

    private static <T> SingleTransformer<T, T> singleTransformer() {
        return upstream -> upstream
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .timeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .retry(DEFAULT_RETRY_ATTEMPTS);
    }

    private static <T> ObservableTransformer<T, T> observableTransformer() {
        return observable -> observable
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .timeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .retry(DEFAULT_RETRY_ATTEMPTS);
    }

    private static CompletableTransformer completableTransformer() {
        return upstream -> upstream
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .timeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .retry(DEFAULT_RETRY_ATTEMPTS);
    }
}
