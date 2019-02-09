package com.kalashnyk.denys.qrscanner.repository.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.kalashnyk.denys.qrscanner.repository.database.entity.ProductEntity;

import java.util.List;

@Dao
public interface ProductDAO {

    @Query("SELECT * FROM product")
    List<ProductEntity> getAll();

    @Query("SELECT * FROM product WHERE mId = :mId")
    ProductEntity getById(int mId);

    @Query("SELECT * FROM product WHERE mScanned = 1")
    List<ProductEntity> getAllScanned();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertList(List<ProductEntity> list);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void update(ProductEntity productEntity);

    @Update
    void updateAll(List<ProductEntity> list);

    @Delete
    void delete(ProductEntity productEntity);

}
