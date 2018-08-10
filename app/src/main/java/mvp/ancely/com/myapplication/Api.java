package mvp.ancely.com.myapplication;

/*
 *  @项目名：  BaseMvp 
 *  @包名：    mvp.ancely.com.myapplication
 *  @文件名:   Api
 *  @创建者:   fanlelong
 *  @创建时间:  2018/8/10 下午1:56
 *  @描述：    TODO
 */


import java.util.Map;

import io.reactivex.Observable;
import mvp.ancely.com.myapplication.bean.RecommentInfoBean;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Api {

    @POST("operate/callProcedure")
    Observable<RecommentInfoBean> getCommentInfo(@Body Map<String,String> map);

}
