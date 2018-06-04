package kabuda.kabuda.com.kabuda;

import android.app.Application;

import cn.jpush.android.api.JPushInterface;
import kabuda.kabuda.com.kabuda.net.AndroidBase;

/**
 * Created by YuanGang on 2018/6/1.
 */

public class App extends Application {

    public static App app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        AndroidBase.init(this, "http://47.98.180.31:7012", "http://47.98.180.31:7012");
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }
}
