package heshida.haihong.com.heshida;


import android.app.Fragment;
import android.view.View;
import android.widget.RadioGroup;

import heshida.haihong.com.heshida.Groups.TabGroupFragmentTab;
import heshida.haihong.com.heshida.Hot.TabHotFragmentTab;
import heshida.haihong.com.heshida.Main.TabHomeFragment;
import heshida.haihong.com.heshida.More.TabMoreFragmentTab;

/**
 * Created by admin on 13-11-23.
 */
public class FragmentFactory {

    public static Fragment getInstanceByIndexOfChild(int index) {
         Fragment fragment = null;
        index = index<=0?1:index+1;
        switch (index) {
            case 1:
                fragment = new TabHomeFragment();
                break;
            case 2:
                fragment = new TabGroupFragmentTab();
                break;
            case 3:
                fragment = new TabHotFragmentTab();
                break;
            case 4:
                fragment = new TabMoreFragmentTab();
                break;
            case 5:
                fragment = new TabGlobalFragmentTab();
                break;
        }
        return fragment;
    }

    //隐藏tabbar的静态方法
    public static void hiddenRadioGroup(RadioGroup group)
    {
        group.setVisibility(View.GONE);
    }

}
