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

import java.util.List;

public class GalleryImagesAdapter extends PagerAdapter {

    private Context mContext;
    private List<String> mImages;

    public GalleryImagesAdapter(Context context, List<String> images) {
        mContext = context;
        mImages = images;
    }

    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View imageLayout = LayoutInflater.from(mContext).inflate(R.layout.item_gallery_image, container, false);
        ImageView imageView = imageLayout.findViewById(R.id.iv_gallery_image);

        Glide.with(mContext)
                .load(mImages.get(position))
                .into(imageView);

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
    public boolean isViewFromObject(@NonNull View view,  Object object) {
        return view == object;
    }
}
