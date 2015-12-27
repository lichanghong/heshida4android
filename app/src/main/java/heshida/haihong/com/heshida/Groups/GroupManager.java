package heshida.haihong.com.heshida.Groups;

import android.content.Context;

import java.util.HashMap;

/**
 * Created by lichanghong on 12/27/15.
 */
public class GroupManager {
    Context context;

    public GroupManager(Context context) {
        this.context = context;
    }

    private static GroupRequest request = new GroupRequest();

    public static void loadGroupNames(Context context,GroupResponse response)
    {
        request.context = context;
        request.loadGroupNames(response);
    }

    public static void loadGroupDetail(Context context,HashMap<String,String> params,GroupResponse response)
    {
        request.context = context;
        request.loadGroupDetail(response,params);
    }


}
