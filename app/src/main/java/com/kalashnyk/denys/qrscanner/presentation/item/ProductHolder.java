package com.kalashnyk.denys.qrscanner.presentation.item;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kalashnyk.denys.qrscanner.R;
import com.kalashnyk.denys.qrscanner.repository.database.entity.ProductEntity;
import com.kalashnyk.denys.qrscanner.presentation.adapter.ProductImagesAdapter;
import com.rbrooks.indefinitepagerindicator.IndefinitePagerIndicator;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_rv_product_title)
    protected TextView mProductTitle;
    @BindView(R.id.tv_rv_product_desc)
    protected TextView mProductDesc;
    @BindView(R.id.tv_rv_product_price)
    protected TextView mProductPrice;
    @BindView(R.id.pager_product_detail)
    protected ViewPager mPager;
    @BindView(R.id.pager_product_indicator)
    protected IndefinitePagerIndicator mIndicator;
    @BindView(R.id.btn_scan)
    protected Button mBtnScan;
    @BindView(R.id.v_item_product_scan_data)
    protected View mVScanData;
    @BindView(R.id.tv_status)
    protected TextView mScanStatus;
    @BindView(R.id.tv_scanned_code)
    protected TextView mScannedCode;

    private ProductEntity mProduct;
    private IProductItemClickListener<ProductEntity> mListener;
    private View mItemView;
    private Boolean mIsNeedScan;

    private View.OnClickListener mItemScan = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mListener.scanItem(mProduct, getAdapterPosition());
        }
    };

    private View.OnClickListener mItemDetail = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mListener.openDetail(mProduct);
        }
    };

    public ProductHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mItemView = itemView;
    }

    public void bind(ProductEntity product, IProductItemClickListener listener) {
        mProduct = product;
        mListener = listener;
        mIsNeedScan = product.getScanned() == 1;
        mScanStatus.setText(itemView.getContext().getString(R.string.scanned));
        mScannedCode.setText(product.getCode());
        setupButton();
        setupTitle(product.getTitle());
        setupDescription(product.getDesc());

        setupGallery(itemView.getContext(), product.getImg(), mProduct.getId());
        setupPrice(product.getPrice());
        mItemView.setOnClickListener(mItemDetail);
        mBtnScan.setOnClickListener(mItemScan);

    }

    private void setupPrice(int mPrice) {
        mProductPrice.setText(String.valueOf(mPrice));
    }


    private void setupDescription(String description) {
        mProductDesc.setEllipsize(TextUtils.TruncateAt.END);
        mProductDesc.setMaxLines(2);
        mProductDesc.setText(description);
    }

    private void setupGallery(Context context, List<String> images, int id) {
        mPager.setAdapter(new ProductImagesAdapter(context, images, id, false));
        mIndicator.attachToViewPager(mPager);
    }

    private void setupTitle(String title) {
        mProductTitle.setText(title);
    }

    private void setupButton() {
        if (!mIsNeedScan) {
            mBtnScan.setVisibility(View.VISIBLE);
            mVScanData.setVisibility(View.GONE);
        } else {
            mVScanData.setVisibility(View.VISIBLE);
            mBtnScan.setVisibility(View.GONE);
        }
    }
}
