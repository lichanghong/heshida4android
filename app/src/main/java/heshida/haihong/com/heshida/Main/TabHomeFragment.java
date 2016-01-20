package heshida.haihong.com.heshida.Main;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import cn.domob.android.ads.AdManager;
import heshida.haihong.com.heshida.AD.ADManager;
import heshida.haihong.com.heshida.AD.DuomengADController;
import heshida.haihong.com.heshida.AD.YouMiADController;
import heshida.haihong.com.heshida.AV.HHAVActivity;
import heshida.haihong.com.heshida.Comment.CommentSchoolActivity;
import heshida.haihong.com.heshida.R;
import heshida.haihong.com.heshida.Utils.Http.UniversalImageUtil;
import heshida.haihong.com.heshida.Utils.VersionManager;
import heshida.haihong.com.heshida.Utils.net.Response;

/**
 * Created by admin on 13-11-23.
 */
public class TabHomeFragment extends Fragment {
    private static final String OpenYouMiADCode    = "1451576200";
    boolean hasAD = false;
    View _view;
    Button mWebSchoolBtn,mCommentSchoolBtn,mSecondhandBtn;
    //    LocationManager mLocationManager;
    ScrollView mScrollView;
    private LinearLayout mGallery;

    private LayoutInflater mInflater;
    List<String> mURLs;
    UniversalImageUtil mUniversalImageUtil;
    List<PageNew> mPageNews;

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
        }
    }

    private void initData() {
        ADManager adManager = ADManager.getInstance(_view.getContext(), getActivity());
        adManager.showQHAD(_view);
        mURLs = new ArrayList<>();
        mUniversalImageUtil = new UniversalImageUtil(_view.getContext(),getActivity());
        mUniversalImageUtil.configImageLoader();
        MainManager.loadMainData(_view.getContext(), new MainResponse() {
            @Override
            public void loadMainData(Response response) {
                super.loadMainData(response);
                if (response.getErrno().equals("0")) {
                    loadDataSuccess(response);
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


                } else {
                    Toast.makeText(getActivity(), response.getErrmsg(), Toast.LENGTH_LONG).show();
                }
            }
        });



        //定位
//        mLocationManager = new LocationManager(getActivity().getApplicationContext());
//        mLocationManager.start();

    }

    private void loadDataSuccess(Response response)
    {
        mURLs.removeAll(mURLs);
        mGallery.removeAllViews();
        MainModel mainModel = (MainModel) response.getData();
        mURLs.add(mainModel.getImg1());
        mURLs.add(mainModel.getImg2());
        mURLs.add(mainModel.getImg3());
        int width = getScreenWidth();
        for (int i = 0; i < mURLs.size(); i++)
        {
            View view = mInflater.inflate(R.layout.activity_index_gallery_item, mGallery, false);
            ImageView img = (ImageView) view.findViewById(R.id.id_index_gallery_item_image);
            ViewGroup.LayoutParams para = img.getLayoutParams();
            para.width = width;
            para.height = width*6/10;
            img.setLayoutParams(para);
            mUniversalImageUtil.displayImageWithImageView(img, mURLs.get(i));

//            TextView txt = (TextView) view.findViewById(R.id.id_index_gallery_item_text);
//            txt.setText("some info ");
            mGallery.addView(view);
        }
         mPageNews = mainModel.getPageNews();

        TextView textView1 = (TextView) _view.findViewById(R.id.new1);
        TextView textView2 = (TextView) _view.findViewById(R.id.new2);
        TextView textView3 = (TextView) _view.findViewById(R.id.new3);
        textView1.setText(mPageNews.get(0).getTitle());
        textView2.setText(mPageNews.get(1).getTitle());
        textView3.setText(mPageNews.get(2).getTitle());
     }

    //获取屏幕的宽度
    public int getScreenWidth() {
        WindowManager manager = (WindowManager)_view.getContext().getSystemService(_view.getContext().WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        Rect rect=new Rect();
        display.getRectSize(rect);
        return rect.width();

    }



    private void initView() {
//        _mWebView = (WebView) _view.findViewById(R.id.home_webView);
        mInflater = LayoutInflater.from(_view.getContext());
        mScrollView = (ScrollView) _view.findViewById(R.id.mainScrollView);
        mGallery    = (LinearLayout) _view.findViewById(R.id.homegallery);
        mWebSchoolBtn = (Button) _view.findViewById(R.id.webSchoolBtn);
        mCommentSchoolBtn = (Button) _view.findViewById(R.id.commentschoolBtn);
        mSecondhandBtn = (Button) _view.findViewById(R.id.secondhandBtn);
        handleAction();
    }

    private void handleAction() {
        mWebSchoolBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VersionManager versionManager = VersionManager.getInstance(getActivity());
                versionManager.checkUpdate();
//                Intent intent = new Intent(_view.getContext(),SchoolWebActivity.class);
//                Intent intent = new Intent(_view.getContext(),HHAVActivity.class);
//                startActivity(intent);
//                getActivity().overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.fade_out);

            }
        });

        //评论校园按钮点击
        mCommentSchoolBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(_view.getContext(),CommentSchoolActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.fade_out);
            }
        });

        mSecondhandBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }




}
