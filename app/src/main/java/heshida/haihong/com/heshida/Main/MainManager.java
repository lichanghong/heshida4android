package heshida.haihong.com.heshida.Main;

import android.content.Context;

import java.util.HashMap;

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
