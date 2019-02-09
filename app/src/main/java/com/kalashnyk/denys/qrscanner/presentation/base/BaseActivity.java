package com.kalashnyk.denys.qrscanner.presentation.base;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toolbar;

import com.kalashnyk.denys.qrscanner.App;
import com.kalashnyk.denys.qrscanner.R;
import com.kalashnyk.denys.qrscanner.di.component.ViewModelComponent;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import butterknife.Unbinder;
import io.reactivex.functions.Action;

public abstract class BaseActivity extends AppCompatActivity {
    protected Unbinder unbinder;
    protected Toolbar toolbar;
    private Location mLocation = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createDaggerDependencies();
        hideSoftKeyboard();
    }

    public void hideSoftKeyboard() {
        if (getCurrentFocus() == null)
            return;
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }

    private void createDaggerDependencies() {
        injectDependency(((App) getApplication()).getViewModelComponent());
    }

    protected abstract void injectDependency(ViewModelComponent component);

    @Override
    protected void onDestroy() {
        if (unbinder != null) unbinder.unbind();
        super.onDestroy();

    }

    private void onClosePressed(Action onPositiveClick) {
        if (onPositiveClick != null) {
            try {
                onPositiveClick.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getFragmentManager();
        if (onBackPressed(fm)) return;
        hideSoftKeyboard();
        super.onBackPressed();
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_down);
    }

    private boolean onBackPressed(FragmentManager fm) {
        if (fm.getBackStackEntryCount() > 1) {
            fm.popBackStackImmediate();
            return true;
        }

        return false;
    }

    protected void disableTextInTitle() {
        if (getActionBar() != null) getActionBar().setDisplayShowTitleEnabled(false);
    }

    protected int getColorCustom(@ColorRes int resId) {
        return ContextCompat.getColor(this, resId);
    }

    public void replaceFragment(Fragment fragment) {
        replaceFragment(fragment, true, true);
    }

    public void replaceFragment(Fragment fragment, boolean addToBackStack, boolean moveOnRight) {
        replaceFragment(fragment, addToBackStack, true, moveOnRight);
    }

    public void replaceFragment(Fragment fragment, boolean addToBackStack, boolean needAnimate, boolean moveOnRight) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        String fragmentName = fragment.getClass().getSimpleName();
        if (addToBackStack) ft = ft.addToBackStack(fragmentName);
        if (needAnimate) {
            int enterAnimation = moveOnRight ? R.animator.slide_in_left : R.animator.pop_out_right;
            int exitAnimation = moveOnRight ? R.animator.slide_out_right : R.animator.pop_in_left;
            int popEnterAnimation = moveOnRight ? R.animator.pop_out_right : R.animator.slide_in_left;
            int popExitAnimation = moveOnRight ? R.animator.pop_in_left : R.animator.slide_out_right;
            ft.setCustomAnimations(enterAnimation, exitAnimation, popEnterAnimation, popExitAnimation);
        }
        ft.replace(R.id.container, fragment, fragmentName).commit();
    }

    public Location getLocation() {
        return mLocation;
    }

    protected boolean saveBitmapToFile(final Bitmap bitmap, final Bitmap.CompressFormat format, final int quality, final File file) {
        if (file == null) {
            return false;
        }
        if (file.getParent() != null && !file.isDirectory()) {
            file.mkdirs();
            OutputStream outputStream;
            try {
                outputStream = new FileOutputStream(file);
                bitmap.compress(format, quality, outputStream);
                outputStream.close();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

}
