package com.kalashnyk.denys.qrscanner.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

public class PermissionManager {
    private static final String[] PERMISSIONS_CAMERA = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private static final int REQUEST_CODE_CAMERA = 101;

    public PermissionManager() {
    }

    public boolean userHasPermissionCamera(Context context) {
        int permissionCheck = ContextCompat.checkSelfPermission(context, PERMISSIONS_CAMERA[0]);
        int permissionCheck2 = ContextCompat.checkSelfPermission(context, PERMISSIONS_CAMERA[1]);
        return permissionCheck == PackageManager.PERMISSION_GRANTED &&
                permissionCheck2 == PackageManager.PERMISSION_GRANTED;
    }

    public void requestPermissionCamera(Activity activity) {
        ActivityCompat.requestPermissions(activity, PERMISSIONS_CAMERA, REQUEST_CODE_CAMERA);
    }
}
