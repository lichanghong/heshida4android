package heshida.haihong.com.heshida.Groups;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import heshida.haihong.com.heshida.R;

/**
 * Created by lichanghong on 13-11-23.
 */
public class TabGroupFragmentTab extends Fragment {

    private View _view;
    private ListView mListView;
    private ArrayAdapter<String>mAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_group, null);
        _view = view;

        initView();
        return view;
    }

    private void initView() {
        mListView = (ListView) _view.findViewById(R.id.group_listView);

        mListView.setAdapter(mAdapter);

        final String[] data = {
                "武术散打协会","援孤社协会",
                "aaaaaaaaaaaaaaaaaa","bbbbbbbbbbbbbbbb",
                "aaaaaaaaaaaaaaaaaa","bbbbbbbbbbbbbbbb",
                "aaaaaaaaaaaaaaaaaa","bbbbbbbbbbbbbbbb",
        };


        final List<String> d = new ArrayList<String>(Arrays.asList(data));

        mAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,d);

        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(_view.getContext(), GroupDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("groupid", "`123");
                bundle.putString("groupname", data[position]);
                intent.putExtras(bundle);
                startActivity(intent);
                getActivity().overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.fade_out);
            }
        });
    }

}
