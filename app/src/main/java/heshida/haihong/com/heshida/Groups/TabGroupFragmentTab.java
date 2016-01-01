package heshida.haihong.com.heshida.Groups;


import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.HandlerThread;
import android.support.annotation.MainThread;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import heshida.haihong.com.heshida.R;
import heshida.haihong.com.heshida.Utils.net.Response;

/**
 * Created by lichanghong on 13-11-23.
 */
public class TabGroupFragmentTab extends Fragment {
    private static final int REFRESH_COMPLETE = 0X110;
    private SwipeRefreshLayout mSwipeLayout;
    private View _view;
    private ListView mListView;
    private List<GroupModel> mGroupList;
    MyAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (null != _view) {
            ViewGroup parent = (ViewGroup) _view.getParent();
            if (null != parent) {
                parent.removeView(_view);
            }
        } else {
            _view = inflater.inflate(R.layout.fragment_group, null);
            initView();
            initData();
            Log.i("lifetime---", "oncreateview-------TabGroupFragmentTab----------------");        }
        return _view;
    }

    private void initData() {
        loadData();

        mGroupList = new ArrayList<GroupModel>();
        mAdapter = new MyAdapter();
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(_view.getContext(), GroupDetailActivity.class);
                Bundle bundle = new Bundle();
                GroupModel model = mGroupList.get(position);
                bundle.putString("groupid", model.getSid());
                bundle.putString("groupname", model.getGroupname());
                intent.putExtras(bundle);
                startActivity(intent);
                getActivity().overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.fade_out);
            }
        });
    }

    private void loadData()
    {
        final ProgressDialog proDialog = android.app.ProgressDialog.show(getActivity(), "", "努力加载中...");
        GroupManager.loadGroupNames(_view.getContext(),new GroupResponse(){
            @Override
            public void loadGroupNames(Response response) {
                super.loadGroupNames(response);
                if (response.getErrno().toString().equals("0")) {
                    mGroupList = (List<GroupModel>) response.getData();
                    mAdapter.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(getActivity(), response.getErrmsg(), Toast.LENGTH_LONG).show();
                }
                proDialog.dismiss();
            }
        });
    }

    private void initView() {
        mSwipeLayout = (SwipeRefreshLayout) _view.findViewById(R.id.id_swipe_ly);
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    mSwipeLayout.setRefreshing(false);
                                }
                            });

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }).start();
            }
        });
        mSwipeLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        mListView = (ListView) _view.findViewById(R.id.group_listView);
    }
    private class MyAdapter extends BaseAdapter
    {
        @Override
        public int getCount() {
            return mGroupList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
          ViewHolder holder = null;
            if (convertView == null)
            {
                convertView = View.inflate(_view.getContext(),R.layout.simple_list_item,null);
                holder = new ViewHolder();
                holder.textView = (TextView) convertView.findViewById(R.id.itemtext);
                convertView.setTag(holder);
            }
            else
            {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.textView.setText(mGroupList.get(position).getGroupname().toString());
             return convertView;
        }
    }

    class ViewHolder
    {
        TextView textView;
    }

}

