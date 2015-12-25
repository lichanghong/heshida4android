package heshida.haihong.com.heshida.Main;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import heshida.haihong.com.heshida.Main.Model.ScheduleAdapter;
import heshida.haihong.com.heshida.Main.Model.ScheduleModel;
import heshida.haihong.com.heshida.R;
import heshida.haihong.com.heshida.TopBar;

public class ScheduleSettingActivity extends Activity {
    TopBar mTopBar;
    Button mWeekBtn;
    ListView mListView;
    List<ScheduleModel> mScheduleList;
    ScheduleAdapter mScheduleAdapter;
    LinearLayout mWeekLayout;

    EditText et_amup_schedulename,et_amup_teachername,et_amup_location,et_amup_other;
    EditText et_amdown_schedulename,et_amdown_teachername,et_amdown_location,et_amdown_other;
    EditText et_pmup_schedulename,et_pmup_teachername,et_pmup_location,et_pmup_other;
    EditText et_pmdown_schedulename,et_pmdown_teachername,et_pmdown_location,et_pmdown_other;
    ScrollView mScrollView;
    Button mScheduleAddOk,mScheduleAddCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_schedule_setting);
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        mScheduleAddOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isNull = false;
                if (et_amdown_schedulename.getText().toString().trim().equals(""))
                {
                    isNull = true;
                    et_amdown_schedulename.setError("请填写内容");
                }
                if (et_amup_schedulename.getText().toString().trim().equals(""))
                {
                    isNull = true;
                    et_amup_schedulename.setError("请填写内容");
                }
                if (et_amup_teachername.getText().toString().trim().equals(""))
                {
                    isNull = true;
                    et_amup_teachername.setError("请填写内容");
                }
                if (et_amdown_teachername.getText().toString().trim().equals(""))
                {
                    isNull = true;
                    et_amdown_teachername.setError("请填写内容");
                }
                if (et_amup_location.getText().toString().trim().equals(""))
                {
                    et_amup_location.setError("请填写内容");
                    isNull = true;
                }

                if (et_pmup_schedulename.getText().toString().trim().equals(""))
                {
                    et_pmup_schedulename.setError("请填写内容");
                    isNull = true;
                }
                if (et_pmup_teachername.getText().toString().trim().equals(""))
                {
                    et_pmup_teachername.setError("请填写内容");
                    isNull = true;
                }

                if (et_pmdown_location.getText().toString().trim().equals(""))
                {
                    et_pmdown_location.setError("请填写内容");
                    isNull = true;
                }
                if (et_amdown_location.getText().toString().trim().equals(""))
                {
                    et_amdown_location.setError("请填写内容");
                    isNull = true;
                }

                if (et_pmdown_schedulename.getText().toString().trim().equals(""))
                {
                    et_pmdown_schedulename.setError("请填写内容");
                    isNull = true;
                }
                if (et_pmdown_teachername.getText().toString().trim().equals(""))
                {
                    et_pmdown_teachername.setError("请填写内容");
                    isNull = true;
                }

                if (et_pmup_location.getText().toString().trim().equals(""))
                {
                    et_pmup_location.setError("请填写内容");
                    isNull = true;
                }

                if (!isNull)
                {
                    ScheduleModel model = new ScheduleModel(
                            Integer.parseInt(mWeekBtn.getTag().toString())
                            ,et_amup_schedulename.getText().toString()+"\t"+et_amup_teachername.getText().toString()+
                            "\n"+et_amup_location.getText().toString()+ " "+ et_amup_other.getText().toString()

                            ,et_amdown_schedulename.getText().toString()+"\t"+et_amdown_teachername.getText().toString()+
                            "\n"+et_amup_location.getText().toString()+ " "+ et_amup_other.getText().toString()

                            ,et_pmup_schedulename.getText().toString()+"\t"+et_pmup_teachername.getText().toString()+
                            "\n"+et_pmup_location.getText().toString()+ " "+ et_pmup_other.getText().toString()

                            ,et_pmdown_schedulename.getText().toString()+"\t"+et_pmdown_teachername.getText().toString()+
                            "\n"+et_pmdown_location.getText().toString()+ " "+ et_pmdown_other.getText().toString()
                    );
                    mScheduleList.add(model);
                    mScheduleAdapter.notifyDataSetChanged();
                    //都填写了内容可以提交
                    clearAddList();
                    mScrollView.setVisibility(View.INVISIBLE);
                }

            }
        });

        mScheduleAddCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAddList();
                mScrollView.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void clearAddList()
    {
        et_amup_schedulename.setText("");
        et_amup_teachername.setText("");
        et_amup_location.setText("");
        et_amup_other.setText("");

        et_pmup_schedulename.setText("");
        et_pmup_teachername.setText("");
        et_pmup_location.setText("");
        et_pmup_other.setText("");

        et_amdown_schedulename.setText("");
        et_amdown_teachername.setText("");
        et_amdown_location.setText("");
        et_amdown_other.setText("");

        et_pmdown_schedulename.setText("");
        et_pmdown_teachername.setText("");
        et_pmdown_location.setText("");
        et_pmdown_other.setText("");
        InputMethodManager imm = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mScrollView.getWindowToken(), 0); //强制隐藏键盘
    }

    private void initData() {
        mScheduleList = new ArrayList<ScheduleModel>();

        mTopBar.setTitle("课程表设置");
        mScheduleAdapter = new ScheduleAdapter(mScheduleList,ScheduleSettingActivity.this);
        mListView.setAdapter(mScheduleAdapter);
    }

    private void initView() {
        mWeekLayout = (LinearLayout) findViewById(R.id.selectWeekLayout);
        mListView = (ListView) findViewById(R.id.scheduleListView);
        mWeekBtn = (Button) findViewById(R.id.selectWeekBtn);
        mTopBar = (TopBar) findViewById(R.id.topbarSchedule);
        mTopBar.getLeftButton().setEnabled(true);
        mTopBar.getRightButton().setEnabled(true);
        mTopBar.setLeftBackground(R.drawable.backbutton);
        mTopBar.getRightButton().setText("添加");
        mTopBar.setOnTopbarLeftClickListener(new TopBar.TopbarLeftClickListener() {
            @Override
            public void leftClick() {
                finish();
                overridePendingTransition(android.R.anim.slide_out_right, android.R.anim.slide_in_left);
            }
        });
        mTopBar.setOnTopbarRightClickListener(new TopBar.TopbarRightClickListener() {
            @Override
            public void rightClick() {
                if (checkSelect()) {
                    addSchedule();
                } else
                    Toast.makeText(ScheduleSettingActivity.this, "请选择星期", Toast.LENGTH_LONG).show();
            }
        });
        mScrollView = (ScrollView)findViewById(R.id.schedule_add_list_scroll);
        et_amup_schedulename = (EditText) findViewById(R.id.et_amup_schedulename);
        et_amup_teachername = (EditText) findViewById(R.id.et_amup_teachername);
        et_amup_location = (EditText) findViewById(R.id.et_amup_location);
        et_amup_other = (EditText) findViewById(R.id.et_amup_other);

        et_pmdown_schedulename = (EditText) findViewById(R.id.et_pmdown_schedulename);
        et_pmdown_teachername = (EditText) findViewById(R.id.et_pmdown_teachername);
        et_pmdown_location = (EditText) findViewById(R.id.et_pmdown_location);
        et_pmdown_other = (EditText) findViewById(R.id.et_pmdown_other);

        et_amdown_schedulename = (EditText) findViewById(R.id.et_amdown_schedulename);
        et_amdown_teachername = (EditText) findViewById(R.id.et_amdown_teachername);
        et_amdown_location = (EditText) findViewById(R.id.et_amdown_location);
        et_amdown_other = (EditText) findViewById(R.id.et_amdown_other);

        et_pmup_schedulename = (EditText) findViewById(R.id.et_pmup_schedulename);
        et_pmup_teachername = (EditText) findViewById(R.id.et_pmup_teachername);
        et_pmup_location = (EditText) findViewById(R.id.et_pmup_location);
        et_pmup_other = (EditText) findViewById(R.id.et_pmup_other);
        mScrollView.setVisibility(View.INVISIBLE);
        mScheduleAddOk = (Button) findViewById(R.id.scheduledialogueBtnOK);
        mScheduleAddCancel = (Button) findViewById(R.id.scheduledialogueBtnCancel);
     }

    private void addSchedule()
    {
        mScrollView.setVisibility(View.VISIBLE);
        mWeekLayout.setVisibility(View.INVISIBLE);
    }


    private boolean checkSelect()
    {
        if (!mWeekBtn.getText().equals("选择星期"))
        {
            return true;
        }
        else  return false;
    }

    public void handleAction(View view)
    {
        if (view.getId() == mWeekBtn.getId()) {
            final String[] weeks = new String[]{"选择星期", "星期一", "星期二", "星期三", "星期四", "星期五"};
            AlertDialog.Builder builder = new AlertDialog.Builder(ScheduleSettingActivity.this);
            builder.setTitle("请选择星期").setIcon(android.R.drawable.ic_dialog_info).
                    setSingleChoiceItems(weeks, 0,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    String selectitem = weeks[which];
                                    mWeekBtn.setText(selectitem);
                                    mWeekBtn.setTag(which);
                                    dialog.dismiss();
                                }
                            }).setNegativeButton("取消", null).show();
        }
    }



}
