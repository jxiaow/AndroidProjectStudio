package cn.xwj.ioc;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.xwj.ioc.annotation.ContentView;
import cn.xwj.ioc.annotation.ViewById;
import cn.xwj.ioc.injector.Injector;

@ContentView(R.layout.fragment_blank)
public class BlankFragment extends Fragment {

    @ViewById(R.id.tv)
    private TextView mTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return Injector.inject(this, inflater, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mTextView.setText("blankFragment");
    }
}
