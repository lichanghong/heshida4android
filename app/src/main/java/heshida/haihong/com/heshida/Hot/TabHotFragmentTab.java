package heshida.haihong.com.heshida.Hot;


import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import heshida.haihong.com.heshida.Groups.GroupDetailActivity;
import heshida.haihong.com.heshida.LostAndFound.FoundActivity;
import heshida.haihong.com.heshida.R;

/**
 * Created by admin on 13-11-23.
 */
public class TabHotFragmentTab extends Fragment {
    private View _view;
    Button mFoundButton;
    ListView mHotListView;
    private SimpleAdapter mAdapter;

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
        Map<String, String> keyValuePair = new HashMap<String, String>();
        keyValuePair.put("title", "捡到身份证银行卡一张");
        keyValuePair.put("time", "2015/12/30 上午");
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        list.add(keyValuePair);

        mAdapter = new SimpleAdapter(getActivity(), list,
                android.R.layout.simple_list_item_2, new String[] { "title",
                "time" }, new int[] { android.R.id.text1,
                android.R.id.text2 });

        mHotListView.setAdapter(mAdapter);
        mHotListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("失物详细信息")
                        .setIcon(android.R.drawable.ic_dialog_email)
                        .setMessage("捡到身份证银行卡一张. \n2015/12/30 上午.\n在"+"河师大图书馆"+"捡到的.\n手机:15652284495.")
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
