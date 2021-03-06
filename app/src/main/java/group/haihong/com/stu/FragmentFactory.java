package group.haihong.com.stu;


import android.app.Fragment;
import android.view.View;
import android.widget.RadioGroup;

import group.haihong.com.stu.Groups.TabGroupFragmentTab;
import group.haihong.com.stu.Hot.TabHotFragmentTab;
import group.haihong.com.stu.Main.TabHomeFragment;
import group.haihong.com.stu.More.TabMoreFragmentTab;

/**
 * Created by admin on 13-11-23.
 */
public class FragmentFactory {
    TabHomeFragment homeFragment;
    TabGroupFragmentTab groupFragmentTab;
    TabHotFragmentTab hotFragmentTab;
    TabMoreFragmentTab moreFragmentTab;

    public FragmentFactory() {
        homeFragment     = new TabHomeFragment();
        groupFragmentTab = new TabGroupFragmentTab();
        hotFragmentTab   = new TabHotFragmentTab();
        moreFragmentTab  = new TabMoreFragmentTab();
    }

    public Fragment getInstanceByIndexOfChild(int index) {
         Fragment fragment = null;
        index = index<=0?1:index+1;
        switch (index) {
            case 1:
                fragment = homeFragment;
                break;
            case 2:
                fragment = groupFragmentTab;
                break;
            case 3:
                fragment = hotFragmentTab;
                break;
            case 4:
                fragment = moreFragmentTab;
                break;
            case 5:
//                fragment = new TabGlobalFragmentTab();
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
