package group.haihong.com.stu;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import group.haihong.com.stu.Register.Login.LoginActivity;
import group.haihong.com.stu.Vendor.StuConstants;
import group.haihong.com.stu.home.MyHomeActivity;


//1.3版本修改点：
// 添加上传寻找失物信息功能
// 添加程序在线升级
// 首页网页去掉，改为安卓视图
// 添加下拉刷新

public class MainActivity extends Activity {

    private FragmentManager fragmentManager;
    private RadioGroup radioGroup;
    private TopBar topbar1;
    FragmentFactory fragmentFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        fragmentManager = getFragmentManager();
        radioGroup = (RadioGroup) findViewById(R.id.rg_tab);
        final RadioButton radioButton = (RadioButton) radioGroup.getChildAt(0);
        topbar1 = (TopBar) findViewById(R.id.topbar1);
        fragmentFactory = new FragmentFactory();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                Fragment fragment = fragmentFactory.getInstanceByIndexOfChild(radioGroup.indexOfChild(findViewById(checkedId)));
                transaction.replace(R.id.content, fragment);
                transaction.commit();
                //切换tab时更改topbar样式
                topbarState(radioGroup.indexOfChild(findViewById(checkedId)));
            }
        });
        radioButton.setChecked(true);

        //topbar init
        initEvent();

    }

    private void topbarState(int checkid)
    {
        checkid = checkid<=0?1:checkid+1;
        switch (checkid)
        {
            case 1:
            {
                topbar1.getLeftButton().setVisibility(View.VISIBLE);
                topbar1.getRightButton().setVisibility(View.INVISIBLE);
                topbar1.setTitle(StuConstants.App_NAME);
            }
            break;
            case 2:
            {
                topbar1.getLeftButton().setVisibility(View.INVISIBLE);
                topbar1.getRightButton().setVisibility(View.INVISIBLE);
                topbar1.setTitle(StuConstants.Group_NAME);
            }
                break;
            case 3:
            {
                topbar1.getLeftButton().setVisibility(View.INVISIBLE);
                topbar1.getRightButton().setVisibility(View.INVISIBLE);
                topbar1.setTitle("热门");
            }
            break;
            case 4:
            {
                topbar1.getLeftButton().setVisibility(View.INVISIBLE);
                topbar1.getRightButton().setVisibility(View.INVISIBLE);
                topbar1.setTitle("更多");
            }
            break;
        }
    }


    private void initEvent() {
        topbar1.setTitle(StuConstants.App_NAME);
        topbar1.setTitleTextSize(20);
        topbar1.setTitleTextColor(Color.WHITE);

        topbar1.setRightHeight(20);
        topbar1.setRightWidth(20);
        topbar1.setRightBackground(R.drawable.tab_mine_l);

        topbar1.getLeftButton().setEnabled(false);
        topbar1.setLeftBackground(R.mipmap.ic_launcher);

//        topbar1.setOnTopbarLeftClickListener(new TopBar.TopbarLeftClickListener() {
//            @Override
//            public void leftClick() {
//                Toast.makeText(MainActivity.this, "left", Toast.LENGTH_SHORT)
//                        .show();
//            }
//        });

        topbar1.setOnTopbarRightClickListener(new TopBar.TopbarRightClickListener() {

            @Override
            public void rightClick() {
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                if (true) //未登陆
                {
                    intent.setClass(MainActivity.this,LoginActivity.class);
                    startActivity(intent);
                }
                else
                {
                    intent.setClass(MainActivity.this,MyHomeActivity.class);
                    startActivity(intent);
                }
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.fade_out);
            }
        });

    }
}
