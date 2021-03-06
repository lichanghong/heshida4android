package group.haihong.com.stu.AD;

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
    YouMiADController   youMiADController;
    BaiduADController   baiduADController;
    WAPSADController    wapsadController;
    QHADController      qhadController;

    public ADManager(Context mContext,Activity mActivity) {
        this.mContext = mContext;
        this.mActivity = mActivity;
    }
    public void showAD(View view)
    {
        ADManager manager =  ADManager.getInstance(mContext, mActivity);
        manager.showBaiduAD(view);
    }
    private static ADManager sSingleton = null;
    public static synchronized ADManager getInstance(Context context,Activity mActivity) {
        if (sSingleton == null) {
            sSingleton = new ADManager(context,mActivity);
            sSingleton.duomengADController = new DuomengADController(mActivity);    //实例话对象
            sSingleton.youMiADController   = new YouMiADController(mActivity);
            sSingleton.baiduADController   = new BaiduADController(mActivity);
            sSingleton.wapsadController    = new WAPSADController(mActivity);
            sSingleton.qhadController      = new QHADController(mActivity);
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

    public void showBaiduAD(View mView)
    {
        baiduADController.showBaiduAD(mView);
    }
    public void showWAPSAD(View mView)
    {
        wapsadController.showADView(mView);
    }
    public void showQHAD(View mView)
    {
        qhadController.showADView(mView);
    }

}
