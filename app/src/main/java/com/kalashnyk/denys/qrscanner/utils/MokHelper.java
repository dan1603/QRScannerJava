package com.kalashnyk.denys.qrscanner.utils;

import android.content.Context;
import android.util.Log;

import com.kalashnyk.denys.qrscanner.R;
import com.kalashnyk.denys.qrscanner.repository.database.entity.ProductEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MokHelper {

    private Context mContext;

    public MokHelper(Context context) {
        mContext = context;
    }

    public Integer getProductsCount() {
        return mContext.getResources().getStringArray(R.array.mok_product_id).length;
    }

    public List<ProductEntity> getProducts() {
        List<ProductEntity> prod = new ArrayList<>();
        String[] id = mContext.getResources().getStringArray(R.array.mok_product_id);
        String[] title = mContext.getResources().getStringArray(R.array.mok_product_title);
        String[] desc = mContext.getResources().getStringArray(R.array.mok_product_description);
        String[] price = mContext.getResources().getStringArray(R.array.mok_product_price);
        String[] code = mContext.getResources().getStringArray(R.array.mok_product_code);
        String[] isScanned = mContext.getResources().getStringArray(R.array.mok_product_is_scanned);


        for (Integer i = 0; i < getProductsCount(); i++) {
            prod.add(new ProductEntity(Integer.parseInt(id[i]),
                    title[i], desc[i], Integer.parseInt(price[i]), code[i],
                    Integer.valueOf(isScanned[i]), getProductImgById(i)));
        }
        return prod;
    }

    public ProductEntity getProduct(int productId) {
        String[] id = mContext.getResources().getStringArray(R.array.mok_product_id);
        String[] title = mContext.getResources().getStringArray(R.array.mok_product_title);
        String[] desc = mContext.getResources().getStringArray(R.array.mok_product_description);
        String[] price = mContext.getResources().getStringArray(R.array.mok_product_price);
        String[] code = mContext.getResources().getStringArray(R.array.mok_product_code);
        String[] isScanned = mContext.getResources().getStringArray(R.array.mok_product_is_scanned);

        return new ProductEntity(Integer.parseInt(id[productId]),
                title[productId], desc[productId], Integer.parseInt(price[productId]), code[productId],
                Integer.valueOf(isScanned[productId]), getProductImgById(productId));
    }

    public List<String> getProductImgById(Integer id) {
        List<String> listImages = new ArrayList<>();
        switch (id) {
            case 0:
                listImages.addAll(Arrays.asList(mContext.getResources().getStringArray(R.array.mok_product_iphonexr_img)));
                break;
            case 1:
                listImages.addAll(Arrays.asList(mContext.getResources().getStringArray(R.array.mok_product_xiaomi_img)));
                break;
            case 2:
                listImages.addAll(Arrays.asList(mContext.getResources().getStringArray(R.array.mok_product_samsung_img)));
                break;
            case 3:
                listImages.addAll(Arrays.asList(mContext.getResources().getStringArray(R.array.mok_product_sony_img)));
                break;
            case 4:
                listImages.addAll(Arrays.asList(mContext.getResources().getStringArray(R.array.mok_product_meizu_img)));
                break;
            case 5:
                listImages.addAll(Arrays.asList(mContext.getResources().getStringArray(R.array.mok_product_huawei_img)));
                break;
            case 6:
                listImages.addAll(Arrays.asList(mContext.getResources().getStringArray(R.array.mok_product_oneplus_img)));
                break;
            case 7:
                listImages.addAll(Arrays.asList(mContext.getResources().getStringArray(R.array.mok_product_asus_img)));
                break;
            case 8:
                listImages.addAll(Arrays.asList(mContext.getResources().getStringArray(R.array.mok_product_blackview_img)));
                break;
            case 9:
                listImages.addAll(Arrays.asList(mContext.getResources().getStringArray(R.array.mok_product_lg_img)));
                break;
            case 10:
                listImages.addAll(Arrays.asList(mContext.getResources().getStringArray(R.array.mok_product_nokia_img)));
                break;
            case 11:
                listImages.addAll(Arrays.asList(mContext.getResources().getStringArray(R.array.mok_product_sigma_img)));
                break;
            case 12:
                listImages.addAll(Arrays.asList(mContext.getResources().getStringArray(R.array.mok_product_honor_img)));
                break;
            case 13:
                listImages.addAll(Arrays.asList(mContext.getResources().getStringArray(R.array.mok_product_ulefone_img)));
                break;
            case 14:
                listImages.addAll(Arrays.asList(mContext.getResources().getStringArray(R.array.mok_prodict_cat_img)));
                break;
        }
        return listImages;
    }
}
