package heshida.haihong.com.heshida.Utils.Http;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.HashMap;
import java.util.Iterator;

import heshida.haihong.com.heshida.R;

/**
 * Created by lichanghong on 12/6/15.
 */
public class HttpUtil {
    private static AsyncHttpClient client =new AsyncHttpClient();    //实例话对象
    static
    {
        client.setTimeout(5000);   //设置链接超时，如果不设置，默认为10s
    }
    public static void get(String urlString,AsyncHttpResponseHandler res)    //用一个完整url获取一个string对象
    {
        client.get(urlString, res);
    }
    public static void get(String urlString,RequestParams params,AsyncHttpResponseHandler res)   //url里面带参数
    {
        client.get(urlString, params,res);
    }
    public static void get(String urlString,JsonHttpResponseHandler res)   //不带参数，获取json对象或者数组
    {
        client.get(urlString, res);
    }
    public static void get(String urlString,RequestParams params,JsonHttpResponseHandler res)   //带参数，获取json对象或者数组
    {
        client.get(urlString, params,res);
    }
    public static void get(String uString, BinaryHttpResponseHandler bHandler)   //下载数据使用，会返回byte数据
    {
        client.get(uString, bHandler);
    }
    public static AsyncHttpClient getClient()
    {
        return client;
    }

    //------------------- url --
    public static String getHostname(Context context)
    {
        return context.getString(R.string.hostname);
    }
    public static String getFeedbackURL(Context context)
    {
        return getHostname(context)+context.getString(R.string.feedback);
    }

    public static String saveHotDataURL(Context context)
    {
        return getHostname(context)+context.getString(R.string.savelostfound);
    }
    public static String getHotDataURL(Context context)
    {
        return  "http://heshidatest.applinzi.com"+"/admin.php/getlostfound";

//        return getHostname(context)+context.getString(R.string.getlostfound);
    }
    public static String loadGroupNames(Context context)
    {
        return getHostname(context)+"/user/societynames";
    }

    public static String loadGroupDetail(Context context)
    {
        return getHostname(context)+"/user/societydetail";
    }

    public static String reportFakeMessage(Context context)
    {
        return getHostname(context)+"/user/lostfoundblock";
    }
    public static String loadMainData(Context context)
    {
        return getHostname(context)+"/user/getheshidapage";
    }

    public static String  getUrl(HashMap<String, String> params) {
         // 添加url参数
        StringBuilder stringBuilder = new StringBuilder();
        if (params != null) {
            Iterator<String> it = params.keySet().iterator();
            StringBuffer sb = null;
            while (it.hasNext()) {
                String key = it.next();
                String value = params.get(key);
                if (sb == null) {
                    sb = new StringBuffer();
                    sb.append("?");
                } else {
                    sb.append("&");
                }
                sb.append(key);
                sb.append("=");
                sb.append(value);
            }
            stringBuilder.append(sb.toString());
        }
        return stringBuilder.toString();
    }
}
