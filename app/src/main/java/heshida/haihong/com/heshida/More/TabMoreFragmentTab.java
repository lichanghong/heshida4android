package heshida.haihong.com.heshida.More;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import heshida.haihong.com.heshida.R;

/**
 * Created by admin on 13-11-23.
 */
public class TabMoreFragmentTab extends Fragment {

    private View _view;
    private ListView mListView;
    private ArrayAdapter<String> mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more, null);
        _view = view;

        initView();
        return view;
    }

    private void initView() {
        mListView = (ListView) _view.findViewById(R.id.more_listView);

        mListView.setAdapter(mAdapter);

        String[] data = {
                "支持我们", "清除缓存",
                "意见反馈", "联系我们",
                "关于我们", "退出登陆",
        };


        final List<String> d = new ArrayList<String>(Arrays.asList(data));

        mAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.preference_category,d);

        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                mAdapter.clear();
//                String[] data2 = {
//                        "sfdsfsdf","sdfasf"
//                };
//                final List<String> dd = new ArrayList<String>(Arrays.asList(data2));
//                mAdapter.addAll(dd);

                switch (position) {
                    case 0: {
                        supportUs();
                    }
                    break;
                    case 1: {
                        clearCache();
                    }
                    break;
                    case 2: {
                        feedBack();
                    }
                    break;
                    case 3: {
                        contactUs();
                    }
                    break;
                    case 4: {
                        aboutUs();
                    }
                    break;
                    case 5: {
                        exitApp();
                    }
                    break;
                    default:
                        break;
                }
            }
        });
    }

    private void supportUs() {

    }

    private void clearCache() {

    }
    private void feedBack() {

    }
    private void contactUs() {

    }
    private void aboutUs() {

    }
    private void exitApp()
    {
        getActivity().finish();
    }

}
