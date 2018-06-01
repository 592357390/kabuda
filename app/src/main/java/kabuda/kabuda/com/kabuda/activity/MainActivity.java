package kabuda.kabuda.com.kabuda.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.wwzz.androidbase.net.RetrofitFactory;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

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
import kabuda.kabuda.com.kabuda.net.RxUtils;
import kabuda.kabuda.com.kabuda.utils.GlideUtil;

/**
 * Created by YuanGang on 2018/6/1.
 */

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.banner)
    MZBannerView bannerMz;
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
                            initBanner(showBean.getBanner());
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

    private void initBanner(List<ShowBean.BannerBean> banner) {
        bannerMz.setDelayedTime(5000);
        bannerMz.setPages(banner, new MZHolderCreator<BannerViewHolder>() {
            @Override
            public BannerViewHolder createViewHolder() {
                return new BannerViewHolder();
            }
        });
        bannerMz.start();
    }

    static class BannerViewHolder implements MZViewHolder<ShowBean.BannerBean> {
        private ImageView mImageView;

        @Override
        public View createView(Context context) {
            // 返回页面布局
            View view = LayoutInflater.from(context).inflate(R.layout.banner_item, null);
            mImageView = view.findViewById(R.id.banner_image);
            return view;
        }

        @Override
        public void onBind(final Context context, int position, final ShowBean.BannerBean data) {
            // 数据绑定
            GlideUtil.loadWithPlaceHolder(context, data.getBanner_url(), mImageView);
        }
    }
}
