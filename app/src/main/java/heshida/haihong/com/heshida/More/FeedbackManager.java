package heshida.haihong.com.heshida.More;

import android.content.Context;

import heshida.haihong.com.heshida.Utils.net.Response;

/**
 * Created by lichanghong on 12/24/15.
 */
public class FeedbackManager {
    Context context;

    public FeedbackManager(Context context) {
        this.context = context;
    }

    private static FeedbackRequest manager = new FeedbackRequest();

    public static void feedback(Context context,String param,FeedbackResponse response)
    {
        manager.context = context;
        manager.feedback(response,param);
    }



}
