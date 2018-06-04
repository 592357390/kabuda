package kabuda.kabuda.com.kabuda.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
        JobBean jobBean = (JobBean) getIntent().getSerializableExtra("data");
        jobName.setText(jobBean.getJob_name());
        jobLocation.setText(jobBean.getJob_address());
        jobDescr.setText(jobBean.getJob_descr());
        jobRequirement.setText(jobBean.getJob_requirement());
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DetailActivity.this, "新用户注册满一天后即可接单", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
