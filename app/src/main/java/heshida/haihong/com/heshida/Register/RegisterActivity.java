package heshida.haihong.com.heshida.Register;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import heshida.haihong.com.heshida.R;
import heshida.haihong.com.heshida.TopBar;

public class RegisterActivity extends Activity {
    TopBar mTopBar;
    Button mCancleButton,mRegisterButton;
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
        mCancleButton = (Button) findViewById(R.id.btnCancel);
        mRegisterButton = (Button) findViewById(R.id.btnRegister);

    }

    public void handleAction(View view)
    {
        if (view.getId() == mCancleButton.getId()) {
            this.finish();
        }
        else if (view.getId() == mRegisterButton.getId())
        {
            /**隐藏软键盘**/
            if(getCurrentFocus()!=null)
            {
                ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                        .hideSoftInputFromWindow(getCurrentFocus()
                                        .getWindowToken(),
                                InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
