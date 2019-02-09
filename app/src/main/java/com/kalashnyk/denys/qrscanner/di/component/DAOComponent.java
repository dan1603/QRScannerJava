package com.kalashnyk.denys.qrscanner.di.component;

import com.kalashnyk.denys.qrscanner.repository.database.dao.ProductDAO;
import com.kalashnyk.denys.qrscanner.di.module.DAOModule;

import dagger.Component;

@Component(modules = {DAOModule.class})
public interface DAOComponent {

    ProductDAO getProductDAO();

}
