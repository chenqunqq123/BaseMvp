package mvp.ancely.com.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.HashMap;

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
}
