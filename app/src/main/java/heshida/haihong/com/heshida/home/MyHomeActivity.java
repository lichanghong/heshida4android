package heshida.haihong.com.heshida.home;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Button;

import heshida.haihong.com.heshida.R;
import heshida.haihong.com.heshida.TopBar;

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
