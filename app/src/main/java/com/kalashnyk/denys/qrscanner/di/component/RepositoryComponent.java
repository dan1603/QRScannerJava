package com.kalashnyk.denys.qrscanner.di.component;

import com.kalashnyk.denys.qrscanner.di.module.RepositoryModule;
import com.kalashnyk.denys.qrscanner.di.scope.Repositorycope;
import com.kalashnyk.denys.qrscanner.repository.QRScannerRepository;

import dagger.Component;

@Repositorycope
@Component(modules = {RepositoryModule.class}, dependencies = {ApiComponent.class, DAOComponent.class})
public interface RepositoryComponent {

    QRScannerRepository getQRScannerRepository();
}
