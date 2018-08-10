package mvp.ancely.com.myapplication.view;

/*
 *  @项目名：  BaseMvp 
 *  @包名：    mvp.ancely.com.myapplication.view
 *  @文件名:   ComsumerInfoView
 *  @创建者:   fanlelong
 *  @创建时间:  2018/8/10 下午2:07
 *  @描述：    TODO
 */

import com.ancely.rxjava.mvp.IBaseView;

import mvp.ancely.com.myapplication.bean.RecommentInfoBean;

public interface ComsumerInfoView extends IBaseView {
    void requesetSuccess(RecommentInfoBean bean);
}
