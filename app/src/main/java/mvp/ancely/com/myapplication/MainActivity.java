package mvp.ancely.com.myapplication;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ancely.rxjava.permission.SettingUtil;
import com.ancely.rxjava.permission.annotation.NeedPermission;
import com.ancely.rxjava.permission.annotation.PermissionCanceled;
import com.ancely.rxjava.permission.annotation.PermissionDenied;
import com.ancely.rxjava.permission.bean.CancelBean;
import com.ancely.rxjava.permission.bean.DenyBean;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import mvp.ancely.com.myapplication.bean.RecommentInfoBean;
import mvp.ancely.com.myapplication.presenter.ComsumerInfoPresenter;
import mvp.ancely.com.myapplication.view.ComsumerInfoView;

public class MainActivity extends AppCompatActivity implements ComsumerInfoView {

    private ComsumerInfoPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter = new ComsumerInfoPresenter(this);
    }

    public void startRequest(View view) {
        HashMap<String, String> map = new HashMap<>();
        map.put("p1", "15915478502");
        map.put("p2", "SZAREA_SZGS.P1524");
        map.put("p3", "");
        map.put("procedureName", "proc_app_user_estate_info");
        mPresenter.accessServer(map, "url", 1);
    }

    @Override
    public void showNetworkError(int code, String displayMessage, int flag) {
        Log.e("Presenter", "异常相关: " + code + "---" + displayMessage);
    }

    @Override
    public void requesetSuccess(RecommentInfoBean bean) {
        Log.e("Presenter", "View层收到数据开始展示界面");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscrible();
    }

    public void requestPermission(View view) {
        downLoad();
    }

    @NeedPermission(value = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, requestCode = 10, permission = {"SD卡"})
    private void downLoad() {
        Log.e("MainActivity", "请求权限成功");
    }

    /**
     * 权限被取消
     *
     * @param bean CancelBean
     */
    @PermissionCanceled
    public void dealCancelPermission(CancelBean bean) {
        Log.e("MainActivity", "请求权限被拒");
        Toast.makeText(this, "requestCode:" + bean.getRequestCode(), Toast.LENGTH_SHORT).show();
    }

    /**
     * 权限被拒绝
     *
     * @param bean DenyBean
     */
    @PermissionDenied
    public void dealPermission(DenyBean bean) {
        if (bean == null) return;
        Toast.makeText(this, "requestCode:" + bean.getRequestCode()
                + ",Permissions: " + Arrays.toString(bean.getDenyList().toArray()), Toast.LENGTH_SHORT).show();
        List<String> denyList = bean.getDenyList();
        switch (bean.getRequestCode()) {
            case 10:
                //多个权限申请返回结果
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < denyList.size(); i++) {
                    if (Manifest.permission.CALL_PHONE.equals(denyList.get(i))) {
                        builder.append("电话");
                    } else if (Manifest.permission.CAMERA.equals(denyList.get(i))) {
                        builder.append("相机");
                    } else if (Manifest.permission.READ_EXTERNAL_STORAGE.equals(denyList.get(i))) {
                        builder.append("写取SD卡");
                    } else if (Manifest.permission.WRITE_EXTERNAL_STORAGE.equals(denyList.get(i))) {
                        builder.append("读取SD卡");
                    }
                }
                builder.append("权限被禁止，需要手动打开");
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("提示")
                        .setMessage(builder)
                        .setPositiveButton("去设置", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                SettingUtil.go2Setting(MainActivity.this);
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create().show();

                break;
            case 0:
                //单个权限申请返回结果
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("提示")
                        .setMessage(bean.getDenyListName().get(0) + "权限被禁止，需要手动打开")
                        .setPositiveButton("去设置", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                SettingUtil.go2Setting(MainActivity.this);
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create().show();
                break;
            default:
                break;
        }
    }
}
