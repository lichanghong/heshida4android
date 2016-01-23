package group.haihong.com.stu.home;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import group.haihong.com.stu.R;
import group.haihong.com.stu.TopBar;

public class MyHomeActivity extends Activity {
    TopBar mTopBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_my_home);
        initView();
        initData();
    }

    private void initData() {
        mTopBar.setTitle("我的");
    }

    private void initView() {
        mTopBar = (TopBar) findViewById(R.id.topbarRegister);


    }


}
