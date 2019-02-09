package com.kalashnyk.denys.qrscanner.presentation.base;

import android.app.ActionBar;
import android.app.Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toolbar;

import com.kalashnyk.denys.qrscanner.App;
import com.kalashnyk.denys.qrscanner.di.component.ViewModelComponent;

import java.lang.ref.WeakReference;
import butterknife.Unbinder;
import io.reactivex.annotations.Nullable;

public abstract class BaseFragment extends Fragment {
    protected WeakReference<View> rootView;
    protected Unbinder mUnbinder;
    protected Toolbar toolbar;
    private Location mLocation = null;
    private BroadcastReceiver mNetworkStateBroadcastReceiver;


    protected void onBackPressed() {
        if (getActivity() != null) getActivity().onBackPressed(); //вызываем обработчик из активности
    }

    public View getRootView() {
        if (rootView == null) return null;
        return rootView.get();
    }

    public void setRootView(View rootView) {
        this.rootView = new WeakReference<>(rootView);
    }

    @Override
    public Context getContext() {
        return getActivity();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createDaggerDependencies();
        hideSoftKeyboard(getActivity());
    }

    public static void hideSoftKeyboard(Activity activity) {
        if (activity.getCurrentFocus() == null) return;
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0); //хайдит если менеджер ввода не нулл
    }

    private void createDaggerDependencies() {
        injectDependency(((App) getActivity().getApplication()).getViewModelComponent());
    }

    protected abstract void injectDependency(ViewModelComponent presentersComponent);

    @Override
    public void onDestroy() {
        if (rootView != null) rootView.clear();
        super.onDestroy();

    }

    protected boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) return false;
        NetworkInfo WiFiInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        return WiFiInfo.isConnected() || mobileInfo.isConnected();
    }

    protected void addListenerStateNetWork(ConnectivityManager.OnNetworkActiveListener listener) {
        IntentFilter filter = new IntentFilter(); //интент
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        filter.addAction("android.net.wifi.WIFI_STATE_CHANGED");

        mNetworkStateBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                listener.onNetworkActive();
            }
        };
        getActivity().registerReceiver(mNetworkStateBroadcastReceiver, filter);
    }

    public Location getLocation() {
        return mLocation;
    }
}
