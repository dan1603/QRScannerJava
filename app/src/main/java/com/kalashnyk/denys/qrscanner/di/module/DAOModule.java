package com.kalashnyk.denys.qrscanner.di.module;

import com.kalashnyk.denys.qrscanner.repository.database.QRScannerDatabase;
import com.kalashnyk.denys.qrscanner.repository.database.dao.ProductDAO;

import dagger.Module;
import dagger.Provides;

@Module
public class DAOModule {


    private QRScannerDatabase mQRScannerDatabase;

    public DAOModule(QRScannerDatabase database) {
        mQRScannerDatabase = database;
    }

    @Provides
    QRScannerDatabase providesRoomDatabase() {
        return mQRScannerDatabase;
    }

    @Provides
    ProductDAO providesProductDao(QRScannerDatabase demoDatabase) {
        return demoDatabase.ProductDao();
    }
}
