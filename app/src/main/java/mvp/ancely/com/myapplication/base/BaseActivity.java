package mvp.ancely.com.myapplication.base;

/*
 *  @项目名：  BaseMvp 
 *  @包名：    mvp.ancely.com.myapplication.base
 *  @文件名:   BaseActivity
 *  @创建者:   fanlelong
 *  @创建时间:  2018/8/10 下午4:29
 *  @描述：
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layoutId = getContentView();
        setContentView(layoutId);
        initView();
        initDatas();

    }

    protected void initDatas() {

    }

    protected void initView() {

    }

    @Override
    public void onClick(View v) {

    }

    protected abstract int getContentView();

    /**
     * 加载数据成功并有数据的时候可以调用
     *
     * @param parentView 父容器
     * @param res        xml
     * @auto ancely
     */
    protected void addSuccessView(ViewGroup parentView, int res) {
        parentView.removeAllViews();
        View view = View.inflate(this, res, null);
        parentView.addView(view);
    }

    /**
     * 加载数据失败的时候可以调用
     *
     * @param parentView 父容器
     * @auto ancely
     */
    protected void addNetWorkErrorView(ViewGroup parentView, int layoutId) {
        parentView.removeAllViews();
        View view = View.inflate(this, layoutId, null);
        parentView.addView(view);
    }
}
