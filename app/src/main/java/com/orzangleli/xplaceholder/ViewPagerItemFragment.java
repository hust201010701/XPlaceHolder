package com.orzangleli.xplaceholder;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.orzangleli.placeholder.IPlaceHolderCallback;
import com.orzangleli.placeholder.State;
import com.orzangleli.placeholder.XPlaceHolderUtil;
import com.orzangleli.xplaceholder.placeholder.ImageAndTextPlaceHolderLayout;
import com.orzangleli.xplaceholder.placeholder.ImageAndTextPlaceHolderVo;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewPagerItemFragment extends Fragment {
    String mName;

    ImageAndTextPlaceHolderLayout mImageAndTextPlaceHolderLayout;

    int times;

    public ViewPagerItemFragment() {
        // Required empty public constructor
    }

    public void setName(String name) {
        mName = name;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mImageAndTextPlaceHolderLayout = new ImageAndTextPlaceHolderLayout(this.getContext());

        View view = inflater.inflate(R.layout.fragment_view_pager_item, container, false);
        final TextView textView = view.findViewById(R.id.textView);
        textView.setText(mName);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                times ++;
                if (times % 2 == 0) {
                    mImageAndTextPlaceHolderLayout.showError();
                } else {
                    mImageAndTextPlaceHolderLayout.showEmpty();
                }
            }
        });

        XPlaceHolderUtil.attach(view, mImageAndTextPlaceHolderLayout, new IPlaceHolderCallback() {
            @Override
            public void onRetry(State state) {
                mImageAndTextPlaceHolderLayout.showLoading();
                textView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mImageAndTextPlaceHolderLayout.showSuccess();
                    }
                }, 2000);
            }
        });

        // 默认只有错误页面可以点击重试，可以设置
        mImageAndTextPlaceHolderLayout.setAvailableStateForClick(new State[]{State.EMPTY, State.ERROR});

        return mImageAndTextPlaceHolderLayout;
    }


}
