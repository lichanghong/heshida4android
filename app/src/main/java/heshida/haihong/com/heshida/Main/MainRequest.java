package heshida.haihong.com.heshida.Main;

import android.content.Context;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import heshida.haihong.com.heshida.Utils.Http.HttpUtil;
import heshida.haihong.com.heshida.Utils.net.Response;

/**
 * Created by lichanghong on 1/1/16.
 */
public class MainRequest {
    Context context;

    public void loadMainData(MainResponse response ) {
        loadMainData_(response);
    }

    private void loadMainData_(final MainResponse response)
    {
        String url = HttpUtil.loadMainData(context);
        HttpUtil.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                super.onSuccess(jsonObject);
                loadMainResult(response, jsonObject);
            }

            @Override
            protected void handleFailureMessage(Throwable throwable, String s) {
                super.handleFailureMessage(throwable, s);
                JSONObject obj = new JSONObject();
                Response response1 = new Response("1", "网络连接失败", String.valueOf(System.currentTimeMillis()));
                response.loadMainData(response1);
            }

            @Override
            public void onFailure(Throwable throwable, JSONObject jsonObject) {
                super.onFailure(throwable, jsonObject);
                loadMainResult(response, jsonObject);
            }
        });
    }

    private void loadMainResult(MainResponse response,JSONObject jsonObject)
    {
        String errno = "", errmsg = "",time="";
        try {
            errmsg = jsonObject.getString("errmsg");
            errno = jsonObject.getString("errno");
            time = jsonObject.getString("time");
            JSONObject data =(JSONObject) jsonObject.getJSONObject("data");
            MainModel mainModel = new MainModel();
            mainModel.setImg1(data.getString("img1"));
            mainModel.setImg2(data.getString("img2"));
            mainModel.setImg3(data.getString("img3"));
            JSONArray pagenews = data.getJSONArray("pagenews");
            List<PageNew>pageNews = new ArrayList<>();
            for (int i = 0; i < pagenews.length(); i++) {
                JSONObject obj = (JSONObject) pagenews.get(i);
                PageNew pageNew = new PageNew();
                pageNew.setNewid(obj.getString("newid"));
                pageNew.setTitle(obj.getString("title"));
                pageNews.add(pageNew);
            }
            mainModel.setPageNews(pageNews);
            Response response1 = new Response(errno, errmsg,mainModel);
            response1.setTime(time);
            response.loadMainData(response1);

        } catch (JSONException e) {
            e.printStackTrace();
            JSONObject obj = new JSONObject();
            Response response1 = new Response("1", e.getLocalizedMessage(),"");
            response.loadMainData(response1);
        }
    }
}
