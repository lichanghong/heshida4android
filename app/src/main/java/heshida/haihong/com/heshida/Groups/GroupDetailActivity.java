package heshida.haihong.com.heshida.Groups;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.util.HashMap;

import heshida.haihong.com.heshida.R;
import heshida.haihong.com.heshida.TopBar;
import heshida.haihong.com.heshida.Utils.Http.HttpUtil;
import heshida.haihong.com.heshida.Utils.net.Response;

public class GroupDetailActivity extends Activity {
    TopBar mTopBar;
    String groupid,groupname,description;
    EditText metgroupdetail;
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

        loadData();
    }

    private void loadData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap<String,String> params = new HashMap<>();
                params.put("sid",groupid);
                GroupManager.loadGroupDetail(GroupDetailActivity.this,params,new GroupResponse(){
                    @Override
                    public void loadGroupDetail(Response response) {
                        super.loadGroupDetail(response);
                        GroupModel model = (GroupModel) response.getData();
                        description = model.getDescription();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                metgroupdetail.setText(description);
                            }
                        });
                    }
                });
            }
        }).start();
    }

    private void initView() {
        mTopBar = (TopBar) findViewById(R.id.topbarGroup);
        mTopBar.getLeftButton().setEnabled(true);
        mTopBar.setLeftBackground(R.drawable.backbutton);
        mTopBar.setOnTopbarLeftClickListener(new TopBar.TopbarLeftClickListener() {
            @Override
            public void leftClick() {
                finish();
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        });

        metgroupdetail = (EditText) findViewById(R.id.groupdetail_edittext);
    }



}
