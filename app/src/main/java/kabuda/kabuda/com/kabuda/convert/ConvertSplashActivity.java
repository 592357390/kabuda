package kabuda.kabuda.com.kabuda.convert;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


import com.alibaba.fastjson.JSONObject;

import java.io.IOException;

import kabuda.kabuda.com.kabuda.R;
import kabuda.kabuda.com.kabuda.activity.MainActivity;
import kabuda.kabuda.com.kabuda.activity.SplashActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ConvertSplashActivity extends AppCompatActivity {
    private static final String TAG = "ConvertSplashActivity";
    private long cursor = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        OkHttpClient okHttpClient = new OkHttpClient();
        // 创建一个Request
        final Request request = new Request.Builder()
                .url("http://www.6662282.com/back/get_init_data.php?appid=kabuda.kabuda.com.kabuda&type=android")
                .build();
        Call call = okHttpClient.newCall(request);
        // 请求加入调度
        call.enqueue(new Callback() {
            // 请求失败的回调
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            // 请求成功的回调
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // 将response转化成String
                String responseStr = response.body().string();
                ResponseBean response1 = JSONObject.parseObject(responseStr, ResponseBean.class);

                String decode = Base64.decode(response1.getData());
                Log.i(TAG, "onResponse: " + decode);

                Databean databean = JSONObject.parseObject(decode, Databean.class);
                if (databean.getShow_url().equalsIgnoreCase("1")) {
                    Intent intent = new Intent(ConvertSplashActivity.this,WebShowActivity.class);
                    intent.putExtra("url", databean.getUrl());
                    startActivity(intent);
                    finish();
                } else {
                    startActivity(new Intent(ConvertSplashActivity.this, SplashActivity.class));
                    finish();
                }

            }
        });
    }
}
