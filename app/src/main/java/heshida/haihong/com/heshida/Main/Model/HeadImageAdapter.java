package heshida.haihong.com.heshida.Main.Model;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by lichanghong on 1/4/16.
 */
public class HeadImageAdapter extends BaseAdapter {
    private String[] list;
    private Context context;
//
//    public HeadImageAdapter(Context context, String[] list) {
//
//        this.context = context;
//
//        this.list = list;
//
//        //创建默认的ImageLoader配置参数
//        ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(context);
//
//        //Initialize ImageLoader with configuration.
//        ImageLoader.getInstance().init(configuration);
//    }

    @Override
    public int getCount() {
        return list.length;
    }

    @Override
    public Object getItem(int position) {
        return list[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
//
//    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//
//        ViewHolder holder = null;
//
//        if (convertView == null) {
//
//            holder = new ViewHolder();
//
//            convertView = LayoutInflater.from(context).inflate(
//                    R.layout.list_item, null);
//            holder.img = (NetworkImageView) convertView
//                    .findViewById(R.id.userimage);
//
//            convertView.setTag(holder);
//
//        } else {
//
//            holder = (ViewHolder) convertView.getTag();
//
//        }
//        String imgUrl = list[position];
//
//        if (imgUrl != null && !imgUrl.equals("")) {
//
//            holder.img.setDefaultImageResId(R.drawable.ic_launcher);
//            holder.img.setErrorImageResId(R.drawable.ic_launcher);
//            holder.img.setImageUrl(imgUrl, imageLoader);
//
//        }
//
        return convertView;
    }
//
//    static class ViewHolder {
//        NetworkImageView img;
//    }

}
