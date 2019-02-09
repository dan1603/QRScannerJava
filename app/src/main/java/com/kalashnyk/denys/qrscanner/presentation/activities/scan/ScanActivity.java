package com.kalashnyk.denys.qrscanner.presentation.activities.scan;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.zxing.Result;
import com.kalashnyk.denys.qrscanner.R;
import com.kalashnyk.denys.qrscanner.di.component.ViewModelComponent;
import com.kalashnyk.denys.qrscanner.presentation.activities.main.MainActivity;
import com.kalashnyk.denys.qrscanner.presentation.base.BaseActivity;
import com.kalashnyk.denys.qrscanner.utils.PermissionManager;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanActivity extends BaseActivity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView mScannerView;
    private int mPosition;
    private PermissionManager mPermissionManager;

    public static Intent newInstance(Context context, int position) {
        Intent intent = new Intent(context, ScanActivity.class);
        intent.putExtra(context.getString(R.string.EXTRAS_POSITION), position);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().getIntExtra(getString(R.string.EXTRAS_POSITION), 0) != 0) mPosition = getIntent().getIntExtra(getString(R.string.EXTRAS_POSITION), 0);
        mPermissionManager = new PermissionManager();
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mPermissionManager.userHasPermissionCamera(this)) {
            mScannerView.setResultHandler(this);
            mScannerView.startCamera();
        } else {
            mPermissionManager.requestPermissionCamera(this);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    protected void injectDependency(ViewModelComponent component) {
        //method override for all children of BaseActivity for dependence injection
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void handleResult(Result result) {
        Intent intent = new Intent();
        intent.putExtra(getString(R.string.EXTRAS_POSITION), mPosition);
        intent.putExtra(getString(R.string.EXTRAS_QRCODE), result.getText());
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}
