package kabuda.kabuda.com.kabuda.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.functions.Consumer;
import kabuda.kabuda.com.kabuda.ApiService;
import kabuda.kabuda.com.kabuda.R;
import kabuda.kabuda.com.kabuda.activity.FeedBackActivity;
import kabuda.kabuda.com.kabuda.activity.LoginActivity;
import kabuda.kabuda.com.kabuda.bean.UserBean;
import kabuda.kabuda.com.kabuda.config.AppConfig;
import kabuda.kabuda.com.kabuda.net.RetrofitFactory;
import kabuda.kabuda.com.kabuda.net.RxUtils;
import kabuda.kabuda.com.kabuda.utils.GlideUtil;
import kabuda.kabuda.com.kabuda.utils.SPUtil;

public class MineFragment extends Fragment {


    @BindView(R.id.userAva)
    ImageView userAva;
    @BindView(R.id.userName)
    TextView userName;
    @BindView(R.id.myOrder)
    LinearLayout myOrder;
    @BindView(R.id.feedBack)
    LinearLayout feedBack;
    @BindView(R.id.loginOut)
    LinearLayout loginOut;
    Unbinder unbinder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        unbinder = ButterKnife.bind(this, view);
        iniData();
        initView();
        return view;
    }

    private void initView() {
        feedBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SPUtil.getBooleanDefultFalse(AppConfig.USER_IS_LOGIN)) {
                    startActivity(new Intent(getContext(), FeedBackActivity.class));
                } else {
                    Toast.makeText(getContext(), "尚未登录", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getContext(), LoginActivity.class));
                    ( getActivity()).finish();
                }
            }
        });
        myOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SPUtil.getBooleanDefultFalse(AppConfig.USER_IS_LOGIN)) {
                    Toast.makeText(getContext(), "你当前暂未接单", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "尚未登录", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getContext(), LoginActivity.class));
                    ( getActivity()).finish();
                }
            }
        });
        loginOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SPUtil.saveboolean(AppConfig.USER_IS_LOGIN, false);
                startActivity(new Intent(getContext(), LoginActivity.class));
                getActivity().finish();
            }
        });
    }

    private void iniData() {
        RxUtils.wrapRestCall(RetrofitFactory.INSTANCE.getRetrofit(ApiService.class).getMsg()).subscribe(new Consumer<UserBean>() {
            @Override
            public void accept(UserBean userBean) throws Exception {
                userName.setText(userBean.getUser_nickname());
                GlideUtil.load(getContext(), "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=525793114,2060865463&fm=27&gp=0.jpg", userAva);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
