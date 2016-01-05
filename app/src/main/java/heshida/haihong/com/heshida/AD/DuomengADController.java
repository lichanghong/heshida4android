package heshida.haihong.com.heshida.AD;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import cn.domob.android.ads.AdEventListener;
import cn.domob.android.ads.AdManager;
import cn.domob.android.ads.AdView;
import heshida.haihong.com.heshida.R;

/**
 * Created by lichanghong on 12/30/15.
 * 多盟
 */
public class DuomengADController {
    Activity mActivity;
    RelativeLayout _AdContainer;
    AdView mAdView;

    //多盟
    public  static  final String PUBLISHER_ID = "7568168";
    public  static  final String INLINE_PPID  = "5RPajD8dGexLfHH89T6DqjOtNrhgjbYq";

    public DuomengADController(Activity mActivity) {
        this.mActivity = mActivity;
    }

    public void showADView(View view) {
        final Activity mainActivity = mActivity;

        _AdContainer =(RelativeLayout) view.findViewById(R.id.duomengadcontainer);
        mAdView = new AdView(mainActivity, PUBLISHER_ID, INLINE_PPID);
        mAdView.setKeyword("game");
        mAdView.setAdSize(AdView.INLINE_SIZE_FLEXIBLE);
        mAdView.setAdEventListener(new AdEventListener() {
            @Override
            public void onEventAdReturned(AdView adView) {
                Log.i("domob", "onEventAdReturned");
            }

            @Override
            public void onAdFailed(AdView adView, AdManager.ErrorCode errorCode) {
                Log.i("domob", "onAdFailed "+errorCode);

            }

            @Override
            public void onAdOverlayPresented(AdView adView) {
                Log.i("domob", "onAdOverlayPresented");

            }

            @Override
            public void onAdOverlayDismissed(AdView adView) {
                Log.i("domob", "onAdOverlayDismissed");

            }

            @Override
            public void onLeaveApplication(AdView adView) {
                Log.i("domob", "onLeaveApplication");

            }

            @Override
            public void onAdClicked(AdView adView) {
                Log.i("domob", "onAdClicked");

            }



            @Override
            public Context onAdRequiresCurrentContext() {
                Log.i("domob", "onAdRequiresCurrentContext");

                return mainActivity;
            }
        });

        RelativeLayout.LayoutParams layout = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layout.addRule(RelativeLayout.CENTER_HORIZONTAL);
        mAdView.setLayoutParams(layout);
        _AdContainer.addView(mAdView);
    }


}
