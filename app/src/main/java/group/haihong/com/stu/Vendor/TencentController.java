package group.haihong.com.stu.Vendor;

import android.app.Activity;
import android.content.Intent;

import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

/**
 * Created by lichanghong on 1/21/16.
 */
public class TencentController {
    Activity mActivity;
    Tencent  mTencent;
    IUiListener mUiListener;

    public  static  final String APP_ID = "1105132594";
    public  static  final String APP_KEY= "91X3Znnua8mExAnL";

    public TencentController(Activity mActivity) {
        this.mActivity = mActivity;
        // Tencent类是SDK的主要实现类，开发者可通过Tencent类访问腾讯开放的OpenAPI。
        // 其中APP_ID是分配给第三方应用的appid，类型为String。
        mTencent = Tencent.createInstance(APP_ID, mActivity.getApplicationContext());
        initListener();
    }

    private void initListener() {
        mUiListener = new IUiListener() {
            @Override
            public void onComplete(Object o) {

            }

            @Override
            public void onError(UiError uiError) {

            }

            @Override
            public void onCancel() {

            }
        };
    }

    public void loginTencent()
    {
        mTencent = Tencent.createInstance(APP_ID, mActivity.getApplicationContext());
        if (!mTencent.isSessionValid())
        {
            mTencent.login(mActivity, "get_simple_userinfo", mUiListener);
        }
    }

    public void getUserInfo()
    {
//        mTencent.requestAsync(Constants.GRAPH_SIMPLE_USER_INFO, null,
//                Constants.HTTP_GET, new BaseApiListener("get_simple_userinfo", false),
//                null);
    }

    public void releaseTencent()
    {
        mTencent.logout(mActivity);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Tencent.onActivityResultData(requestCode, resultCode, data, mUiListener);
    }

}
