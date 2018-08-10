package com.ancely.rxjava.mvp;

/*
 *  @项目名：  NewCalendar 
 *  @包名：    calendar.ancyel.com.newcalendar.retrofit.mvp
 *  @文件名:   IBaseModel
 *  @创建者:   fanlelong
 *  @创建时间:  2018/8/7 下午2:43
 *  @描述：    TODO
 */


import java.util.Map;

import io.reactivex.Observable;


public interface IBaseModel {

    <T,R> void sendRequestToServer(R request, Map<String, String> map, String url, Observable<T> netObservable, int flag);

}
