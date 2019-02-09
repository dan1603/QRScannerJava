package com.kalashnyk.denys.qrscanner.presentation.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.kalashnyk.denys.qrscanner.R;
import com.kalashnyk.denys.qrscanner.presentation.base.BaseAdapter;
import com.kalashnyk.denys.qrscanner.presentation.item.IProductItemClickListener;
import com.kalashnyk.denys.qrscanner.repository.database.entity.ProductEntity;
import com.kalashnyk.denys.qrscanner.presentation.item.ProductHolder;
import java.util.List;

public class ProductAdapter extends BaseAdapter<ProductHolder, ProductEntity, IProductItemClickListener<ProductEntity>> {

    public ProductAdapter(Context context, List<ProductEntity> list) {
        super(context, list);
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductHolder(LayoutInflater.from(mContext).inflate(R.layout.item_product, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.bind(mList.get(position), mItemClickListener);
    }
}
