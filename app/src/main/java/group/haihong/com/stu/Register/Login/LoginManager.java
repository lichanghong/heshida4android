package group.haihong.com.stu.Register.Login;

import android.content.Context;

import java.util.HashMap;

/**
 * Created by lichanghong on 1/25/16.
 */
public class LoginManager {
    Context mContext;

    public LoginManager(Context mContext) {
        this.mContext = mContext;
    }


    private static LoginRequest request = new LoginRequest();
    public static void login(Context context,HashMap<String,String>params,LoginResponse response)
    {
        request.mContext = context;
        request.login(response,params);
    }
}
