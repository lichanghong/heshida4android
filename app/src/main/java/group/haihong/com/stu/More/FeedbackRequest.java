package group.haihong.com.stu.More;

import android.content.Context;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import group.haihong.com.stu.Utils.Http.HttpUtil;
import group.haihong.com.stu.Utils.net.Response;

/**
 * Created by lichanghong on 12/23/15.
 */
public class FeedbackRequest {
    Context context;

    public void FeedbackContext(Context context) {
        this.context = context;
    }

    public void feedback(FeedbackResponse response,String param)
    {
        this.feedback_(response,param);
    }

    private void feedback_(final FeedbackResponse response,String param)
    {
        String url = HttpUtil.getFeedbackURL(context)+"?content="+param;
        HttpUtil.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                super.onSuccess(jsonObject);
                processresult(response,jsonObject);
            }

            @Override
            public void onFailure(Throwable throwable, JSONObject jsonObject) {
                super.onFailure(throwable, jsonObject);
               processresult(response,jsonObject);
            }
        });
    }

    private void processresult(final FeedbackResponse response,JSONObject jsonObject)
    {
        String errno = "", errmsg = "";
        try {
            errmsg = jsonObject.getString("errmsg");
            errno = jsonObject.getString("errno");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Response response1 = new Response(errno, errmsg,"");
        response.feedBack(response1);
    }

}
