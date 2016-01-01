package heshida.haihong.com.heshida.Main;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Toast;

import cn.domob.android.ads.AdManager;
import heshida.haihong.com.heshida.AD.ADManager;
import heshida.haihong.com.heshida.AD.DuomengADController;
import heshida.haihong.com.heshida.AD.YouMiADController;
import heshida.haihong.com.heshida.R;
import heshida.haihong.com.heshida.Utils.net.Response;

/**
 * Created by admin on 13-11-23.
 */
public class TabHomeFragment extends Fragment {
    private static final String OpenDuoMengADCode  = "1451576100";
    private static final String OpenYouMiADCode    = "1451576200";
    boolean hasAD = false;
    View _view;
    //    LocationManager mLocationManager;
    WebView _mWebView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (null != _view) {
            ViewGroup parent = (ViewGroup) _view.getParent();
            if (null != parent) {
                parent.removeView(_view);
            }
        } else {
            _view = inflater.inflate(R.layout.fragment_home, null);
            initView();
            if (!hasAD)
            {
                initData();
            }
        }
        return _view;
      }

    @Override
    public void onStart() {
        super.onStart();
        if (!hasAD)
        {
            initData();
            //webview
            _mWebView.getSettings().setJavaScriptEnabled(true);
            _mWebView.loadUrl("http://www.henannu.edu.cn/");
        }
    }

    private void initData() {
        MainManager.loadMainData(_view.getContext(),new MainResponse(){
            @Override
            public void loadMainData(Response response) {
                super.loadMainData(response);
                if (response.getErrno().equals("0")) {
                    ADManager adManager = ADManager.getInstance(_view.getContext(), getActivity());
                    String responseTime = response.getTime().toString();
                    String adTime       = OpenYouMiADCode.toString();
                    if (responseTime.equals(adTime))
                    {
                        adManager.showYouMiAD(_view);
                        hasAD = true;
                    }
                    else if (response.getTime().toString().equals(OpenDuoMengADCode.toString()))
                    {
                        adManager.showDuoMengAD(_view);
                        hasAD = true;
                    }
                    else  hasAD = false;
                } else {
                    Toast.makeText(getActivity(), response.getErrmsg(), Toast.LENGTH_LONG).show();
                }
            }
        });



//         //多盟广告
//        DuomengADController duomengAdController = new DuomengADController(getActivity());
//        duomengAdController.showADView(_view);
//        //有米广告
//        YouMiADController youMiADController = new YouMiADController(getActivity());
//        youMiADController.showYOUMIAdView(_view);


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
