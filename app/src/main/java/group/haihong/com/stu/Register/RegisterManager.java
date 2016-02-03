package group.haihong.com.stu.Register;

import android.content.Context;

import java.util.HashMap;

/**
 * Created by lichanghong on 1/24/16.
 */
public class RegisterManager {
    Context mContext;

    public RegisterManager(Context mContext) {
        this.mContext = mContext;
    }

    private static RegisterRequest request = new RegisterRequest();
    public static void register(Context context,HashMap<String, String> param,RegisterResponse response)
    {
        request.mContext = context;
        request.register(response, param);
    }
}



