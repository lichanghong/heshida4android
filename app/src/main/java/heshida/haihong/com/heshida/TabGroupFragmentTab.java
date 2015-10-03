package heshida.haihong.com.heshida;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by admin on 13-11-23.
 */
public class TabGroupFragmentTab extends Fragment {

    private View _view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_group, null);
        _view = view;

        initView();
        return view;
    }

    private void initView() {

    }

}
