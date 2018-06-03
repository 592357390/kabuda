package kabuda.kabuda.com.kabuda.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import kabuda.kabuda.com.kabuda.R;

public class FeedBackActivity extends AppCompatActivity {

    @BindView(R.id.back_image)
    ImageView backImage;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.feedback_content)
    EditText feedbackContent;
    @BindView(R.id.feedback_phones_et)
    EditText feedbackPhonesEt;
    @BindView(R.id.feedback_confirm)
    Button feedbackConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        ButterKnife.bind(this);

        feedbackConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(feedbackContent.getText().toString())||TextUtils.isEmpty(feedbackPhonesEt.getText())){
                    Toast.makeText(FeedBackActivity.this, "信息填写不完整", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(FeedBackActivity.this, "反馈成功，我们客服将会在2天内联系您，耐心等待!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
