package kabuda.kabuda.com.kabuda.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wwzz.androidbase.net.RetrofitFactory;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.functions.Consumer;
import kabuda.kabuda.com.kabuda.ApiService;
import kabuda.kabuda.com.kabuda.R;
import kabuda.kabuda.com.kabuda.adapter.PartTimeAdapter;
import kabuda.kabuda.com.kabuda.bean.JobBean;
import kabuda.kabuda.com.kabuda.bean.TypeBean;
import kabuda.kabuda.com.kabuda.net.RxUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class PartTimeFragment extends Fragment {


    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;
    Unbinder unbinder;
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
    }


    public void getData() {
        RxUtils.wrapRestCall(RetrofitFactory.INSTANCE.getRetrofit(ApiService.class).getContent(url))
                .subscribe(new Consumer<TypeBean>() {
                    @Override
                    public void accept(TypeBean jobBeans) throws Exception {
                        adapter.setNewData(jobBeans.getJob());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
