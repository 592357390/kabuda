package kabuda.kabuda.com.kabuda.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;
import kabuda.kabuda.com.kabuda.ApiService;
import kabuda.kabuda.com.kabuda.R;
import kabuda.kabuda.com.kabuda.bean.UserBean;
import kabuda.kabuda.com.kabuda.config.AppConfig;
import kabuda.kabuda.com.kabuda.net.RetrofitFactory;
import kabuda.kabuda.com.kabuda.net.RxUtils;

public class RigisterActivity extends AppCompatActivity {

    @BindView(R.id.account)
    EditText account;
    @BindView(R.id.userName)
    EditText userName;
    @BindView(R.id.userPhone)
    EditText userPhone;
    @BindView(R.id.passWord)
    EditText passWord;
    @BindView(R.id.commit)
    Button commit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rigister);
        ButterKnife.bind(this);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("注册");
        }

        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(account.getText())) {
                    Toast.makeText(RigisterActivity.this, "账号不能为空，请重新输入", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(userName.getText())) {
                    Toast.makeText(RigisterActivity.this, "账号不能为空，请重新输入", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!TextUtils.isEmpty(userPhone.getText())) {
                    if (!checkPhone(userPhone.getText().toString())) {
                        Toast.makeText(RigisterActivity.this, "手机号码有误，请重新输入", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                if (TextUtils.isEmpty(passWord.getText())) {
                    Toast.makeText(RigisterActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                requestRegister();
            }
        });
    }

    protected boolean checkPhone(String str) {
        String pattern = "0?(13|14|15|17|18)[0-9]{9}";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        return m.matches();
    }

    public void requestRegister() {

        RxUtils.wrapRestCall(RetrofitFactory.INSTANCE.getRetrofit(ApiService.class).register(
                AppConfig.appId, userName.getText().toString(), account.getText().toString(), passWord.getText().toString(), userPhone.getText().toString()
        )).subscribe(new Consumer<UserBean>() {
            @Override
            public void accept(UserBean userBean) throws Exception {
                finish();
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Toast.makeText(RigisterActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
