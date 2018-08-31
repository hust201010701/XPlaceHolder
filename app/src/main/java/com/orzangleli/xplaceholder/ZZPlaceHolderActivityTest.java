package com.orzangleli.xplaceholder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.orzangleli.placeholder.IPlaceHolderCallback;
import com.orzangleli.placeholder.IPlaceHolderLayout;
import com.orzangleli.placeholder.State;
import com.orzangleli.placeholder.XPlaceHolderUtil;
import com.orzangleli.xplaceholder.placeholder.ImageAndTextPlaceHolderLayout;

public class ZZPlaceHolderActivityTest extends AppCompatActivity implements IPlaceHolderCallback {

    ImageAndTextPlaceHolderLayout mImageAndTextPlaceHolderLayout;
    TextView mTextView;
    int mTimes = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zzplace_holder_test);

        mTextView = this.findViewById(R.id.textview);

        mImageAndTextPlaceHolderLayout = new ImageAndTextPlaceHolderLayout(this);
        XPlaceHolderUtil.attach(this, mImageAndTextPlaceHolderLayout, this);

        // 默认只有错误页面可以点击重试，可以设置
        mImageAndTextPlaceHolderLayout.setAvailableStateForClick(new State[]{State.EMPTY, State.ERROR});
        mImageAndTextPlaceHolderLayout.setLoadingAnimationDuration(200);

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
