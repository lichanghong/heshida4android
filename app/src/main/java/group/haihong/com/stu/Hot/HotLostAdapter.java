package group.haihong.com.stu.Hot;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import group.haihong.com.stu.R;

/**
 * Created by lichanghong on 1/9/16.
 */
public class HotLostAdapter extends BaseAdapter {
    List<Map<String, String>> mHotList;
    Context mContext;

    final int TYPE_1 = 0;
    final int TYPE_2 = 1;

    public HotLostAdapter(List<Map<String, String>> mHotList, Context mContext) {
        super();
        this.mHotList = mHotList;
        this.mContext = mContext;
    }

    @Override
    public int getItemViewType(int position) {
        Map<String, String> keyValuePair = mHotList.get(position);
        if (keyValuePair.get("losted").toString().equals("2"))
        {
            return TYPE_2;
        }
        else  return TYPE_1;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        return mHotList.size();
    }

    @Override
    public Object getItem(int position) {
        return mHotList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder1 = null;
        ViewHolder holder2 = null;
        int type = getItemViewType(position);

        if (convertView == null)
        {
            if (type == TYPE_1) {
                convertView = View.inflate(mContext, R.layout.lost_layout, null);
                holder1 = new ViewHolder();
                holder1.lost_item_title = (TextView) convertView.findViewById(R.id.lost_item_title);
                holder1.lost_item_time = (TextView) convertView.findViewById(R.id.lost_item_time);
                holder1.bg_image_tag = (ImageView) convertView.findViewById(R.id.bg_lost_item);
                convertView.setTag(holder1);
            }
            else
            {
                convertView = View.inflate(mContext, R.layout.lost_layout2, null);
                holder2 = new ViewHolder();
                holder2.lost_item_title = (TextView) convertView.findViewById(R.id.lost_item_title);
                holder2.lost_item_time = (TextView) convertView.findViewById(R.id.lost_item_time);
                holder2.bg_image_tag = (ImageView) convertView.findViewById(R.id.bg_lost_item);
                convertView.setTag(holder2);
            }
        }
        else
        {
            if (type == TYPE_1)
            {
                holder1 = (ViewHolder) convertView.getTag();
            }
            else {
                holder2 = (ViewHolder) convertView.getTag();
            }
        }
        Map<String, String> keyValuePair = mHotList.get(position);
        if (type == TYPE_1) {
            holder1.lost_item_title.setText(keyValuePair.get("title").toString());
            holder1.lost_item_time.setText(keyValuePair.get("time").toString());
            if (keyValuePair.get("losted").toString().equals("0"))
            {
                holder1.bg_image_tag.setImageResource(R.drawable.bg_found_item);
            }
            else
            {
                holder1.bg_image_tag.setImageResource(R.drawable.bg_lost_item);
            }
        }
        else
        {
            holder2.lost_item_title.setText(keyValuePair.get("title").toString());
            holder2.lost_item_time.setText(keyValuePair.get("time").toString());
            holder2.bg_image_tag.setImageResource(R.drawable.bg_lost_av);
        }

        return convertView;
    }

    class ViewHolder
    {
        TextView lost_item_title,lost_item_time;
        ImageView bg_image_tag;
    }
}
