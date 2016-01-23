package group.haihong.com.stu.Hot;

import android.content.Context;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import group.haihong.com.stu.Utils.Http.HttpUtil;
import group.haihong.com.stu.Utils.net.Response;

/**
 * Created by lichanghong on 12/26/15.
 */
public class HotRequest {
    Context context;

    public void loadHotData(HotResponse response,HashMap<String,String> params ) {
        loadHotData_(response, params);
    }

    public void reportFakeMessage(HotResponse response,HashMap<String,String> params)
    {
        reportFakeMessage_(response, params);
    }



    public void saveHotData(HotResponse response,HashMap<String,String> params ) {
        saveHotData_(response, params);
    }

    private void saveHotData_(final HotResponse response,HashMap<String,String> params)
    {
        String url = HttpUtil.saveHotDataURL(context)+HttpUtil.getUrl(params);
        HttpUtil.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                super.onSuccess(jsonObject);
                hotresult(response, jsonObject);
            }

            @Override
            public void onFailure(Throwable throwable, JSONObject jsonObject) {
                super.onFailure(throwable, jsonObject);
                hotresult(response, jsonObject);
            }
        });
    }


    private void loadHotData_(final HotResponse response,HashMap<String,String> params)
    {
        String url = HttpUtil.getHotDataURL(context)+HttpUtil.getUrl(params);
        HttpUtil.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                super.onSuccess(jsonObject);
                loadhotresult(response, jsonObject);
            }
            @Override
            protected void handleFailureMessage(Throwable throwable, String s) {
                super.handleFailureMessage(throwable, s);
                Response response1 = new Response("1", "网络连接失败","");
                response.loadHotData(response1);

            }
            @Override
            public void onFailure(Throwable throwable, JSONObject jsonObject) {
                super.onFailure(throwable, jsonObject);
                loadhotresult(response, jsonObject);
            }
        });
    }

    private void loadhotresult(final HotResponse response,JSONObject jsonObject)
    {
        String errno = "", errmsg = "";
        try {
            errmsg = jsonObject.getString("errmsg");
            errno = jsonObject.getString("errno");
            JSONArray datas =(JSONArray) jsonObject.getJSONArray("data");
            List<HotModel> models = new ArrayList<>();
            for (int i = 0; i < datas.length(); i++) {
                HotModel model = new HotModel();
                JSONObject obj = (JSONObject) datas.get(i);
                model.setLostid(obj.getString("lostid"));
                model.setTitle(obj.getString("title"));
                model.setBlocked(obj.getString("blocked"));
                model.setFoundtime(obj.getString("foundtime"));
                model.setLocation(obj.getString("location"));
                model.setLine(obj.getString("line"));
                model.setLosted(obj.getString("losted"));
                if (model.getBlocked().equals("0")) {
                    models.add(model);
                }
            }

            Response response1 = new Response(errno, errmsg,models);
            response.loadHotData(response1);

        } catch (JSONException e) {
            e.printStackTrace();
            Response response1 = new Response("1", e.getLocalizedMessage(),"");
            response.loadHotData(response1);
        }
    }

    private void hotresult(final HotResponse response,JSONObject jsonObject)
    {
        String errno = "", errmsg = "";
        try {
            errmsg = jsonObject.getString("errmsg");
            errno = jsonObject.getString("errno");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Response response1 = new Response(errno, errmsg,"");
        response.saveHotData(response1);
    }

    private void reportFakeMessage_(final HotResponse response, HashMap<String,String> params)
    {
        String url = HttpUtil.reportFakeMessage(context)+HttpUtil.getUrl(params);
        HttpUtil.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                super.onSuccess(jsonObject);
                reportFakeMessageResult(response, jsonObject);
            }

            @Override
            protected void handleFailureMessage(Throwable throwable, String s) {
                super.handleFailureMessage(throwable, s);
                JSONObject obj = new JSONObject();
                Response response1 = new Response("1", "网络连接失败","");
                response.reportFakeMessage(response1);

            }

            @Override
            public void onFailure(Throwable throwable, JSONObject jsonObject) {
                super.onFailure(throwable, jsonObject);
                reportFakeMessageResult(response, jsonObject);
            }
        });
    }

    private void reportFakeMessageResult(final HotResponse response,JSONObject jsonObject)
    {
        String errno = "", errmsg = "";
        try {
            errmsg = jsonObject.getString("errmsg");
            errno = jsonObject.getString("errno");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Response response1 = new Response(errno, errmsg,"");
        response.reportFakeMessage(response1);
    }

}
