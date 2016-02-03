package group.haihong.com.stu.Register.Login;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Timestamp;
import java.util.HashMap;

import group.haihong.com.stu.R;
import group.haihong.com.stu.Register.RegisterActivity;
import group.haihong.com.stu.TopBar;
import group.haihong.com.stu.Utils.AESCipher;
import group.haihong.com.stu.Utils.net.Response;
import group.haihong.com.stu.Vendor.StuConstants;
import group.haihong.com.stu.home.MyHomeActivity;

public class LoginActivity extends Activity {

    TopBar mTopBar;
    Button mLoginBtn,mRegisterBtn;
    EditText mPhoneText,mPwdText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        initView();
        initData();
        initListener();
    }

    private void initListener() {
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
//                 long timeMillis = System.currentTimeMillis();
//                 Timestamp now = new Timestamp(timeMillis);//获取系统当前时间
//                 mPhoneText.setText(now.toString());
                 String phone = mPhoneText.getText().toString().trim();
                 String pwd = mPwdText.getText().toString().trim();

                 if (phone.isEmpty() || !RegisterActivity.isMobileNO(phone)) {
                     mPhoneText.setError("请正确输入手机号登陆");
                     return;
                 }
                 if (pwd.isEmpty() || pwd.length() < 6) {
                     mPwdText.setError("密码长度至少为6位");
                     return;
                 }
                 try {
                     String decrypt = AESCipher.encrypt(StuConstants.AESKey,pwd).toString();
                     loginWith(phone, decrypt);
                 } catch (Exception e) {
                     // TODO Auto-generated catch block
                     e.printStackTrace();
                     Toast.makeText(LoginActivity.this,
                             "系统异常,请反馈给我们", Toast.LENGTH_LONG).show();
                 }

             }
         });

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.fade_out);
            }
        });
    }

    private void loginWith(String phone,String encryptpwd)
    {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("phone", phone);
        params.put("password", encryptpwd);
        final ProgressDialog proDialog = android.app.ProgressDialog.show(LoginActivity.this, "", "登陆中...");
        LoginManager.login(LoginActivity.this, params, new LoginResponse() {
            @Override
            public void login(Response response) {
                super.login(response);
                if (response.getErrno().equals("0")) {
                    Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_LONG).show();
                    loginSuccess();
                } else {
                    Toast.makeText(LoginActivity.this, response.getErrmsg(), Toast.LENGTH_LONG).show();
                }
                proDialog.cancel();
            }
        });
    }

    private  void loginSuccess()
    {
        Intent intent = new Intent(LoginActivity.this,MyHomeActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.fade_out);
        finish();
    }

    private void initData() {
        mTopBar.setTitle("登陆");
    }

    private void initView() {
        mTopBar = (TopBar) findViewById(R.id.topbarRegister);
        mTopBar.getLeftButton().setEnabled(true);
        mTopBar.setTitleTextColor(Color.WHITE);
        mTopBar.setLeftBackground(R.drawable.backbutton);
        mTopBar.setOnTopbarLeftClickListener(new TopBar.TopbarLeftClickListener() {
            @Override
            public void leftClick() {
                finish();
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        });

        mLoginBtn = (Button) findViewById(R.id.login_loginBtn);
        mRegisterBtn = (Button) findViewById(R.id.login_registerBtn);
        mPhoneText = (EditText) findViewById(R.id.login_phone);
        mPwdText = (EditText) findViewById(R.id.login_pwd);
    }
}
