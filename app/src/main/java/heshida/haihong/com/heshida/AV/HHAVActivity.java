package heshida.haihong.com.heshida.AV;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.lang.reflect.InvocationTargetException;

import heshida.haihong.com.heshida.R;
import heshida.haihong.com.heshida.TopBar;

public class HHAVActivity extends Activity {
    TopBar mTopBar;
    private FrameLayout frameLayout = null;
    private WebView webView = null;
    private WebChromeClient chromeClient = null;
    private View myView = null;
    private WebChromeClient.CustomViewCallback myCallBack = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null)
        {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.activity_hhav);
            mTopBar = (TopBar) findViewById(R.id.hhav_tabbar);
            mTopBar.getLeftButton().setEnabled(true);
            mTopBar.setLeftBackground(R.drawable.backbutton);
            mTopBar.setTitle("师大影院");
            mTopBar.setOnTopbarLeftClickListener(new TopBar.TopbarLeftClickListener() {
                @Override
                public void leftClick() {
                    finish();
                    overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                }
            });

            //--------webview

            frameLayout = (FrameLayout)findViewById(R.id.avframelayout);
            webView = (WebView)findViewById(R.id.av_webview);

            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);


            webView.setWebViewClient(new MyWebviewCient());

            chromeClient = new MyChromeClient();

            webView.setWebChromeClient(chromeClient);
            webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
            webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);

            webView.setHorizontalScrollBarEnabled(false);
            webView.setVerticalScrollBarEnabled(false);

            final String USER_AGENT_STRING = webView.getSettings().getUserAgentString() + "Rong/2.0";
            webView.getSettings().setUserAgentString(USER_AGENT_STRING);
            webView.getSettings().setSupportZoom(true);
            webView.getSettings().setPluginState(WebSettings.PluginState.ON);
            webView.getSettings().setLoadWithOverviewMode(true);
            webView.stopLoading();
            webView.requestFocus();
            webView.loadUrl("http://1.ckplayertest.applinzi.com/demo2.htm");
        }
        else if(savedInstanceState != null){
            webView.restoreState(savedInstanceState);
        }
    }


    @Override
    public void onBackPressed() {
        if(myView == null){
            super.onBackPressed();
        }
        else{
            chromeClient.onHideCustomView();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // TODO Auto-generated method stub
        webView.saveState(outState);
    }

    public class MyWebviewCient extends WebViewClient {
        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view,
                                                          String url) {
            WebResourceResponse response = null;
            response = super.shouldInterceptRequest(view, url);
            return response;
        }

        @Override
        public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
            return super.shouldOverrideKeyEvent(view, event);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }
    }

    public class MyChromeClient extends WebChromeClient{

        @Override
        public void onShowCustomView(View view, CustomViewCallback callback) {
            if(myView != null){
                callback.onCustomViewHidden();
                return;
            }
            frameLayout.removeView(webView);
            frameLayout.addView(view);
            myView = view;
            myCallBack = callback;
        }

        @Override
        public void onHideCustomView() {
            if(myView == null){
                return;
            }
            frameLayout.removeView(myView);
            myView = null;
            frameLayout.addView(webView);
            myCallBack.onCustomViewHidden();
        }

        @Override
        public View getVideoLoadingProgressView() {
            FrameLayout frameLayout = new FrameLayout(getApplicationContext());
            frameLayout.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT));
            return frameLayout;
        }

        @Override
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            // TODO Auto-generated method stub
            Log.d("ZR", consoleMessage.message() + " at " + consoleMessage.sourceId() + ":" + consoleMessage.lineNumber());
            return super.onConsoleMessage(consoleMessage);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        webView.stopLoading();
        try {
            webView.getClass().getMethod("onPause").invoke(webView, (Object[]) null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

//        Toast.makeText(HHAVActivity.this,"onpause",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            webView.getClass().getMethod("onResume").invoke(webView, (Object[]) null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mTopBar = null;
        webView = null;
        chromeClient = null;
        myView = null;
        myCallBack = null;
    }
}
