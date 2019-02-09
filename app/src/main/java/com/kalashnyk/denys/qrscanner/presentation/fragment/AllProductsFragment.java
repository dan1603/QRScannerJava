package com.kalashnyk.denys.qrscanner.presentation.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kalashnyk.denys.qrscanner.R;
import com.kalashnyk.denys.qrscanner.di.component.ViewModelComponent;
import com.kalashnyk.denys.qrscanner.domain.AllProductsListViewModel;
import com.kalashnyk.denys.qrscanner.presentation.activities.detail.DetailActivity;
import com.kalashnyk.denys.qrscanner.presentation.activities.scan.ScanActivity;
import com.kalashnyk.denys.qrscanner.presentation.adapter.ProductAdapter;
import com.kalashnyk.denys.qrscanner.presentation.base.BaseFragment;
import com.kalashnyk.denys.qrscanner.presentation.item.IProductItemClickListener;
import com.kalashnyk.denys.qrscanner.repository.database.entity.ProductEntity;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AllProductsFragment extends BaseFragment {

    private static int REQUEST_CODE_SCAN = 102;

    @Inject
    AllProductsListViewModel mViewModel;

    @BindView(R.id.rv_products)
    protected RecyclerView mRvProduct;

    private BottomSheetDialog dialog;
    private ProductEntity mScannedProduct;
    private ProductAdapter mAdapter;
    private IProductItemClickListener<ProductEntity> mItemClickListener = new IProductItemClickListener<ProductEntity>() {
        @Override
        public void scanItem(ProductEntity productEntity, int position) {
            // if need do some with object of item, we can use param productEntity
            openDialog(productEntity, position);
        }

        @Override
        public void openDetail(ProductEntity productEntity) {
            openItemDetail(productEntity.getId());
        }
    };

    public AllProductsFragment() {

    }

    public static AllProductsFragment newInstance() {
        return new AllProductsFragment();
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
        View v = inflater.inflate(R.layout.fragment_all_product, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel.getAllItems(getActivity());
        mViewModel.getLiveDataItems().observe(this, this::initRecyclerView);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SCAN && resultCode == Activity.RESULT_OK) {
            handleUpdateProduct(data.getStringExtra(getString(R.string.EXTRAS_QRCODE)));
        }
    }

    private void handleUpdateProduct(String stringExtra) {

        mScannedProduct.setCode(stringExtra.substring(0, stringExtra.length()-1));
        mScannedProduct.setScanned(1);
        mViewModel.updateProduct(mScannedProduct);
        if(mAdapter != null) mAdapter.notifyDataSetChanged();
        mScannedProduct = null;
    }

    private void initRecyclerView(List<ProductEntity> list) {
        GridLayoutManager layoutManager = new GridLayoutManager(this.getContext(), 2);
        mAdapter = new ProductAdapter(getContext(), list);
        mAdapter.setItemClickListener(mItemClickListener);
        mRvProduct.setLayoutManager(layoutManager);
        mRvProduct.setAdapter(mAdapter);
    }

    private void openItemDetail(long id) {
        getContext().startActivity(DetailActivity.newInstance(getActivity(), (int) id));
    }

    private void openDialog(ProductEntity productEntity, int position) {
        View view = getLayoutInflater().inflate(R.layout.dialog_bottom, null);
        dialog = new BottomSheetDialog(Objects.requireNonNull(getActivity()));
        dialog.setContentView(view);

        TextView cancel = view.findViewById(R.id.v_dialog_bottom_cancel);
        TextView scan = view.findViewById(R.id.v_dialog_bottom_scan);
        cancel.setOnClickListener(v -> dialog.dismiss());
        scan.setOnClickListener(v -> {
            mScannedProduct = productEntity;
            startActivityForResult(ScanActivity.newInstance(getContext(), position), REQUEST_CODE_SCAN);
            dialog.dismiss();
        });


        dialog.show();
    }

}
