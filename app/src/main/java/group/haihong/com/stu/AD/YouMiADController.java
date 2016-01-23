package group.haihong.com.stu.AD;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import group.haihong.com.stu.R;
import kll.dod.rtk.AdManager;
import kll.dod.rtk.br.AdSize;
import kll.dod.rtk.br.AdView;
import kll.dod.rtk.br.AdViewListener;


/**
 * Created by lichanghong on 12/30/15.
 * 有米
 */
public class YouMiADController {
    Activity mActivity;
    LinearLayout madLayout;
    //有米
    public  static  final String youmiPUBLISHER_ID = "adbb19820887ec19";
    public  static  final String youmiINLINE_PPID  = "f75651f7bc267359";//应用秘钥


    public YouMiADController(Activity mActivity) {
        this.mActivity = mActivity;
        //请务必在应用第一个 Activity（启动的第一个类）的 onCreate 中调用以下代码  youmi
        AdManager.getInstance(mActivity.getApplicationContext()).init(youmiPUBLISHER_ID, youmiINLINE_PPID, false);
    }

    public void showYOUMIAdView(View view)
    {
        // 实例化广告条
        AdView adView = new AdView(mActivity,AdSize.FIT_SCREEN);

// 获取要嵌入广告条的布局
        LinearLayout adLayout=(LinearLayout)view.findViewById(R.id.youmiadLayout);

// 将广告条加入到布局中
        adLayout.addView(adView);
        adView.setAdListener(new AdViewListener() {

            @Override
            public void onSwitchedAd(AdView adView) {
                // 切换广告并展示\
                Log.e("youmiad","onSwitchedAd");
            }

            @Override
            public void onReceivedAd(AdView adView) {
                // 请求广告成功
                Log.e("youmiad","onReceivedAd");

            }

            @Override
            public void onFailedToReceivedAd(AdView adView) {
                // 请求广告失败
                Log.e("youmiad","onFailedToReceivedAd");
            }
        });
    }



}
