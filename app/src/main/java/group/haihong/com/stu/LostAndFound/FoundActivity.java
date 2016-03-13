package group.haihong.com.stu.LostAndFound;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

import group.haihong.com.stu.Hot.HotManager;
import group.haihong.com.stu.Hot.HotResponse;
import group.haihong.com.stu.R;
import group.haihong.com.stu.TopBar;
import group.haihong.com.stu.Utils.net.Response;

public class FoundActivity extends Activity {
    TopBar mTopBar;
    EditText mFoundDetail,mFoundTime,mFoundLocation,mFoundLine;
    Button mFoundBtn;
    Boolean losted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_found);
        Intent intent = getIntent();
        losted = intent.getExtras().getBoolean("losted");
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
                    HashMap<String,String> params = new HashMap<String, String>();
                    params.put("title",detail);
                    params.put("foundtime",time);
                    params.put("location",location);
                    params.put("line", line);
                    params.put("losted",losted+"");
                    String message = "小伙伴们会全力帮您找的~";
                    if (losted)
                    {
                        message = "失主会非常赶紧您的~";
                    }
                    final ProgressDialog proDialog = android.app.ProgressDialog.show(FoundActivity.this, "", "上传中...");
                    HotResponse hotResponse = new HotResponse(){
                        @Override
                        public void saveHotData(Response response) {
                            super.saveHotData(response);
                            if (response.getErrno().equals("0")) {
                                Toast.makeText(FoundActivity.this, "上传成功", Toast.LENGTH_LONG).show();
                                proDialog.dismiss();
                                finish();
                                overridePendingTransition(android.R.anim.slide_out_right, android.R.anim.slide_in_left);
                            } else {
                                Toast.makeText(FoundActivity.this, response.getErrmsg(), Toast.LENGTH_LONG).show();
                            }
                            proDialog.cancel();
                        }
                    };
                    HotManager.saveHotData(FoundActivity.this, params,hotResponse);
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
        mTopBar.setTitleTextColor(Color.WHITE);
        mTopBar.setLeftBackground(R.drawable.backbutton);
        mTopBar.setOnTopbarLeftClickListener(new TopBar.TopbarLeftClickListener() {
            @Override
            public void leftClick() {
                finish();
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        });

        mFoundDetail = (EditText) findViewById(R.id.found_detail);
        mFoundTime = (EditText) findViewById(R.id.found_time);
        mFoundLocation = (EditText) findViewById(R.id.found_location);
        mFoundLine = (EditText) findViewById(R.id.found_line);
        mFoundBtn  = (Button) findViewById(R.id.found_BtnOK);
        if (losted)
        {
            mFoundTime.setHint("丢失物品大致时间");
            mFoundLocation.setHint("丢失物品大致地点地点");
        }

    }


}
