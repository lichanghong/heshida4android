package heshida.haihong.com.heshida.Hot;


import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.UiThread;
import android.test.UiThreadTest;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import heshida.haihong.com.heshida.Groups.GroupDetailActivity;
import heshida.haihong.com.heshida.LostAndFound.FoundActivity;
import heshida.haihong.com.heshida.R;
import heshida.haihong.com.heshida.Utils.Http.HttpUtil;
import heshida.haihong.com.heshida.Utils.net.Response;

/**
 * Created by admin on 13-11-23.
 */
public class TabHotFragmentTab extends Fragment {
    private View _view;
    Button mFoundButton;
    ListView mHotListView;
    private SimpleAdapter mAdapter;
    List<Map<String, String>> mHotList;
    List<HotModel>mHotModels;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (null != _view) {
            ViewGroup parent = (ViewGroup) _view.getParent();
            if (null != parent) {
                parent.removeView(_view);
            }
        } else {
            _view = inflater.inflate(R.layout.fragment_hot, null);
            initView();
            initListener();
            initData();
            Log.i("lifetime---", "oncreateview-------TabHotFragmentTab----------------");        }
        return _view;
    }

    private void initData() {
        mHotList = new ArrayList<Map<String, String>>();
        mAdapter = new SimpleAdapter(_view.getContext(), mHotList,
                android.R.layout.simple_list_item_2, new String[] { "title",
                "time" }, new int[] { android.R.id.text1,
                android.R.id.text2 });
        mHotListView.setAdapter(mAdapter);

        mHotListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                final HotModel hotModel = mHotModels.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("失物详细信息")
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setMessage(hotModel.getTitle()+"\n"+ hotModel.getFoundtime()
                                +"\n"+ hotModel.getLocation()+"\n"+hotModel.getLine())
                        .setPositiveButton("知道了", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                .setNegativeButton("举报虚假信息", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, final int which) {
                        HashMap<String,String>params = new HashMap<String, String>();
                        params.put("lostid",hotModel.getLostid());
                        HotManager.reportFakeMessage(_view.getContext(), params, new HotResponse(){
                            @Override
                            public void reportFakeMessage(Response response) {
                                super.reportFakeMessage(response);
                                if (response.getErrno().equals("0")) {
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(_view.getContext(), "举报成功", Toast.LENGTH_LONG).show();
                                            mHotList.remove(position);
                                            mAdapter.notifyDataSetChanged();
                                        }
                                    });

                                }
                                else {
                                    Toast.makeText(_view.getContext(),"举报失败", Toast.LENGTH_LONG).show();
                                }
                                dialog.cancel();
                            }
                        });
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        final ProgressDialog proDialog = android.app.ProgressDialog.show(getActivity(), "", "努力加载中...");
        HotManager.loadHotData(_view.getContext(), null, new HotResponse() {
            @Override
            public void loadHotData(Response response) {
                super.loadHotData(response);
                if (response.getErrno().equals("0")) {
                    mHotModels = (List<HotModel>) response.getData();
                    for (int i = 0; i < mHotModels.size(); i++) {
                        Map<String, String> keyValuePair = new HashMap<String, String>();
                        keyValuePair.put("title", mHotModels.get(i).getTitle());
                        keyValuePair.put("time", mHotModels.get(i).getFoundtime());
                        mHotList.add(keyValuePair);
                    }
                    mAdapter.notifyDataSetChanged();

                } else {
                    Toast.makeText(getActivity(), response.getErrmsg(), Toast.LENGTH_LONG).show();
                }
                proDialog.dismiss();
            }
        });
    }

    private void initListener() {
        mFoundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FoundActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        mFoundButton  = (Button) _view.findViewById(R.id.pickedSomething);
        mHotListView  = (ListView) _view.findViewById(R.id.hotListView);

    }

}
