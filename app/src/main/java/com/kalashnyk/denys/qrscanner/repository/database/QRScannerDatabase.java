package com.kalashnyk.denys.qrscanner.repository.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.kalashnyk.denys.qrscanner.repository.database.dao.ProductDAO;
import com.kalashnyk.denys.qrscanner.repository.database.entity.ProductEntity;

@Database(entities = {ProductEntity.class}, version = 1)
public abstract class QRScannerDatabase extends RoomDatabase {
    public abstract ProductDAO ProductDao();
}