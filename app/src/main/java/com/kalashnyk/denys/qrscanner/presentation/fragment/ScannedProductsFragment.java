package com.kalashnyk.denys.qrscanner.presentation.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kalashnyk.denys.qrscanner.R;
import com.kalashnyk.denys.qrscanner.di.component.ViewModelComponent;
import com.kalashnyk.denys.qrscanner.domain.ScannedProductsListViewModel;
import com.kalashnyk.denys.qrscanner.presentation.adapter.ProductAdapter;
import com.kalashnyk.denys.qrscanner.presentation.base.BaseFragment;
import com.kalashnyk.denys.qrscanner.repository.database.entity.ProductEntity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScannedProductsFragment extends BaseFragment {

    @Inject
    ScannedProductsListViewModel mViewModel;

    @BindView(R.id.tv_scanned_products_empty)
    protected TextView mTvEmptyList;
    @BindView(R.id.rv_products_scanned)
    protected RecyclerView mRvProductsScanned;

    private ProductAdapter mAdapter;

    public ScannedProductsFragment() {
        // Required empty public constructor
    }

    public static ScannedProductsFragment newInstance() {
        return new ScannedProductsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void injectDependency(ViewModelComponent component) {
        component.inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_scanned_product, container, false);
        ButterKnife.bind(this, v);
        mViewModel.getAllScannedItems();
        mViewModel.getLiveDataItems().observe(this, this::handleList);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void initRecyclerView(List<ProductEntity> list) {
        GridLayoutManager layoutManager = new GridLayoutManager(this.getContext(), 2);
        mAdapter = new ProductAdapter(getContext(), list);
        mRvProductsScanned.setLayoutManager(layoutManager);
        mRvProductsScanned.setAdapter(mAdapter);
    }

    private void handleList(List<ProductEntity> list) {
        if (list != null && !list.isEmpty()) {
            mTvEmptyList.setVisibility(View.GONE);
            mRvProductsScanned.setVisibility(View.VISIBLE);
            initRecyclerView(list);
        } else {
            mRvProductsScanned.setVisibility(View.GONE);
            mTvEmptyList.setVisibility(View.VISIBLE);
        }
    }
}
