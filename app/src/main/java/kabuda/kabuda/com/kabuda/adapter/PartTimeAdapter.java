package kabuda.kabuda.com.kabuda.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import kabuda.kabuda.com.kabuda.R;
import kabuda.kabuda.com.kabuda.bean.JobBean;

/**
 * Created by YuanGang on 2018/6/1.
 */

public class PartTimeAdapter extends BaseQuickAdapter<JobBean, BaseViewHolder> {

    public PartTimeAdapter(@Nullable List<JobBean> data) {
        super(R.layout.item_job, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, JobBean item) {
        helper.setText(R.id.jobName, item.getJob_address());
    }
}