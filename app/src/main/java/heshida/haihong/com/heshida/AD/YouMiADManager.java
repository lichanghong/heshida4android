package heshida.haihong.com.heshida.AD;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import net.youmi.android.AdManager;
import net.youmi.android.banner.AdSize;
import net.youmi.android.banner.AdView;
import net.youmi.android.banner.AdViewListener;

import heshida.haihong.com.heshida.R;

/**
 * Created by lichanghong on 12/30/15.
 */
public class YouMiADManager {
    Activity mActivity;
    LinearLayout madLayout;
    //有米
    public  static  final String youmiPUBLISHER_ID = "adbb19820887ec19";
    public  static  final String youmiINLINE_PPID  = "f75651f7bc267359";//应用秘钥


    public YouMiADManager(Activity mActivity) {
        this.mActivity = mActivity;
        //请务必在应用第一个 Activity（启动的第一个类）的 onCreate 中调用以下代码  youmi
        AdManager.getInstance(mActivity.getApplicationContext()).init(youmiPUBLISHER_ID, youmiINLINE_PPID, true);
    }

    public void showYOUMIAdView(View view)
    {
        // 实例化广告条
        AdView adView = new AdView(view.getContext(), AdSize.FIT_SCREEN);

// 获取要嵌入广告条的布局
        madLayout =(LinearLayout)view.findViewById(R.id.adLayout);

// 将广告条加入到布局中
        madLayout.addView(adView);

        adView.setAdListener(new AdViewListener() {

            @Override
            public void onSwitchedAd(AdView adView) {
                // 切换广告并展示
                Log.i("youmi", "onSwitchedAd");

            }

            @Override
            public void onReceivedAd(AdView adView) {
                // 请求广告成功
                Log.i("youmi", "onReceivedAd");

            }

            @Override
            public void onFailedToReceivedAd(AdView adView) {
                // 请求广告失败
                Log.i("youmi", "onFailedToReceivedAd");

            }
        });
    }



}
