package kabuda.kabuda.com.kabuda.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.functions.Consumer;
import kabuda.kabuda.com.kabuda.ApiService;
import kabuda.kabuda.com.kabuda.R;
import kabuda.kabuda.com.kabuda.activity.MainActivity;
import kabuda.kabuda.com.kabuda.adapter.PartTimeAdapter;
import kabuda.kabuda.com.kabuda.bean.JobBean;
import kabuda.kabuda.com.kabuda.bean.ShowBean;
import kabuda.kabuda.com.kabuda.bean.TypeBean;
import kabuda.kabuda.com.kabuda.net.RetrofitFactory;
import kabuda.kabuda.com.kabuda.net.RxUtils;
import kabuda.kabuda.com.kabuda.utils.GlideUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class PartTimeFragment extends Fragment {


    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;
    Unbinder unbinder;
    @BindView(R.id.banner)
    MZBannerView bannerMz;
    private String url;
    private PartTimeAdapter adapter;

    public PartTimeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_part_time, container, false);
        unbinder = ButterKnife.bind(this, view);
        url = getArguments().getString("url");
        initView();
        initData();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void initData() {
        getData();
    }

    private void initView() {
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new PartTimeAdapter(new ArrayList<JobBean>());
        recycleView.setAdapter(adapter);

        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh.setRefreshing(true);
                getData();
            }
        });
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


    public void getData() {
        RxUtils.wrapRestCall(RetrofitFactory.INSTANCE.getRetrofit(ApiService.class).getContent(url))
                .subscribe(new Consumer<TypeBean>() {
                    @Override
                    public void accept(TypeBean jobBeans) throws Exception {
                        adapter.setNewData(jobBeans.getJob());
                        if (MainActivity.bannerData != null && MainActivity.bannerData.size() > 0) {
                            initBanner(MainActivity.bannerData);
                        }
                        refresh.setRefreshing(false);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
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
