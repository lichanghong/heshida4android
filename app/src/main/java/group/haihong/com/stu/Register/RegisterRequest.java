package group.haihong.com.stu.Register;

import android.content.Context;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import group.haihong.com.stu.Utils.Http.HttpUtil;
import group.haihong.com.stu.Utils.net.Response;

/**
 * Created by lichanghong on 1/24/16.
 */
public class RegisterRequest {
    Context mContext;

    public RegisterRequest() {

    }

    public void register(RegisterResponse response,HashMap<String, String> params){
        this.userRegister(response,params);
    }

    private void userRegister(final RegisterResponse response,HashMap<String, String> params)
    {
        String url = HttpUtil.getRegisterURL(mContext)+HttpUtil.getUrl(params);
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

    private void processresult(RegisterResponse response,JSONObject jsonObject)
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
        response.register(response1);
    }



}
