package heshida.haihong.com.heshida.Utils.Http;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.util.Log;

/**
 * Created by lichanghong on 12/6/15.
 */
public class Reachability {
    private static final String TAG = "reachability";

    public static void checkNetWorkInfo(final Context context) {
        if (!isNetworkAvailable(context)) {
            new AlertDialog.Builder(context)
                    .setTitle("提示!")
                    .setIcon(android.R.drawable.ic_dialog_info)
                    .setMessage("检测到你还没开启网络，请开启")
                    .setNegativeButton("取消", null)
                    .setPositiveButton("开启",
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    context.startActivity(new Intent(
                                            Settings.ACTION_WIRELESS_SETTINGS));// 进入无线网络配置界面
                                    // startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS)); // 进入手机中的wifi网络设置界面
                                }
                            }).show();
        }
    }

    /*
      * 判断是否有网络
      */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null) {
            return false;
        } else {
            // 打印所有的网络状态
            NetworkInfo[] infos = cm.getAllNetworkInfo();
            if (infos != null) {
                for (int i = 0; i < infos.length; i++) {
                    // Log.d(TAG, "isNetworkAvailable - info: " +
                    // infos[i].toString());
                    if (infos[i].getState() == NetworkInfo.State.CONNECTED) {
                        Log.d(TAG, "isNetworkAvailable -  I " + i);
                    }
                }
            }

            // 如果仅仅是用来判断网络连接　　　　　　
            // 则可以使用 cm.getActiveNetworkInfo().isAvailable();
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            if (networkInfo != null) {
                Log.d(TAG,
                        "isNetworkAvailable - 是否有网络： "
                                + networkInfo.isAvailable());
            } else {
                Log.d(TAG, "isNetworkAvailable - 完成没有网络！");
                return false;
            }

            // 1、判断是否有3G网络
            if (networkInfo != null
                    && networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                Log.d(TAG, "isNetworkAvailable - 有3G网络");
                return true;
            } else {
                Log.d(TAG, "isNetworkAvailable - 没有3G网络");
            }

            // 2、判断是否有wifi连接
            if (networkInfo != null
                    && networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                Log.d(TAG, "isNetworkAvailable - 有wifi连接");
                return true;
            } else {
                Log.d(TAG, "isNetworkAvailable - 没有wifi连接");
            }
        }
        return false;
    }

}
