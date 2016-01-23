package group.haihong.com.stu.AD;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.qhad.ads.sdk.adcore.Qhad;
import com.qhad.ads.sdk.interfaces.IQhAdEventListener;
import com.qhad.ads.sdk.interfaces.IQhBannerAd;

import group.haihong.com.stu.R;

/**
 * Created by lichanghong on 1/10/16.
 */
public class QHADController {
    Activity mActivity;
    LinearLayout _AdContainer;
    IQhBannerAd qhadAdBannerView;
    public QHADController(Activity mActivity) {
        this.mActivity = mActivity;
    }

    public void showADView(View view) {
        _AdContainer = (LinearLayout) view.findViewById(R.id.QHAdLinearLayout);
        final String adSpaceid = "ukkbFeTAjd";
        qhadAdBannerView = Qhad.showBanner(_AdContainer, mActivity, adSpaceid, false);
        qhadAdBannerView.setAdEventListener(new IQhAdEventListener() {

            @Override
            public void onAdviewGotAdSucceed() {
                Log.i("qihuad----","onAdviewGotAdSucceed");

            }

            @Override
            public void onAdviewGotAdFail() {
                Log.i("qihuad----","onAdviewGotAdFail");

            }

            @Override
            public void onAdviewRendered() {
                Log.i("qihuad----","onAdviewRendered");

            }

            @Override
            public void onAdviewIntoLandpage() {
                Log.i("qihuad----","onAdviewIntoLandpage");

            }

            @Override
            public void onAdviewDismissedLandpage() {
                Log.i("qihuad----","onAdviewDismissedLandpage");

            }

            @Override
            public void onAdviewClicked() {
                Log.i("qihuad----","onAdviewClicked");

            }

            @Override
            public void onAdviewClosed() {
                Log.i("qihuad----","onAdviewClosed");

            }

            @Override
            public void onAdviewDestroyed() {
                Log.i("qihuad----","onAdviewDestroyed");

            }
        });

    }

    public void releaseWAPSAD()
    {
        qhadAdBannerView.closeAds();
    }
}
