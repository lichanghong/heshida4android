package heshida.haihong.com.heshida.AD;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.baidu.appx.BDBannerAd;

import heshida.haihong.com.heshida.R;

/**
 * Created by lichanghong on 1/4/16.
 */
public class BaiduADController {
    Activity mActivity;

    private static String TAG = "AppX_BannerAd";
    private RelativeLayout appxBannerContainer;
    private static BDBannerAd bannerAdView;

    public BaiduADController(Activity mActivity) {
        this.mActivity = mActivity;
    }

    //多盟
    public  static  final String PUBLISHER_ID = "5RPajD8dGexLfHH89T6DqjOtNrhgjbYq";
    public  static  final String INLINE_PPID  = "jdpOgbDm1BZS2ysGYPOrupSU";

    public void showBaiduAD(View view)
    {
        // 创建广告视图，发布时要使用正确的apikey和广告位id
        bannerAdView = new BDBannerAd(mActivity, PUBLISHER_ID, INLINE_PPID);

        // 设置横幅尺寸，如果不设置默认flexible
        bannerAdView.setAdSize(BDBannerAd.SIZE_320X50);

        // 设置横幅广告行为监听器
        bannerAdView.setAdListener(new BDBannerAd.BannerAdListener() {

            @Override
            public void onAdvertisementDataDidLoadFailure() {
                Log.e(TAG, "load failure");
            }

            @Override
            public void onAdvertisementDataDidLoadSuccess() {
                Log.e(TAG, "load success");
            }

            @Override
            public void onAdvertisementViewDidClick() {
                Log.e(TAG, "on click");
            }

            @Override
            public void onAdvertisementViewDidShow() {
                Log.e(TAG, "on show");
            }

            @Override
            public void onAdvertisementViewWillStartNewIntent() {
                Log.e(TAG, "leave app");
            }
        });

        // 创建广告容器
        appxBannerContainer = (RelativeLayout)view.findViewById(R.id.appx_banner_container);

        // 显示广告视图
        appxBannerContainer.addView(bannerAdView);
    }

}
