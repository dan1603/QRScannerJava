package com.kalashnyk.denys.qrscanner.presentation.activities.gallery_detail;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.Pair;
import android.view.MenuItem;

import com.kalashnyk.denys.qrscanner.R;
import com.kalashnyk.denys.qrscanner.di.component.ViewModelComponent;
import com.kalashnyk.denys.qrscanner.domain.GalleryItemViewModel;
import com.kalashnyk.denys.qrscanner.domain.ItemViewModel;
import com.kalashnyk.denys.qrscanner.presentation.adapter.GalleryImagesAdapter;
import com.kalashnyk.denys.qrscanner.presentation.base.BaseActivity;
import com.kalashnyk.denys.qrscanner.repository.database.entity.ProductEntity;
import com.rbrooks.indefinitepagerindicator.IndefinitePagerIndicator;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GalleryDetailActivity extends BaseActivity {

    @Inject
    GalleryItemViewModel mViewModel;

    private int mProductId;

    @BindView(R.id.pager_gallery_detail)
    protected ViewPager mPager;
    @BindView(R.id.pager_gallery_indicator_detail)
    protected IndefinitePagerIndicator mIndicator;

    public static Intent newInstance(Context context, int id) {
        Intent intent = new Intent(context, GalleryDetailActivity.class);
        intent.putExtra(context.getString(R.string.EXTRAS_ID), id);
        return intent;
    }

    @Override
    protected void injectDependency(ViewModelComponent component) {
        component.inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_detail);
        ButterKnife.bind(this);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        initViewModel();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initViewModel() {
        mProductId = getIntent().getIntExtra(getString(R.string.EXTRAS_ID), 0);
        mViewModel.getItem(this, mProductId);
        mViewModel.getLiveDataItem().observe(this, this::initViewPager);
    }

    private void initViewPager(Pair<String, List<String>> data) {
        if(data.first != null && data.second != null) {
            Objects.requireNonNull(getSupportActionBar()).setTitle(data.first);
            mPager.setAdapter(new GalleryImagesAdapter(this, data.second));
            mIndicator.attachToViewPager(mPager);
        }
    }
}
