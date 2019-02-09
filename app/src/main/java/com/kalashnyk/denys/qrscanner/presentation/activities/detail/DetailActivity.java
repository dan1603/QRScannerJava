package com.kalashnyk.denys.qrscanner.presentation.activities.detail;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.widget.TextView;

import com.kalashnyk.denys.qrscanner.R;
import com.kalashnyk.denys.qrscanner.di.component.ViewModelComponent;
import com.kalashnyk.denys.qrscanner.domain.ItemViewModel;
import com.kalashnyk.denys.qrscanner.presentation.adapter.ProductImagesAdapter;
import com.kalashnyk.denys.qrscanner.presentation.base.BaseActivity;
import com.kalashnyk.denys.qrscanner.repository.database.entity.ProductEntity;
import com.rbrooks.indefinitepagerindicator.IndefinitePagerIndicator;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends BaseActivity {

    @Inject
    ItemViewModel mViewModel;

    @BindView(R.id.tv_rv_product_title)
    protected TextView mProductTitleTextView;
    @BindView(R.id.tv_rv_product_desc)
    protected TextView mProductDescTextView;
    @BindView(R.id.tv_rv_product_price)
    protected TextView mProductPriceTextView;
    @BindView(R.id.pager_product_detail)
    protected ViewPager mPager;
    @BindView(R.id.tv_status)
    protected TextView mScanStatus;
    @BindView(R.id.tv_scanned_code)
    protected TextView mScannedCode;
    @BindView(R.id.pager_product_indicator)
    protected IndefinitePagerIndicator mIndicator;

    private int mProductId;

    public static Intent newInstance(Context context, int id) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(context.getString(R.string.EXTRAS_ID), id);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
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

    @Override
    protected void injectDependency(ViewModelComponent component) {
        component.inject(this);
    }

    private void initViewModel() {
        mProductId = getIntent().getIntExtra(getString(R.string.EXTRAS_ID), 0);
        mViewModel.getItem(this, mProductId);
        mViewModel.getLiveDataItem().observe(this, new Observer<ProductEntity>() {
            @Override
            public void onChanged(@Nullable ProductEntity productEntitie) {
                initActionBar(productEntitie.getTitle());
                initScannedStatus(productEntitie);
                initTextViews(
                        productEntitie.getTitle(),
                        productEntitie.getDesc(),
                        String.valueOf(productEntitie.getPrice()));
                        initViewPager(productEntitie.getImg(), mProductId);
            }

        });
    }

    private void initScannedStatus(ProductEntity productEntitie) {
        if(productEntitie.getScanned() == 1) {
            mScanStatus.setText(getString(R.string.scanned));
            mScannedCode.setText(productEntitie.getCode());
        }
    }

    private void initActionBar(String title) {
        Objects.requireNonNull(getSupportActionBar()).setTitle(title);
    }

    private void initTextViews(String title, String desc, String price) {
        TextView tv = findViewById(R.id.tv_rv_product_title);
        tv.setText(title);
        mProductTitleTextView.setText(title);
        mProductDescTextView.setText(desc);
        mProductPriceTextView.setText(price);
    }

    private void initViewPager(List<String> images, int id) {
        mPager.setAdapter(new ProductImagesAdapter(this, images, id, true));
        mIndicator.attachToViewPager(mPager);
    }
}
