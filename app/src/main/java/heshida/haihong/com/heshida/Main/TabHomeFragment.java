package heshida.haihong.com.heshida.Main;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import cn.domob.android.ads.AdEventListener;
import cn.domob.android.ads.AdManager;
import cn.domob.android.ads.AdView;
import heshida.haihong.com.heshida.AD.ADManager;
import heshida.haihong.com.heshida.AD.YouMiADManager;
import heshida.haihong.com.heshida.R;
import heshida.haihong.com.heshida.Utils.Location.LocationManager;

/**
 * Created by admin on 13-11-23.
 */
public class TabHomeFragment extends Fragment {

    View _view;
    //    LocationManager mLocationManager;
    WebView _mWebView;


    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        Log.i("lifetime---", "startActivity-----------------------");

    }

    @Override
    public void onInflate(Context context, AttributeSet attrs, Bundle savedInstanceState) {
        super.onInflate(context, attrs, savedInstanceState);
        Log.i("lifetime---", "onInflate-----------------------");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = null;
      if (_view == null) {
          view = inflater.inflate(R.layout.fragment_home, null);
          _view = view;
          initView();
          initData();
          Log.i("lifetime---", "oncreateview-----------------------");
      }
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        //webview
        _mWebView.getSettings().setJavaScriptEnabled(true);
        _mWebView.loadUrl("http://www.henannu.edu.cn/");
    }

    private void initData() {
        //多盟广告
        ADManager adManager = new ADManager(getActivity());
        adManager.showADView(_view);
        //有米广告
        YouMiADManager youMiADManager = new YouMiADManager(getActivity());
        youMiADManager.showYOUMIAdView(_view);


        //定位
//        mLocationManager = new LocationManager(getActivity().getApplicationContext());
//        mLocationManager.start();

    }

    private void initView() {
        _mWebView = (WebView) _view.findViewById(R.id.home_webView);
        initListener();
    }

    private void initListener() {
//            Button button =(Button) _view.findViewById(R.id.home_button_joke);
//            button.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(_view.getContext(),RegisterActivity.class);
//                    startActivity(intent);
////                    Toast.makeText(v.getContext(),"show joke",Toast.LENGTH_LONG).show();
//                }
//            });
    }




}
