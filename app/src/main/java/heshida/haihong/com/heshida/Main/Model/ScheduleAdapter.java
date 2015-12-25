package heshida.haihong.com.heshida.Main.Model;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import heshida.haihong.com.heshida.Main.ScheduleSettingActivity;
import heshida.haihong.com.heshida.R;

/**
 * Created by lichanghong on 12/20/15.
 */
public class ScheduleAdapter extends BaseAdapter
{
    List<ScheduleModel> scheduleModels;
    ScheduleSettingActivity mContext;

    public ScheduleAdapter(List<ScheduleModel> scheduleModels, ScheduleSettingActivity mContext) {
        this.scheduleModels = scheduleModels;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return scheduleModels.size();
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
            convertView = View.inflate(mContext, R.layout.schedule_layout,null);
            holder = new ViewHolder();
            holder.tvweek = (TextView) convertView.findViewById(R.id.tv_week);
            holder.tvam = (TextView) convertView.findViewById(R.id.tvam);
            holder.amone = (TextView) convertView.findViewById(R.id.AM_one);
            holder.amtwo = (TextView) convertView.findViewById(R.id.AM_two);
            holder.tvpm = (TextView) convertView.findViewById(R.id.tvpm);
            holder.pmone = (TextView) convertView.findViewById(R.id.PM_one);
            holder.pmtwo = (TextView) convertView.findViewById(R.id.PM_two);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        ScheduleModel model = scheduleModels.get(position);

        holder.tvweek.setText("星\n期\n" + model.getWeek());

        holder.tvam.setText("上\n午");
        holder.amone.setText(model.getAmupDetail());
        holder.amtwo.setText(model.getAmdownDetail());
        holder.tvpm.setText("下\n午");
        holder.pmone.setText(model.getPmupDetail());
        holder.pmtwo.setText(model.getPmdownDetail());
        return convertView;
    }

    class ViewHolder
    {
        TextView tvweek,tvam,amone,amtwo;
        TextView tvpm,pmone,pmtwo;

    }
}