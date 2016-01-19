package heshida.haihong.com.heshida.Utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import heshida.haihong.com.heshida.R;

/**
 * Created by lichanghong on 1/20/16.
 */
public class VersionManager {
    Activity mActivity;

    public VersionManager(Activity mActivity) {
        this.mActivity = mActivity;
    }

    private static VersionManager sSingleton = null;

    public static synchronized VersionManager getInstance(Activity mActivity) {
        if (sSingleton == null) {
            sSingleton = new VersionManager(mActivity);
        }
        return sSingleton;
    }

    /* 下载中 */
    private static final int DOWNLOAD = 1;
    /* 下载结束 */
    private static final int DOWNLOAD_FINISH = 2;
    /* 保存解析的XML信息 */
    HashMap<String, String> mHashMap;
    /* 下载保存路径 */
    private String mSavePath;
    /* 记录进度条数量 */
    private int progress;
    /* 是否取消更新 */
    private boolean cancelUpdate = false;
    /* 更新进度条 */
    private ProgressBar mProgress;
    private Dialog mDownloadDialog;

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                // 正在下载
                case DOWNLOAD:
                    // 设置进度条位置
                    mProgress.setProgress(progress);
                    break;
                case DOWNLOAD_FINISH:
                    // 安装文件
                    installApk();
                    break;
                default:
                    break;
            }
        }

        ;
    };

    /**
     * 检测软件更新
     */
    public void checkUpdate() {
        if (isUpdate()) {
            // 显示提示对话框
            showNoticeDialog();
        } else {
            Toast.makeText(mActivity.getApplicationContext(), "已经是最新版本", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 检查软件是否有更新版本
     *
     * @return
     */
    private boolean isUpdate() {
//            // 获取当前软件版本
            int versionCode = getVersionCode(mActivity);
            // 把version.xml放到网络上，然后获取文件信息
//            InputStream inStream = ParseXmlService.class.getClassLoader().getResourceAsStream("version.xml");
//            // 解析XML文件。 由于XML文件比较小，因此使用DOM方式进行解析
//            ParseXmlService service = new ParseXmlService();
//            try
//            {
//                mHashMap = service.parseXml(inStream);
//            } catch (Exception e)
//            {
//                e.printStackTrace();
//            }
//            if (null != mHashMap)
//            {
//                int serviceCode = Integer.valueOf(mHashMap.get("version"));
//                // 版本判断
//                if (serviceCode > versionCode)
//                {
                    return true;
//                }
//            }
//        return false;
    }

    /**
     * 获取软件版本号
     *
     * @param context
     * @return
     */
    private int getVersionCode(Context context) {
        int versionCode = 0;
        try {
            // 获取软件版本号，对应AndroidManifest.xml下android:versionCode
            versionCode = context.getPackageManager().getPackageInfo("heshida.haihong.com.heshida", 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * 显示软件更新对话框
     */
    private void showNoticeDialog() {
        // 构造对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setTitle("河南师范可以升级了");
       // builder.setMessage("升级应用");
        // 更新
        builder.setPositiveButton("马上升级", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                // 显示下载对话框
                showDownloadDialog();
            }
        });
        // 稍后更新
        builder.setNegativeButton("稍后升级", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        Dialog noticeDialog = builder.create();
        noticeDialog.show();
    }

    /**
     * 显示软件下载对话框
     */
    private void showDownloadDialog() {
        // 构造软件下载对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setTitle("正在更新");
        // 给下载对话框增加进度条
        final LayoutInflater inflater = LayoutInflater.from(mActivity);
        View v = inflater.inflate(R.layout.softupdate_progress, null);
        mProgress = (ProgressBar) v.findViewById(R.id.update_progress);
        builder.setView(v);
        // 取消更新
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                // 设置取消状态
                cancelUpdate = true;
            }
        });
        mDownloadDialog = builder.create();
        mDownloadDialog.show();
        // 现在文件
        downloadApk();
    }

    /**
     * 下载apk文件
     */
    private void downloadApk() {
        // 启动新线程下载软件
        new downloadApkThread().start();
    }

    /**
     * 下载文件线程
     *
     * @author coolszy
     * @date 2012-4-26
     * @blog http://blog.92coding.com
     */
    private class downloadApkThread extends Thread {
        @Override
        public void run() {
//            try {
//                // 判断SD卡是否存在，并且是否具有读写权限
//                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
//                    // 获得存储卡的路径
//                    String sdpath = Environment.getExternalStorageDirectory() + "/";
//                    mSavePath = sdpath + "download";
//                    URL url = new URL(mHashMap.get("url"));
//                    // 创建连接
//                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                    conn.connect();
//                    // 获取文件大小
//                    int length = conn.getContentLength();
//                    // 创建输入流
//                    InputStream is = conn.getInputStream();
//
//                    File file = new File(mSavePath);
//                    // 判断文件目录是否存在
//                    if (!file.exists()) {
//                        file.mkdir();
//                    }
//                    File apkFile = new File(mSavePath, mHashMap.get("name"));
//                    FileOutputStream fos = new FileOutputStream(apkFile);
//                    int count = 0;
//                    // 缓存
//                    byte buf[] = new byte[1024];
//                    // 写入到文件中
//                    do {
//                        int numread = is.read(buf);
//                        count += numread;
//                        // 计算进度条位置
//                        progress = (int) (((float) count / length) * 100);
//                        // 更新进度
//                        mHandler.sendEmptyMessage(DOWNLOAD);
//                        if (numread <= 0) {
//                            // 下载完成
//                            mHandler.sendEmptyMessage(DOWNLOAD_FINISH);
//                            break;
//                        }
//                        // 写入文件
//                        fos.write(buf, 0, numread);
//                    } while (!cancelUpdate);// 点击取消就停止下载.
//                    fos.close();
//                    is.close();
//                }
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            // 取消下载对话框显示
            mDownloadDialog.dismiss();
        }
    }

    ;

    /**
     * 安装APK文件
     */
    private void installApk() {
        File apkfile = new File(mSavePath, mHashMap.get("name"));
        if (!apkfile.exists()) {
            return;
        }
        // 通过Intent安装APK文件
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setDataAndType(Uri.parse("file://" + apkfile.toString()), "application/vnd.android.package-archive");
        mActivity.getApplication().startActivity(i);
    }

    /*
 * 获取当前程序的版本号
 */
    private String getVersionName() throws Exception {
        //获取packagemanager的实例
        PackageManager packageManager = mActivity.getPackageManager();
        //getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(mActivity.getPackageName(), 0);
        return packInfo.versionName;
    }
}