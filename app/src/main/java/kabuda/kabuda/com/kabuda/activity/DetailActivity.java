package kabuda.kabuda.com.kabuda.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import kabuda.kabuda.com.kabuda.R;
import kabuda.kabuda.com.kabuda.bean.JobBean;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.jobName)
    TextView jobName;
    @BindView(R.id.jobLocation)
    TextView jobLocation;
    @BindView(R.id.job_time)
    TextView jobTime;
    @BindView(R.id.job_descr)
    TextView jobDescr;
    @BindView(R.id.job_requirement)
    TextView jobRequirement;
    @BindView(R.id.commit)
    Button commit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("订单详情");
        }

        JobBean jobBean = (JobBean) getIntent().getSerializableExtra("data");
        jobName.setText("工作名称："+jobBean.getJob_name());
        jobLocation.setText("工作地点："+jobBean.getJob_address());
        jobDescr.setText("工作描述："+jobBean.getJob_descr());
        jobRequirement.setText("工作要求："+jobBean.getJob_requirement());
        long time = new Date().getTime();
        time += Math.round(Math.random() * 24 * 3 * 60 * 60 * 1000 + 1000);
        jobTime.setText("工作时间："+new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(time));
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DetailActivity.this, "新用户注册满两天后即可接单", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
