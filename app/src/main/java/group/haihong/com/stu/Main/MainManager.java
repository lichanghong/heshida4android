package group.haihong.com.stu.Main;

import android.content.Context;

/**
 * Created by lichanghong on 1/1/16.
 */
public class MainManager {
    Context context;

    public MainManager(Context context) {
        this.context = context;
    }

    private static MainRequest mainRequest = new MainRequest();

    public static void loadMainData(Context context, MainResponse response)
    {
        mainRequest.context = context;
        mainRequest.loadMainData(response);
    }

}
