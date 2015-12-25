package heshida.haihong.com.heshida.LostAndFound;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import heshida.haihong.com.heshida.R;
import heshida.haihong.com.heshida.TopBar;

public class FoundActivity extends Activity {
    TopBar mTopBar;
    EditText mFoundDetail,mFoundTime,mFoundLocation,mFoundLine;
    Button mFoundBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_found);
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        mFoundBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFoundDetail.getText().toString().isEmpty() ||
                        mFoundTime.getText().toString().isEmpty() ||
                        mFoundLocation.getText().toString().isEmpty()||
                        mFoundLine.getText().toString().isEmpty())
                {
                    Toast.makeText(FoundActivity.this,"请填写完整哦~",Toast.LENGTH_LONG).show();
                }

                else
                {
                    String detail,time,location,line;
                    detail = mFoundDetail.getText().toString();
                    time   = mFoundTime.getText().toString();
                    location = mFoundLocation.getText().toString();
                    line     = mFoundLine.getText().toString();

                    Toast.makeText(FoundActivity.this,"感谢您的热心帮助~",Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });
    }

    private void initData() {
        mTopBar.setTitle("发布失物");
    }

    private void initView() {
        mTopBar = (TopBar) findViewById(R.id.topbarFound);
        mTopBar.getLeftButton().setEnabled(true);
        mTopBar.setLeftBackground(R.drawable.backbutton);
        mTopBar.setOnTopbarLeftClickListener(new TopBar.TopbarLeftClickListener() {
            @Override
            public void leftClick() {
                finish();
                overridePendingTransition(android.R.anim.slide_out_right, android.R.anim.slide_in_left);
            }
        });

        mFoundDetail = (EditText) findViewById(R.id.found_detail);
        mFoundTime = (EditText) findViewById(R.id.found_time);
        mFoundLocation = (EditText) findViewById(R.id.found_location);
        mFoundLine = (EditText) findViewById(R.id.found_line);
        mFoundBtn  = (Button) findViewById(R.id.found_BtnOK);

    }


}
