package heshida.haihong.com.heshida.AD;

import android.app.Activity;
import android.content.Context;
import android.view.View;

/**
 * Created by lichanghong on 1/1/16.
 */
public class ADManager {
    Context mContext;
    Activity mActivity;
    DuomengADController duomengADController;
    YouMiADController     youMiADController;

    public ADManager(Context mContext,Activity mActivity) {
        this.mContext = mContext;
        this.mActivity = mActivity;
    }

    private static ADManager sSingleton = null;
    public static synchronized ADManager getInstance(Context context,Activity mActivity) {
        if (sSingleton == null) {
            sSingleton = new ADManager(context,mActivity);
            sSingleton.duomengADController = new DuomengADController(mActivity);    //实例话对象
            sSingleton.youMiADController   = new YouMiADController(mActivity);
        }
        return sSingleton;
    }

    public void showDuoMengAD(View mView)
    {
        duomengADController.showADView(mView);
    }

    public void showYouMiAD(View mView)
    {
        youMiADController.showYOUMIAdView(mView);
    }


}
