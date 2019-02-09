package com.kalashnyk.denys.qrscanner.di.module;


import com.kalashnyk.denys.qrscanner.di.scope.Repositorycope;
import com.kalashnyk.denys.qrscanner.repository.QRScannerRepository;
import com.kalashnyk.denys.qrscanner.repository.database.dao.ProductDAO;
import com.kalashnyk.denys.qrscanner.repository.server.QRScannerServerCommunicator;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Repositorycope
    @Provides
    QRScannerRepository providesProductRepository(QRScannerServerCommunicator communicator, ProductDAO productDAO) {
        return new QRScannerRepository(communicator, productDAO);
    }
}
