package kabuda.kabuda.com.kabuda.fragment;

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
import kabuda.kabuda.com.kabuda.bean.UserBean;
import kabuda.kabuda.com.kabuda.net.RetrofitFactory;
import kabuda.kabuda.com.kabuda.net.RxUtils;

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
                startActivity(new Intent(getContext(), FeedBackActivity.class));
            }
        });
        myOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "你当前暂未接单", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void iniData() {
        RxUtils.wrapRestCall(RetrofitFactory.INSTANCE.getRetrofit(ApiService.class).getMsg(
        )).subscribe(new Consumer<UserBean>() {
            @Override
            public void accept(UserBean userBean) throws Exception {
                userName.setText(userBean.getUser_nickname());
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
