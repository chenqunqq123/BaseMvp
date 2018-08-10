package mvp.ancely.com.myapplication.presenter;

/*
 *  @项目名：  BaseMvp 
 *  @包名：    mvp.ancely.com.myapplication.presenter
 *  @文件名:   ComsumerInfoPresenter
 *  @创建者:   fanlelong
 *  @创建时间:  2018/8/10 下午1:59
 *  @描述：
 */


import android.util.Log;

import com.ancely.rxjava.mvp.Presenter;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import mvp.ancely.com.myapplication.Api;
import mvp.ancely.com.myapplication.bean.RecommentInfoBean;
import mvp.ancely.com.myapplication.view.ComsumerInfoView;

public class ComsumerInfoPresenter extends Presenter<RecommentInfoBean, Api> {
    private ComsumerInfoView mComsumerInfoView;

    private ComsumerInfoPresenter(ComsumerInfoView infoView, Class<Api> clazz) {
        super(infoView, clazz);
        mComsumerInfoView = infoView;
    }

    public ComsumerInfoPresenter(ComsumerInfoView infoView) {
        this(infoView, Api.class);
    }

    @Override
    protected Observable<RecommentInfoBean> getObservable(Api request, Map<String, String> map, String url) {

        return request.getCommentInfo(map);
    }

    @Override
    protected void serverResponse(RecommentInfoBean body, int flag) {
        Log.e("Presenter", "从Model-层获取到了数据开始传送到-View层");
        mComsumerInfoView.requesetSuccess(body);

    }

    @Override
    public void showProgress() {
        Log.e("Presenter", "showProgress");
    }

    @Override
    public void hideProgress() {
        Log.e("Presenter", "hideProgress");
    }

    @Override
    public void hanlerDataRequestSuccess(RecommentInfoBean bean) {
        super.hanlerDataRequestSuccess(bean);
    }

    @Override
    public void handlerFirstObservable(ObservableEmitter<RecommentInfoBean> emitter, Api request) {
        super.handlerFirstObservable(emitter, request);
    }
}
