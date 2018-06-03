package kabuda.kabuda.com.kabuda.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;
import kabuda.kabuda.com.kabuda.ApiService;
import kabuda.kabuda.com.kabuda.R;
import kabuda.kabuda.com.kabuda.bean.UserBean;
import kabuda.kabuda.com.kabuda.config.AppConfig;
import kabuda.kabuda.com.kabuda.net.RetrofitFactory;
import kabuda.kabuda.com.kabuda.net.RxUtils;
import kabuda.kabuda.com.kabuda.utils.SPUtil;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.account)
    EditText account;
    @BindView(R.id.passWord)
    EditText passWord;
    @BindView(R.id.forget)
    TextView forget;
    @BindView(R.id.register)
    TextView register;
    @BindView(R.id.lookLook)
    TextView lookLook;
    @BindView(R.id.login)
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(passWord.getText()) || TextUtils.isEmpty(account.getText())) {
                    Toast.makeText(LoginActivity.this, "账号密码为空，请重新输入", Toast.LENGTH_SHORT).show();
                } else {
                    login();
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RigisterActivity.class));
            }
        });

        lookLook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    private void login() {
        RxUtils.wrapRestCall(RetrofitFactory.INSTANCE.getRetrofit(ApiService.class).login(AppConfig.appId, account.getText()
                .toString(), passWord.getText().toString()
        )).subscribe(new Consumer<UserBean>() {
            @Override
            public void accept(UserBean userBean) throws Exception {
                SPUtil.saveboolean(AppConfig.USER_IS_LOGIN, true);
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Toast.makeText(LoginActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
