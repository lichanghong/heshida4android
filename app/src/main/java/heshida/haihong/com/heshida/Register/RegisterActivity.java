package heshida.haihong.com.heshida.Register;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import heshida.haihong.com.heshida.R;
import heshida.haihong.com.heshida.TopBar;
import heshida.haihong.com.heshida.home.MyHomeActivity;

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

            if (phone.isEmpty() && isMobileNO(phone)) {
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
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("phone", phone);
            params.put("password", pwd);
            final ProgressDialog proDialog = android.app.ProgressDialog.show(RegisterActivity.this, "", "注册中...");
//                    HotManager.saveHotData(FoundActivity.this, params, new HotResponse() {
//                        @Override
//                        public void saveHotData(Response response) {
//                            super.saveHotData(response);
//                            if (response.getErrno().equals("0")) {
//                                Toast.makeText(FoundActivity.this, "上传成功,失主会非常感激您的~", Toast.LENGTH_LONG).show();
//                                proDialog.dismiss();
                                finish();
            Intent intent = new Intent(RegisterActivity.this,MyHomeActivity.class);
            startActivity(intent);
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.fade_out);

//                                overridePendingTransition(android.R.anim.slide_out_right, android.R.anim.slide_in_left);
//
//                            } else {
//                                Toast.makeText(FoundActivity.this, response.getErrmsg(), Toast.LENGTH_LONG).show();
//                            }
//                            proDialog.cancel();
//
//    });
        }

    }
}
