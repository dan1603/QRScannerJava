package com.kalashnyk.denys.qrscanner.di.component;

import com.kalashnyk.denys.qrscanner.di.scope.ApiScope;
import com.kalashnyk.denys.qrscanner.repository.server.QRScannerServerCommunicator;
import com.kalashnyk.denys.qrscanner.di.module.ApiModule;

import dagger.Component;

@ApiScope
@Component(modules = {ApiModule.class})
public interface ApiComponent {
    QRScannerServerCommunicator getUserServerCommunicator();
}
