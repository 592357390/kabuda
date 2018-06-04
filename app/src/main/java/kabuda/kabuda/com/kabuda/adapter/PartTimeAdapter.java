package kabuda.kabuda.com.kabuda.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import kabuda.kabuda.com.kabuda.R;
import kabuda.kabuda.com.kabuda.activity.DetailActivity;
import kabuda.kabuda.com.kabuda.activity.LoginActivity;
import kabuda.kabuda.com.kabuda.bean.JobBean;
import kabuda.kabuda.com.kabuda.config.AppConfig;
import kabuda.kabuda.com.kabuda.utils.SPUtil;

/**
 * Created by YuanGang on 2018/6/1.
 */

public class PartTimeAdapter extends BaseQuickAdapter<JobBean, BaseViewHolder> {

    public PartTimeAdapter(@Nullable List<JobBean> data) {
        super(R.layout.item_job, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final JobBean item) {
        helper.setText(R.id.jobName, "工作名称:  " + item.getJob_name());
        helper.setText(R.id.jobLocation, "工作地址:  " + item.getJob_address());
        if (TextUtils.isEmpty(item.getJob_contact())) {
            helper.setText(R.id.job_contack, "联系人姓名:  " + " 具体方式点击详情查看");
        } else {
            helper.setText(R.id.job_contack, "联系人姓名:  " + item.getJob_contact());
        }
        helper.getView(R.id.commit_order).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SPUtil.getBooleanDefultFalse(AppConfig.USER_IS_LOGIN)) {
                    Toast.makeText(mContext, "您注册时间少于两天暂不能接单", Toast.LENGTH_SHORT).show();
                } else {
                    mContext.startActivity(new Intent(mContext, LoginActivity.class));
                    ((Activity) mContext).finish();
                }
            }
        });
        helper.getView(R.id.detail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("data", item);
                mContext.startActivity(intent);
            }
        });
    }
}