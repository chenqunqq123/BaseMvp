package com.ancely.rxjava.mvp;

/*
 *  @项目名：  NewCalendar 
 *  @包名：    calendar.ancyel.com.newcalendar.retrofit.mvp
 *  @文件名:   BaseModel
 *  @创建者:   fanlelong
 *  @创建时间:  2018/8/7 下午2:44
 */

import android.util.Log;

import com.ancely.rxjava.exception.ApiException;
import com.ancely.rxjava.ResultTransformer;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;


public class BaseModel implements IBaseModel {
    private static String TAG = Presenter.class.getSimpleName();


    private Presenter basePresenter;


    BaseModel(Presenter basePresenter) {
        this.basePresenter = basePresenter;
    }


    @Override
    public <T, R> void sendRequestToServer(R request, Map<String, String> map, String url, Observable<T> netObservable, int flag) {
        Observable<T> cacheObservable = Observable.create(emitter -> {
            Log.e(TAG, "create当前线程:" + Thread.currentThread().getName());

            basePresenter.handlerFirstObservable(emitter, request);

        });

        Disposable subscribe = Observable.concat(cacheObservable, netObservable)
                .compose(ResultTransformer.handleResult())
                .compose(SchedulerProvider.getInstance().applySchedulers())
                .subscribe(t -> {
                    Log.e(TAG, "subscribe 成功:" + Thread.currentThread().getName());

                    basePresenter.accessSucceed(t, flag);
                    basePresenter.hanlerDataRequestSuccess(t);

                }, throwable -> {
                    Log.e(TAG, "accept : 网络异常: \n");
                    if (throwable instanceof ApiException) {
                        basePresenter.accessError(((ApiException) throwable).getCode(), ((ApiException) throwable).getDisplayMessage(), flag);
                    }
                });
        basePresenter.addDisposable(subscribe);
    }
}
