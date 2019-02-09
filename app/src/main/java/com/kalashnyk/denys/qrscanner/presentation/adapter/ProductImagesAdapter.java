package com.kalashnyk.denys.qrscanner.presentation.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.kalashnyk.denys.qrscanner.R;
import com.kalashnyk.denys.qrscanner.presentation.activities.detail.DetailActivity;
import com.kalashnyk.denys.qrscanner.presentation.activities.gallery_detail.GalleryDetailActivity;

import java.util.List;

public class ProductImagesAdapter extends PagerAdapter {

    private Context mContext;
    private List<String> mImages;
    private int mPosition;
    private boolean mIsNeedDetail;

    private View.OnClickListener mOpenDetailOrGallery = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            openScreenDetailOrGalleryItem(mIsNeedDetail);
        }
    };

    public ProductImagesAdapter(Context context, List<String> images, int pos, boolean isNeedDetail) {
        mContext = context;
        mImages = images;
        mPosition = pos;
        mIsNeedDetail = isNeedDetail;
    }

    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View imageLayout = LayoutInflater.from(mContext).inflate(R.layout.item_product_image, container, false);
        ImageView imageView = imageLayout.findViewById(R.id.iv_product_item_image);

        Glide.with(mContext)
                .load(mImages.get(position))
                .into(imageView);


        imageView.setOnClickListener(mOpenDetailOrGallery);
        container.addView(imageLayout, 0);
        return imageLayout;
    }

    @Override
    public int getCount() {
        return mImages.size();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup collection, int position, @NonNull Object view) {
        collection.removeView((View) view);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, Object object) {
        return view == object;
    }

    private void openScreenDetailOrGalleryItem(boolean isNeedDetail) {
        mContext.startActivity(isNeedDetail ? GalleryDetailActivity.newInstance(mContext, mPosition) : DetailActivity.newInstance(mContext, mPosition));
    }
}
