package com.orzangleli.xplaceholder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.orzangleli.placeholder.IPlaceHolderCallback;
import com.orzangleli.placeholder.State;
import com.orzangleli.placeholder.XPlaceHolderUtil;

public class MainActivity extends AppCompatActivity implements IPlaceHolderCallback{

    private ImageAndTextPlaceHolderLayout mImageAndTextPlaceHolderLayout;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = this.findViewById(R.id.textView);

        mImageAndTextPlaceHolderLayout = new ImageAndTextPlaceHolderLayout(this);
        mImageAndTextPlaceHolderLayout.setPlaceHolderVo(new ImageAndTextPlaceHolderVo.Builder()
                .setLoadingImageResource(R.drawable.icon_loading)
                .setEmptyImageResource(R.drawable.icon_empty)
                .setErrorImageResource(R.drawable.icon_error)
                .setLoadingText(this.getString(R.string.loading_tip))
                .setEmptyText(this.getString(R.string.empty_tip))
                .setErrorText(this.getString(R.string.error_tip))
                .build());

        XPlaceHolderUtil.attach(this, mImageAndTextPlaceHolderLayout, this);

        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImageAndTextPlaceHolderLayout.showError();
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
