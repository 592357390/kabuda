package kabuda.kabuda.com.kabuda;

import io.reactivex.Observable;
import kabuda.kabuda.com.kabuda.bean.ShowBean;
import kabuda.kabuda.com.kabuda.bean.TypeBean;
import kabuda.kabuda.com.kabuda.bean.UserBean;
import kabuda.kabuda.com.kabuda.net.ResponseBean;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by YuanGang on 2018/6/1.
 */

public interface ApiService {

    @POST("/core/login")
    Observable<ResponseBean<UserBean>> login(@Query("appId") int appId, @Query("userAccount") String userAccount, @Query("userPassword") String userPassword);

    @POST("/core/register")
    Observable<ResponseBean<UserBean>> register(@Query("appId") int appId, @Query("userNickname") String userNickname, @Query("userAccount") String userAccount, @Query("userPassword") String userPassword, @Query("userPhone") String userPhone);

    @POST("/module/getPer")
    Observable<ResponseBean<UserBean>> getMsg();

    @POST("/app/get")
    Observable<ResponseBean<ShowBean>> getShow(@Query("appId") int appId);

    @POST("{path}")
    Observable<ResponseBean<TypeBean>> getContent(@Path(value = "path", encoded = true) String path);
}
