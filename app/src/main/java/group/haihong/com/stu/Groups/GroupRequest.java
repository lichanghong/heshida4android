package group.haihong.com.stu.Groups;

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
 * Created by lichanghong on 12/27/15.
 */
public class GroupRequest {
    Context context;

    public void loadGroupNames(GroupResponse response)
    {
            loadGroupNames_(response);
    }

    public  void loadGroupDetail(GroupResponse response,HashMap<String,String> params)
    {
            loadGroupDetail_(response, params);
    }

    private void loadGroupDetail_(final GroupResponse response,HashMap<String,String>params)
    {
        String url = HttpUtil.loadGroupDetail(context)+HttpUtil.getUrl(params);
        HttpUtil.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                super.onSuccess(jsonObject);
                try {
                    loadDetail(response, jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Throwable throwable, JSONObject jsonObject) {
                super.onFailure(throwable, jsonObject);
                try {
                    loadDetail(response, jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void loadDetail(GroupResponse response,JSONObject jsonObject) throws JSONException {
        String errno = "", errmsg = "";
        errmsg = jsonObject.getString("errmsg");
        errno = jsonObject.getString("errno");
        JSONObject obj =(JSONObject) jsonObject.getJSONObject("societyname");

        GroupModel model = new GroupModel();
        model.setSid(obj.getString("sid"));
        model.setGroupname(obj.getString("groupname"));
        model.setDescription(obj.getString("description"));

        Response response1 = new Response(errno, errmsg,"");
        response1.setData(model);
        response.loadGroupDetail(response1);
    }

    private void loadGroupNames_(final GroupResponse response) {
        String url = HttpUtil.loadGroupNames(context);
        HttpUtil.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                super.onSuccess(jsonObject);
                try {
                    loadNames(response, jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void handleFailureMessage(Throwable throwable, String s) {
                super.handleFailureMessage(throwable, s);
                JSONObject obj = new JSONObject();
                Response response1 = new Response("1", "网络连接失败","");
                response.loadGroupNames(response1);

            }

            @Override
            public void onFailure(Throwable throwable, JSONObject jsonObject) {
                super.onFailure(throwable, jsonObject);
                try {
                    loadNames(response, jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private void loadNames(final GroupResponse response,JSONObject jsonObject) throws JSONException {
        String errno = "", errmsg = "";
            errmsg = jsonObject.getString("errmsg");
            errno = jsonObject.getString("errno");
            JSONArray datas =(JSONArray) jsonObject.getJSONArray("societyname");
            List<GroupModel> models = new ArrayList<>();
            for (int i = 0; i < datas.length(); i++) {
                GroupModel model = new GroupModel();
                JSONObject obj = (JSONObject) datas.get(i);
                model.setSid(obj.getString("sid"));
                model.setGroupname(obj.getString("groupname"));
                models.add(model);
            }

            Response response1 = new Response(errno, errmsg,models);
            response.loadGroupNames(response1);
    }





















}
