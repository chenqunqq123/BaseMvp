package com.ancely.rxjava.permission;

/*
 *  @项目名：  ancely_market 
 *  @包名：    com.ancely.market.permission
 *  @文件名:   PermissionRequestActivity
 *  @创建者:   fanlelong
 *  @创建时间:  2018/7/31 下午4:18
 *  @描述：    权限管理Activity
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import com.ancely.rxjava.R;

import java.util.ArrayList;
import java.util.List;

public class PermissionRequestActivity extends AppCompatActivity {


    private static IPermission permissionListener;
    private String[] permissions;
    private String[] permissionsName;
    private static final String PERMISSION_KEY = "permission_key";
    private static final String PERMISSION_NAME = "permission_name";
    private static final String REQUEST_CODE = "request_code";
    private int requestCode;


    /**
     * 跳转到Activity申请权限
     *
     * @param context     Context
     * @param permissions Permission List
     * @param iPermission Interface
     */
    public static void PermissionRequest(Context context, String[] permissions, int requestCode, String[] permissionsName, IPermission iPermission) {
        permissionListener = iPermission;
        Intent intent = new Intent(context, PermissionRequestActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Bundle bundle = new Bundle();
        bundle.putStringArray(PERMISSION_KEY, permissions);
        bundle.putStringArray(PERMISSION_NAME, permissionsName);
        bundle.putInt(REQUEST_CODE, requestCode);
        intent.putExtras(bundle);
        context.startActivity(intent);
        if (context instanceof Activity) {
            ((Activity) context).overridePendingTransition(0, 0);
        }

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_permission);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            permissions = bundle.getStringArray(PERMISSION_KEY);
            permissionsName = bundle.getStringArray(PERMISSION_NAME);
            requestCode = bundle.getInt(REQUEST_CODE, 0);
        }
        if (permissions == null || permissions.length <= 0) {
            finish();
            return;
        }
        requestPermission(permissions,permissionsName);
    }


    /**
     * 申请权限
     *
     * @param permissions permission list
     */
    private void requestPermission(String[] permissions, String[] permissionsName) {

        if (PermissionUtil.hasSelfPermissions(this, permissions)) {
            //all permissions granted
            if (permissionListener != null) {
                permissionListener.PermissionGranted();
                permissionListener = null;
            }
            finish();
            overridePendingTransition(0, 0);
        } else {
            //request permissions
            ActivityCompat.requestPermissions(this, permissions, requestCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (PermissionUtil.verifyPermissions(grantResults)) {
            //所有权限都同意
            if (permissionListener != null) {
                permissionListener.PermissionGranted();
            }
        } else {
            if (!PermissionUtil.shouldShowRequestPermissionRationale(this, permissions)) {
                //权限被拒绝并且选中不再提示
                if (permissions.length != grantResults.length) return;
                List<String> denyList = new ArrayList<>();
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults[i] == -1) {
                        denyList.add(permissions[i]);
                    }
                }
                if (permissionListener != null) {
                    permissionListener.PermissionDenied(requestCode, denyList);
                }
            } else {
                //权限被取消
                if (permissionListener != null) {
                    permissionListener.PermissionCanceled(requestCode);
                }
            }

        }
        permissionListener = null;
        finish();
        overridePendingTransition(0, 0);
    }
}
