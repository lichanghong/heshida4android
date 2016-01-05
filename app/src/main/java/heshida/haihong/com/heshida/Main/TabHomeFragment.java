package heshida.haihong.com.heshida.Main;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.List;

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
//    WebView _mWebView;
    ScrollView mScrollView;
    private LinearLayout mGallery;

    private int[] mImgIds;
    private LayoutInflater mInflater;

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
//            _mWebView.getSettings().setJavaScriptEnabled(true);
//            _mWebView.loadUrl("http://www.henannu.edu.cn/");
        }
    }

    private void initData() {
        ADManager adManager = ADManager.getInstance(_view.getContext(), getActivity());
        adManager.showBaiduAD(_view);

//        MainManager.loadMainData(_view.getContext(), new MainResponse() {
//            @Override
//            public void loadMainData(Response response) {
//                super.loadMainData(response);
//                if (response.getErrno().equals("0")) {
//                    ADManager adManager = ADManager.getInstance(_view.getContext(), getActivity());
//                    String responseTime = response.getTime().toString();
//                    String adTime = OpenYouMiADCode.toString();
//                    if (responseTime.equals(adTime)) {
//                        adManager.showYouMiAD(_view);
//                        hasAD = true;
//                    } else if (response.getTime().toString().equals(OpenDuoMengADCode.toString())) {
//                        adManager.showDuoMengAD(_view);
//                        hasAD = true;
//                    } else hasAD = false;
//                } else {
//                    Toast.makeText(getActivity(), response.getErrmsg(), Toast.LENGTH_LONG).show();
//                }
//            }
//        });



        //定位
//        mLocationManager = new LocationManager(getActivity().getApplicationContext());
//        mLocationManager.start();

    }

    private void initView() {
//        _mWebView = (WebView) _view.findViewById(R.id.home_webView);
        mInflater = LayoutInflater.from(_view.getContext());
        mScrollView = (ScrollView) _view.findViewById(R.id.mainScrollView);
        mGallery    = (LinearLayout) _view.findViewById(R.id.homegallery);
        mImgIds = new int[] { R.mipmap.ic_launcher, R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher};

        for (int i = 0; i < mImgIds.length; i++)
        {
            View view = mInflater.inflate(R.layout.activity_index_gallery_item, mGallery, false);
            ImageView img = (ImageView) view.findViewById(R.id.id_index_gallery_item_image);
            TextView txt = (TextView) view.findViewById(R.id.id_index_gallery_item_text);

            img.setImageResource(mImgIds[i]);
            txt.setText("some info ");
            mGallery.addView(view);
        }
    }



}
