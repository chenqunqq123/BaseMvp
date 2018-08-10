package com.ancely.rxjava.mvp;

/*
 *  @项目名：  NewCalendar 
 *  @包名：    calendar.ancyel.com.newcalendar.retrofit.mvp
 *  @文件名:   IBaseView
 *  @创建者:   fanlelong
 *  @创建时间:  2018/8/7 下午2:43
 *  @描述：
 */

public interface IBaseView {
    void showNetworkError(int code, String displayMessage, int flag);
}
