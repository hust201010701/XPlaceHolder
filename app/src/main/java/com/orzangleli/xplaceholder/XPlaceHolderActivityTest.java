package com.orzangleli.xplaceholder;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.orzangleli.placeholder.IPlaceHolderCallback;
import com.orzangleli.placeholder.State;
import com.orzangleli.placeholder.XPlaceHolderUtil;
import com.orzangleli.xplaceholder.placeholder.ImageAndTextPlaceHolderLayout;
import com.orzangleli.xplaceholder.placeholder.ImageAndTextPlaceHolderVo;

public class XPlaceHolderActivityTest extends AppCompatActivity implements IPlaceHolderCallback {

    ImageAndTextPlaceHolderLayout mImageAndTextPlaceHolderLayout;
    TextView mTextView;
    int mTimes = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xplace_holder_test);

        mTextView = this.findViewById(R.id.textview);

        mImageAndTextPlaceHolderLayout = new ImageAndTextPlaceHolderLayout(this);
        mImageAndTextPlaceHolderLayout.setPlaceHolderVo(new ImageAndTextPlaceHolderVo.Builder()
                .setLoadingImageResource(R.drawable.icon_loading)
                .setEmptyImageResource(R.drawable.icon_empty)
                .setErrorImageResource(R.drawable.icon_error)
                .setLottieFileName("lottie/lego_loader.json")
                .setLoadingText(getString(R.string.loading_tip))
                .setEmptyText(getString(R.string.empty_tip))
                .setErrorText(getString(R.string.error_tip))
                .setEnableLottie(true)
                .build());
        XPlaceHolderUtil.attach(this, mImageAndTextPlaceHolderLayout, this);

        // 默认只有错误页面可以点击重试，可以设置
        mImageAndTextPlaceHolderLayout.setAvailableStateForClick(new State[]{State.EMPTY, State.ERROR});
        mImageAndTextPlaceHolderLayout.setLoadingAnimationDuration(200);
        mImageAndTextPlaceHolderLayout.setPlaceHolderBackgroundColor(Color.WHITE);

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
