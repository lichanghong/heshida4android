package group.haihong.com.stu.More;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.webkit.WebView;

import group.haihong.com.stu.R;
import group.haihong.com.stu.TopBar;

public class AgreementActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_agreement);
        initView();
    }

    TopBar mTopBar;



    private void initView() {
        mTopBar = (TopBar) findViewById(R.id.topBarAgreement);
        mTopBar.getLeftButton().setEnabled(true);
        mTopBar.setLeftBackground(R.drawable.backbutton);
        mTopBar.setTitle("Stu服务使用协议");
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
