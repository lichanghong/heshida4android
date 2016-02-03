package group.haihong.com.stu.Register;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import group.haihong.com.stu.R;
import group.haihong.com.stu.TopBar;
import group.haihong.com.stu.Utils.AESCipher;
import group.haihong.com.stu.Utils.net.Response;
import group.haihong.com.stu.Vendor.StuConstants;
import group.haihong.com.stu.home.MyHomeActivity;

public class RegisterActivity extends Activity {
    TopBar mTopBar;
    Button mCancleButton, mRegisterButton;
    EditText mPhone, mPwd, mConfirmPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_register);
        initView();
        initData();
    }

    private void initData() {
        mTopBar.setTitle("注册");
    }

    private void initView() {
        mTopBar = (TopBar) findViewById(R.id.topbarRegister);
        mTopBar.setTitleTextColor(Color.WHITE);
        mCancleButton = (Button) findViewById(R.id.btnCancel);
        mRegisterButton = (Button) findViewById(R.id.btnRegister);
        mPhone = (EditText) findViewById(R.id.register_phone);
        mPwd = (EditText) findViewById(R.id.register_pwd);
        mConfirmPwd = (EditText) findViewById(R.id.register_confirm_pwd);
    }

    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    public void handleAction(View view) {
        if (view.getId() == mCancleButton.getId()) {
            this.finish();
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        } else if (view.getId() == mRegisterButton.getId()) {
            /**隐藏软键盘**/
            if (getCurrentFocus() != null) {
                ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                        .hideSoftInputFromWindow(getCurrentFocus()
                                        .getWindowToken(),
                                InputMethodManager.HIDE_NOT_ALWAYS);
            }

            String phone, pwd, confirmpwd;
            phone = mPhone.getText().toString();
            pwd = mPwd.getText().toString();
            confirmpwd = mConfirmPwd.getText().toString();

            if (phone.isEmpty() || !isMobileNO(phone)) {
                mPhone.setError("请正确输入手机号");
                return;
            }
            if (pwd.isEmpty()) {
                mPwd.setError("请设置您的密码");
                return;
            }
            if (confirmpwd.isEmpty()) {
                mConfirmPwd.setError("请确认密码");
                return;
            }
            if (!pwd.equals(confirmpwd)) {
                mConfirmPwd.setError("两次输入密码不一样");
                return;
            }
            String encryptpwd = null;
            try {
                encryptpwd = AESCipher.encrypt(StuConstants.AESKey, pwd).toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("phone", phone);
            params.put("password", encryptpwd);
            params.put("registertime", String.valueOf(System.currentTimeMillis()));
            final ProgressDialog proDialog = android.app.ProgressDialog.show(RegisterActivity.this, "", "注册中...");
            RegisterManager.register(RegisterActivity.this,params,new RegisterResponse(){
                @Override
                public void register(Response response) {
                    super.register(response);
                    if (response.getErrno().equals("0")) {
                        Toast.makeText(RegisterActivity.this, "恭喜您！注册成功", Toast.LENGTH_LONG).show();
                        finish();
                        Intent intent = new Intent(RegisterActivity.this,MyHomeActivity.class);
                        startActivity(intent);
                        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.fade_out);
                     } else {
                            Toast.makeText(RegisterActivity.this, response.getErrmsg(), Toast.LENGTH_LONG).show();
                    }
                        proDialog.cancel();
                }
            });
        }

    }
}
