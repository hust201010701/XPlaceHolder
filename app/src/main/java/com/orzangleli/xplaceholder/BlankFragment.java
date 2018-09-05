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
public class BlankFragment extends Fragment implements IPlaceHolderCallback {

    ImageAndTextPlaceHolderLayout mImageAndTextPlaceHolderLayout;
    TextView mTextView;
    int mTimes = 0;

    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        mTextView = view.findViewById(R.id.textView);

        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTimes ++;
                if (mTimes % 2 == 0) {
                    mImageAndTextPlaceHolderLayout.showEmpty();
                } else {
                    mImageAndTextPlaceHolderLayout.showError();
                }
            }
        });

        mImageAndTextPlaceHolderLayout = new ImageAndTextPlaceHolderLayout(this.getContext());
        ImageAndTextPlaceHolderVo imageAndTextPlaceHolderVo = new ImageAndTextPlaceHolderVo.Builder()
                .setLoadingText("loading...")
                .setLoadingImageResource(R.drawable.icon_loading)
                .setEmptyText("empty data")
                .setEmptyImageResource(R.drawable.icon_empty)
                .setErrorText("load error, click to retry")
                .setErrorImageResource(R.drawable.icon_error)
                .build();
        mImageAndTextPlaceHolderLayout.setPlaceHolderVo(imageAndTextPlaceHolderVo);
        XPlaceHolderUtil.attach(view, mImageAndTextPlaceHolderLayout, this);

        // 默认只有错误页面可以点击重试，可以设置
        mImageAndTextPlaceHolderLayout.setAvailableStateForClick(new State[]{State.EMPTY, State.ERROR});

        // 注意这里不再是返回之前的view
        return mImageAndTextPlaceHolderLayout;
    }

    @Override
    public void onRetry(State state) {
        mImageAndTextPlaceHolderLayout.showLoading();
        mTextView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mImageAndTextPlaceHolderLayout.showSuccess();
            }
        }, 2000);
    }

}
