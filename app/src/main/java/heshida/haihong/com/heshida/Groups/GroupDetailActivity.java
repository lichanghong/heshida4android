package heshida.haihong.com.heshida.Groups;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import heshida.haihong.com.heshida.R;
import heshida.haihong.com.heshida.TopBar;

public class GroupDetailActivity extends Activity {
    TopBar mTopBar;
    String groupid,groupname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_group_detail);
        initView();
        initData();
    }

    private void initData() {
        Bundle bundle = getIntent().getExtras();
        groupid = bundle.getString("groupid");
        groupname = bundle.getString("groupname");
        mTopBar.setTitle(groupname);
    }

    private void initView() {
        mTopBar = (TopBar) findViewById(R.id.topbarGroup);
        mTopBar.getLeftButton().setEnabled(true);
        mTopBar.setLeftBackground(R.drawable.backbutton);
        mTopBar.setOnTopbarLeftClickListener(new TopBar.TopbarLeftClickListener() {
            @Override
            public void leftClick() {
                finish();
                overridePendingTransition(android.R.anim.slide_out_right, android.R.anim.slide_in_left);
            }
        });
    }



}
