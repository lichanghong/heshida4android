package group.haihong.com.stu.Main;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebView;

import group.haihong.com.stu.R;
import group.haihong.com.stu.TopBar;

public class SchoolWebActivity extends Activity {
    TopBar mTopBar;
    private WebView mWebView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_school_web);
        initView();
        initData();
    }

    private void initData() {
        mWebView.loadUrl("http://www.henannu.edu.cn/");
    }

    private void initView() {
        mWebView = (WebView) findViewById(R.id.schoolWebView);
        mTopBar = (TopBar) findViewById(R.id.topbarWebSchool);
        mTopBar.getLeftButton().setEnabled(true);
        mTopBar.setLeftBackground(R.drawable.backbutton);
        mTopBar.setTitle("河师大网站");
        mTopBar.setTitleTextColor(Color.WHITE);
        mTopBar.setOnTopbarLeftClickListener(new TopBar.TopbarLeftClickListener() {
            @Override
            public void leftClick() {
                finish();
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        });

    }


}
