package group.haihong.com.stu.More;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import group.haihong.com.stu.R;
import group.haihong.com.stu.Utils.Http.Reachability;
import group.haihong.com.stu.Utils.VersionManager;
import group.haihong.com.stu.Utils.net.Response;

/**
 * Created by admin on 13-11-23.
 */
public class TabMoreFragmentTab extends Fragment {

    private View _view;
    private ListView mListView;
    private ArrayAdapter<String> mAdapter;
    private TextView mVersionText;
    private Button mAgreementBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (null != _view) {
            ViewGroup parent = (ViewGroup) _view.getParent();
            if (null != parent) {
                parent.removeView(_view);
            }
        } else {
            _view = inflater.inflate(R.layout.fragment_more, null);
            initView();
            initData();
            initListener();
            Log.i("lifetime---", "oncreateview-------TabMoreFragmentTab----------------");        }
        return _view;
    }

    private void initListener() {
        mAgreementBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),AgreementActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.fade_out);

            }
        });
    }

    private void initData() {
        String[] data = {
                "意见反馈", "联系我们",
                "关于我们", "检测更新",
                "退出登陆",
        };


        final List<String> d = new ArrayList<String>(Arrays.asList(data));

        mAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.preference_category,d);

        mListView.setAdapter(mAdapter);
        ListViewCellClick();
        mVersionText.setText("当前版本号:"+getAppVersionName());

    }

    private void initView() {
        mVersionText = (TextView) _view.findViewById(R.id.versionText);
        mListView = (ListView) _view.findViewById(R.id.more_listView);
        mAgreementBtn = (Button) _view.findViewById(R.id.agreementBtn);

    }

    /**
     * 返回当前程序版本名
     */
    public String getAppVersionName() {
        String versionName = "";
        try {
            // ---get the package info---
            PackageManager pm = _view.getContext().getPackageManager();
            PackageInfo pi = pm.getPackageInfo(_view.getContext().getPackageName(), 0);
            versionName = pi.versionName;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return versionName;
    }


    private void ListViewCellClick() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: {
                        feedBack();
                    }
                    break;
                    case 1: {
                        contactUs();
                    }
                    break;
                    case 2: {
                        aboutUs();
                    }
                    break;
                    case 3: {
                        checkUpdate();
                    }
                    break;
                    case 4: {
                        exitApp();
                    }
                    break;
                    default:
                        break;
                }
            }
        });
    }

    private void checkUpdate()
    {
       if (Reachability.isNetworkAvailable(getActivity()))
       {
           VersionManager versionManager = VersionManager.getInstance(getActivity());
           versionManager.checkUpdate();
       }
        else
       {
           Toast.makeText(getActivity(), "您好像没网络了哦~", Toast.LENGTH_LONG).show();
       }

    }

    private void feedBack() {
        final EditText editText = new EditText(getActivity());
//设置EditText的显示方式为多行文本输入
        editText.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
//文本显示的位置在EditText的最上方
        editText.setGravity(Gravity.TOP);
        editText.setHint("请输入您的宝贵意见或建议~");
//改变默认的单行模式
        editText.setSingleLine(false);
//水平滚动设置为False
        editText.setHorizontallyScrolling(false);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("意见反馈")
                .setIcon(android.R.drawable.ic_dialog_email)
                .setView(editText)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (editText.getText().toString().isEmpty()) {
                            Toast.makeText(getActivity(), "请填写反馈内容", Toast.LENGTH_LONG).show();
                        } else {
                            FeedbackManager.feedback(_view.getContext(),editText.getText().toString(), new FeedbackResponse() {
                                @Override
                                public void feedBack(Response response) {
                                    super.feedBack(response);
                                    if (response.getErrno().equals("0")) {
                                        Toast.makeText(getActivity(), "反馈成功,谢谢您的支持", Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(getActivity(), response.getErrmsg(), Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        })
        ;
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void contactUs() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("联系我们")
                .setIcon(android.R.drawable.ic_dialog_email)
                .setMessage("QQ:1613391871 \n手机:15652284495\n欢迎招商与合作")
                .setPositiveButton("知道了", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void aboutUs() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("关于我们")
                .setIcon(android.R.drawable.btn_star)
                .setMessage("我们是自由群体，开发校园app来方便学生生活学习和娱乐!\n欢迎招商与合作")
                .setPositiveButton("知道了", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();

    }
    private void exitApp()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("确定要退出吗？")
                .setIcon(android.R.drawable.ic_dialog_info)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getActivity().finish();
                    }
                })
        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }

}
