package kabuda.kabuda.com.kabuda.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.zhouwei.mzbanner.MZBannerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;
import kabuda.kabuda.com.kabuda.ApiService;
import kabuda.kabuda.com.kabuda.R;
import kabuda.kabuda.com.kabuda.adapter.MainPagerAdapter;
import kabuda.kabuda.com.kabuda.bean.ShowBean;
import kabuda.kabuda.com.kabuda.config.AppConfig;
import kabuda.kabuda.com.kabuda.fragment.MineFragment;
import kabuda.kabuda.com.kabuda.fragment.PartTimeFragment;
import kabuda.kabuda.com.kabuda.net.RetrofitFactory;
import kabuda.kabuda.com.kabuda.net.RxUtils;

/**
 * Created by YuanGang on 2018/6/1.
 */

public class MainActivity extends AppCompatActivity {
    public static List<ShowBean.BannerBean> bannerData;

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    private ArrayList<String> titles = new ArrayList<>();
    private ArrayList<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getShow();
    }

    private void getShow() {
        RxUtils.wrapRestCall(RetrofitFactory.INSTANCE.getRetrofit(ApiService.class).getShow(AppConfig.appId))
                .subscribe(new Consumer<ShowBean>() {

                    @Override
                    public void accept(ShowBean showBean) throws Exception {
                        if (showBean.getBanner() != null && showBean.getBanner().size() > 0) {
                            bannerData = showBean.getBanner();
                        }
                        if (showBean.getModule() != null && showBean.getModule().size() > 0) {
                            initTab(showBean.getModule());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    private void initTab(List<ShowBean.ModuleBean> module) {
        for (ShowBean.ModuleBean moduleBean : module) {
            titles.add(moduleBean.getModule_name());
            if (moduleBean.getId() == 1) {
                fragments.add(new MineFragment());
            } else {
                PartTimeFragment partTimeFragment = new PartTimeFragment();
                Bundle arguments = new Bundle();
                arguments.putString("url", moduleBean.getModule_url());
                partTimeFragment.setArguments(arguments);
                fragments.add(partTimeFragment);
            }
        }
        viewPager.setAdapter(new MainPagerAdapter(getSupportFragmentManager(), fragments, titles));
        tabLayout.setupWithViewPager(viewPager);
    }

}
