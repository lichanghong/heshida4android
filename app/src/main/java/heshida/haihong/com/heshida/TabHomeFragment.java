package heshida.haihong.com.heshida;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import cn.domob.android.ads.AdEventListener;
import cn.domob.android.ads.AdManager;
import cn.domob.android.ads.AdView;
import heshida.haihong.com.heshida.home.JokeActivity;

/**
 * Created by admin on 13-11-23.
 */
public class TabHomeFragment extends Fragment {

    public  static  final String PUBLISHER_ID = "56OJ25K4uN38i7A79L";
    public  static  final String INLINE_PPID  = "16TLPlylApn4PNUv2cu8brIs";

    View _view;
    RelativeLayout _AdContainer;
    AdView _Adview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        _view = view;

//        添加广告页
        //initADView(view);
        initView();
        return view;
    }

    private void initView() {

        initListener();
    }

    private void initListener() {
            Button button =(Button) _view.findViewById(R.id.home_button_joke);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(_view.getContext(),JokeActivity.class);
                    startActivity(intent);
//                    Toast.makeText(v.getContext(),"show joke",Toast.LENGTH_LONG).show();
                }
            });
    }

    private void initADView(View view) {
        final Activity mainActivity = getActivity();
        _AdContainer =(RelativeLayout) view.findViewById(R.id.adcontainer);
        _Adview = new AdView(mainActivity, PUBLISHER_ID, INLINE_PPID);
        _Adview.setKeyword("game");
        _Adview.setAdSize(AdView.INLINE_SIZE_320X50);
        _Adview.setAdEventListener(new AdEventListener() {
            @Override
            public void onEventAdReturned(AdView adView) {
                Log.i("domob", "onEventAdReturned");
            }

            @Override
            public void onAdFailed(AdView adView, AdManager.ErrorCode errorCode) {
                Log.i("domob", "onAdFailed "+errorCode);

            }

            @Override
            public void onAdOverlayPresented(AdView adView) {
                Log.i("domob", "onAdOverlayPresented");

            }

            @Override
            public void onAdOverlayDismissed(AdView adView) {
                Log.i("domob", "onAdOverlayDismissed");

            }

            @Override
            public void onLeaveApplication(AdView adView) {
                Log.i("domob", "onLeaveApplication");

            }

            @Override
            public void onAdClicked(AdView adView) {
                Log.i("domob", "onAdClicked");

            }

            @Override
            public Context onAdRequiresCurrentContext() {
                Log.i("domob", "onAdRequiresCurrentContext");

                return mainActivity;
            }
        });

        RelativeLayout.LayoutParams layout = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layout.addRule(RelativeLayout.CENTER_HORIZONTAL);
        _Adview.setLayoutParams(layout);
        _AdContainer.addView(_Adview);
    }



}
