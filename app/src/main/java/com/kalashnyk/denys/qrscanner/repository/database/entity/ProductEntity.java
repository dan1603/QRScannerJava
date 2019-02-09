package com.kalashnyk.denys.qrscanner.repository.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.kalashnyk.denys.qrscanner.repository.database.converters.Converters;

import java.util.List;

@Entity(tableName = "product")
@TypeConverters(Converters.class)
public class ProductEntity {
    @PrimaryKey
    private int mId;

    private String mTitle;

    private String mDesc;

    private int mPrice;

    private String mCode;

    private int mScanned;

    private List<String> mImg;

    public ProductEntity() {}

    public ProductEntity(Integer id, String title, String desc, Integer price, String code, int scanned, List<String> img) {
        setId(id);
        setTitle(title);
        setDesc(desc);
        setPrice(price);
        setCode(code);
        setScanned(scanned);
        setImg(img);
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDesc() {
        return mDesc;
    }

    public void setDesc(String description) {
        mDesc = description;
    }

    public int getPrice() {
        return mPrice;
    }

    public void setPrice(int price) {
        mPrice = price;
    }

    public String getCode() {
        return mCode;
    }

    public void setCode(String code) {
        mCode = code;
    }

    public int getScanned() {
        return mScanned;
    }

    public void setScanned(int param) {
        mScanned = param;
    }

    public List<String> getImg() {
        return mImg;
    }

    public void setImg(List<String> images) {
        mImg = images;
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "mId=" + getId() +
                ", mTitle='" + getTitle() + '\'' +
                ", mDescription='" + getDesc() + '\'' +
                ", mPrice='" + getPrice() + '\'' +
                ", mCode=" + getCode() +
                ", mScanned=" + getScanned() +
                ", mImg=" + getImg() +
                '}';
    }
}
