package heshida.haihong.com.heshida.AD;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import cn.waps.AppConnect;
import cn.waps.AppListener;
import heshida.haihong.com.heshida.R;

/**
 * Created by lichanghong on 1/2/16.
 * 万普世纪传媒
 * APP_ID 为应用标识,该值由万普后台添加应用后自劢生成,点击“应用详情”获取;
 * APP_PID 为分发渠道标识,使用规则请参见本文档附表《常用渠道编码表》
 */
public class WAPSADController {
    Activity mActivity;
    LinearLayout _AdContainer;

    public  static  final String WAPS_APP_ID = "d2978396e7a4c62b0ca9b58ae50d062d";
    public  static  final String WAPS_APP_PID= "default";//渠道

    public WAPSADController(Activity mActivity) {
        this.mActivity = mActivity;
        //请务必在应用第一个 Activity（启动的第一个类）中调用以下代码
        AppConnect.getInstance(WAPS_APP_ID, WAPS_APP_PID, mActivity);
    }

    public void showADView(View view) {
        _AdContainer =(LinearLayout) view.findViewById(R.id.wanpuAdLinearLayout);
        AppConnect.getInstance(mActivity).setBannerAdNoDataListener(
                new AppListener(){
                    @Override
                    public void onPopNoData() {
                        super.onPopNoData();
                        Log.e("WAPSADController", "Banner广告无数据");
                    }
                });
        AppConnect.getInstance(mActivity).showBannerAd(mActivity, _AdContainer);
    }

    public void releaseWAPSAD()
    {
        AppConnect.getInstance(mActivity).close();
    }

}
