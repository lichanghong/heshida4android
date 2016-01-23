package group.haihong.com.stu.Hot;

import android.content.Context;
import java.util.HashMap;

/**
 * Created by lichanghong on 12/26/15.
 */
public class HotManager {

    Context context;

    public HotManager(Context context) {
        this.context = context;
    }

    private static HotRequest hotRequest = new HotRequest();

    public static void loadHotData(Context context,HashMap<String,String> params,HotResponse response)
    {
        hotRequest.context = context;
        hotRequest.loadHotData(response,params);
    }

    public static void saveHotData(Context context,HashMap<String,String> params,HotResponse response)
    {
        hotRequest.context = context;
        hotRequest.saveHotData(response, params);
    }

    public static void reportFakeMessage(Context context,HashMap<String,String> params,HotResponse response)
    {
        hotRequest.context = context;
        hotRequest.reportFakeMessage(response,params);
    }

}
