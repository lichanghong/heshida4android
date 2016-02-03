package group.haihong.com.stu.Register.Login;

import android.content.Context;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import group.haihong.com.stu.Register.UserModel;
import group.haihong.com.stu.Utils.Http.HttpUtil;
import group.haihong.com.stu.Utils.net.Response;

/**
 * Created by lichanghong on 1/25/16.
 */
public class LoginRequest {
    Context mContext;

    public LoginRequest() {
    }

    public void login(LoginResponse response,HashMap<String,String>params) {
    login_(response,params);
    }

    private void login_(final LoginResponse response,HashMap<String,String>params)
    {
        String url = HttpUtil.getLoginURL(mContext)+HttpUtil.getUrl(params);
        HttpUtil.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                super.onSuccess(jsonObject);
                processresult(response, jsonObject);
            }

            @Override
            public void onFailure(Throwable throwable, JSONObject jsonObject) {
                super.onFailure(throwable, jsonObject);
                processresult(response, jsonObject);
            }
        });
    }
    private void processresult(LoginResponse response,JSONObject jsonObject)
    {
        String errno = "1", errmsg = "服务器错误";
        JSONObject data=null;
        UserModel model = null;

        try {
            errmsg = jsonObject.getString("errmsg");
            errno = jsonObject.getString("errno");
            if (errno.equals("0"))
            {
                data = jsonObject.getJSONObject("data");
                if (data!=null)
                {
                    model =  UserModel.modelWithJsonObject(data);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Response response1 = new Response(errno, errmsg,model);
        response.login(response1);
    }

}
