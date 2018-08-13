package com.ancely.rxjava.mvp;

/*
 *  @项目名：  NewCalendar 
 *  @包名：    calendar.ancyel.com.newcalendar.retrofit.mvp
 *  @文件名:   IBasepresenter
 *  @创建者:   fanlelong
 *  @创建时间:  2018/8/7 下午2:43
 *  @描述：
 */

import java.util.Map;

import io.reactivex.disposables.Disposable;

public interface IBasepresenter<T> {

    IBaseModel getModel();

    void accessServer(Map<String, String> map, String url, int flag);

    void accessSucceed(T body, int flag);

    void accessError(int code, String displayMessage, int flag);

    void unDisposable();

    void disposable(Disposable s);
}
