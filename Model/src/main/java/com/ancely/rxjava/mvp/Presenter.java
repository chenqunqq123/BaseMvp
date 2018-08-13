package com.ancely.rxjava.mvp;

/*
 *  @项目名：  NewCalendar 
 *  @包名：    calendar.ancyel.com.newcalendar.retrofit.base
 *  @创建者:   fanlelong
 *  @创建时间:  2018/8/7 下午2:42
 *  @描述：    基类中介层
 */

import android.util.Log;

import com.ancely.rxjava.NetWorkManager;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class Presenter<T, R> implements IBasepresenter<T> {
    private IBaseModel baseModel;
    private IBaseView mvpView;
    private static final int IS_NOT_NEED_SHOW_DIALOG = -1;//判断是否要显示和隐藏加载进度
    private CompositeDisposable mDisposable;
    private R mRequest;

    public Presenter(IBaseView mvpView, Class<R> clazz) {
        this.mvpView = mvpView;
        this.baseModel = new BaseModel(this);
        mRequest = NetWorkManager.getRetrofit().create(clazz);
    }


    @Override
    public void disposable(Disposable s) {
        if (this.mDisposable == null) {
            this.mDisposable = new CompositeDisposable();
        }
        this.mDisposable.add(s);
    }


    @Override
    public void unDisposable() {
        if (this.mDisposable != null && mDisposable.isDisposed()) {
            this.mDisposable.dispose();
            this.mDisposable = null;
        }
    }

    @Override
    public IBaseModel getModel() {
        return baseModel;
    }

    @Override
    public void accessServer(Map<String, String> map, String url, int flag) {


        Observable<T> netObservable = getObservable(mRequest, map, url);


        if (flag > IS_NOT_NEED_SHOW_DIALOG) {
            showProgress();
        }
        getModel().sendRequestToServer(mRequest, map, url, netObservable, flag);

    }

    protected abstract Observable<T> getObservable(R request, Map<String, String> map, String url);

    @Override
    public void accessSucceed(T body, int flag) {
        if (flag > IS_NOT_NEED_SHOW_DIALOG) {
            hideProgress();
        }
        serverResponse(body, flag);
    }

    @Override
    public void accessError(int code, String displayMessage, int flag) {
        if (flag > IS_NOT_NEED_SHOW_DIALOG) {
            hideProgress();
        }
        mvpView.showNetworkError(code, displayMessage, flag);
    }

    protected abstract void serverResponse(T body, int flag);

    public abstract void showProgress();

    public abstract void hideProgress();

    public void handlerFirstObservable(ObservableEmitter<T> emitter, R request) {
        Log.e("Presenter", "开始处理数据_P: " + Thread.currentThread().getName());
        emitter.onComplete();
    }

    public void hanlerDataRequestSuccess(T t) {
        Log.e("Presenter", "返回结果给主线程: " + Thread.currentThread().getName());
    }
}
