package heshida.haihong.com.heshida.Hot;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import heshida.haihong.com.heshida.R;

/**
 * Created by lichanghong on 1/9/16.
 */
public class HotLostAdapter extends BaseAdapter {
    List<Map<String, String>> mHotList;
    Context mContext;

    public HotLostAdapter(List<Map<String, String>> mHotList, Context mContext) {
        super();
        this.mHotList = mHotList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mHotList.size();
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
            convertView = View.inflate(mContext, R.layout.lost_layout,null);
            holder = new ViewHolder();
            holder.lost_item_title = (TextView) convertView.findViewById(R.id.lost_item_title);
            holder.lost_item_time  = (TextView) convertView.findViewById(R.id.lost_item_time);
            holder.bg_image_tag  = (ImageView) convertView.findViewById(R.id.bg_lost_item);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        Map<String, String> keyValuePair = mHotList.get(position);
        holder.lost_item_title.setText(keyValuePair.get("title").toString());
        holder.lost_item_time.setText(keyValuePair.get("time").toString());
        if (keyValuePair.get("losted").toString().equals("0"))
        {
            holder.bg_image_tag.setImageResource(R.drawable.bg_found_item);
        }
        else
        {
            holder.bg_image_tag.setImageResource(R.drawable.bg_lost_item);
        }
        return convertView;
    }

    class ViewHolder
    {
        TextView lost_item_title,lost_item_time;
        ImageView bg_image_tag;
    }
}
