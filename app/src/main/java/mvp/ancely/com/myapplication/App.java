package mvp.ancely.com.myapplication;

/*
 *  @项目名：  BaseMvp 
 *  @包名：    mvp.ancely.com.myapplication
 *  @文件名:   App
 *  @创建者:   fanlelong
 *  @创建时间:  2018/8/10 下午1:57
 *  @描述：    TODO
 */

import android.app.Application;

import com.ancely.rxjava.NetWorkManager;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        NetWorkManager.getInstance().init("http://testsocial.vanke.com/");
    }
}
