package heshida.haihong.com.heshida.Hot;


import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
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
        View view = inflater.inflate(R.layout.fragment_hot, null);
        _view = view;

        initView();
        initListener();
        initData();
        return view;
    }

    private void initData() {
        mHotList = new ArrayList<Map<String, String>>();
        mAdapter = new SimpleAdapter(getActivity(), mHotList,
                android.R.layout.simple_list_item_2, new String[] { "title",
                "time" }, new int[] { android.R.id.text1,
                android.R.id.text2 });
        mHotListView.setAdapter(mAdapter);

        mHotListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HotModel hotModel = mHotModels.get(position);
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
