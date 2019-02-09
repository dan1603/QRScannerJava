package com.kalashnyk.denys.qrscanner.repository;

import android.content.Context;
import android.util.Log;
import android.util.Pair;

import com.kalashnyk.denys.qrscanner.repository.database.QRScannerDatabase;
import com.kalashnyk.denys.qrscanner.repository.database.dao.ProductDAO;
import com.kalashnyk.denys.qrscanner.repository.database.entity.ProductEntity;
import com.kalashnyk.denys.qrscanner.repository.server.QRScannerServerCommunicator;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import kotlin.jvm.internal.SpreadBuilder;

public class QRScannerRepository {

    QRScannerServerCommunicator mServerCommunicator;
    ProductDAO mDatabase;

    public QRScannerRepository(QRScannerServerCommunicator serverCommunicator, ProductDAO database) {
        mServerCommunicator = serverCommunicator;
        mDatabase = database;
    }

    public Single<List<ProductEntity>> getAllProducts(Context context) {
        return mServerCommunicator.getAllProducts(context)
                .flatMap(list -> {
                    mDatabase.insertList(list);
                    return Single.just(mDatabase.getAll());
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

    public Flowable<List<ProductEntity>> getScannedProducts() {
        return Flowable.just(mDatabase.getAllScanned())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

    public Single<ProductEntity> getProduct(Context context, int id) {
        return mServerCommunicator.getProduct(context, id)
                        .flatMap(product -> {
                            ProductEntity productEntity = mDatabase.getById(id);
                            return productEntity != null ? Single.just(productEntity) : Single.just(product);
                        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

    public Single<Pair<String, List<String>>> getProductGallery(Context context, int id) {
        return mServerCommunicator.getProduct(context, id)
                .flatMap(product -> {
                    ProductEntity productEntity = mDatabase.getById(id);
                    return productEntity != null ? Single.just(Pair.create(productEntity.getTitle(), productEntity.getImg())) : Single.just(Pair.create(product.getTitle(), product.getImg()));
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

    public void updateProduct(ProductEntity productEntity) {
        mDatabase.update(productEntity);
    }

}
