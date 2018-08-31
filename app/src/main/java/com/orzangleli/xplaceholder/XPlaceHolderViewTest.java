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

public class XPlaceHolderViewTest extends AppCompatActivity implements IPlaceHolderCallback {

    TextView mTextView;
    ImageAndTextPlaceHolderLayout mImageAndTextPlaceHolderLayout;
    int mTimes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xplace_holder_view_test);

        mTextView = findViewById(R.id.textView);

        mImageAndTextPlaceHolderLayout = new ImageAndTextPlaceHolderLayout(this);
        ImageAndTextPlaceHolderVo imageAndTextPlaceHolderVo = new ImageAndTextPlaceHolderVo.Builder()
                .setLoadingText("加载中。。。")
                .setLoadingImageResource(R.drawable.icon_loading)
                .setEmptyText("页面空白文本")
                .setEmptyImageResource(R.drawable.icon_empty)
                .setErrorText("页面加载错误")
                .setErrorImageResource(R.drawable.icon_error)
                .build();
        mImageAndTextPlaceHolderLayout.setPlaceHolderVo(imageAndTextPlaceHolderVo);

        XPlaceHolderUtil.attach(mTextView, mImageAndTextPlaceHolderLayout, this);

        // 默认只有错误页面可以点击重试，可以设置
        mImageAndTextPlaceHolderLayout.setAvailableStateForClick(new State[]{State.EMPTY, State.ERROR});

        mImageAndTextPlaceHolderLayout.setPlaceHolderBackgroundColor(Color.MAGENTA);

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
