package com.kalashnyk.denys.qrscanner.presentation.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.kalashnyk.denys.qrscanner.presentation.fragment.AllProductsFragment;
import com.kalashnyk.denys.qrscanner.presentation.fragment.ScannedProductsFragment;
import com.kalashnyk.denys.qrscanner.presentation.item.IProductItemClickListener;
import com.kalashnyk.denys.qrscanner.repository.database.entity.ProductEntity;

public class TabPagerAdapter extends FragmentPagerAdapter {


    public TabPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return AllProductsFragment.newInstance();
            case 1:
                return ScannedProductsFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "ALL";
            case 1:
                return "SCANNED";
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

}
