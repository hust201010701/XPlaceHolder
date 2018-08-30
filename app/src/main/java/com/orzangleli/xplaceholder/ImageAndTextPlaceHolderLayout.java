package com.orzangleli.xplaceholder;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.orzangleli.placeholder.IPlaceHolderLayout;
import com.orzangleli.placeholder.State;

/**
 * <p>description：带图片和文本的占位控件
 * <p>===============================
 * <p>creator：lixiancheng
 * <p>create time：2018/8/30 下午7:46
 * <p>===============================
 * <p>reasons for modification：
 * <p>Modifier：
 * <p>Modify time：
 * <p>@version
 */

public class ImageAndTextPlaceHolderLayout extends IPlaceHolderLayout<ImageAndTextPlaceHolderVo> {

    private ImageView mImageView;
    private TextView mTextView;
    private ObjectAnimator mRotationAnimation;

    public ImageAndTextPlaceHolderLayout(@NonNull Context context) {
        super(context);
    }

    public ImageAndTextPlaceHolderLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageAndTextPlaceHolderLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_image_and_text_place_holder;
    }

    @Override
    protected void initView(View rootView) {
        mImageView = rootView.findViewById(R.id.img);
        mTextView = rootView.findViewById(R.id.text);
        mRotationAnimation = ObjectAnimator.ofFloat(mImageView, "rotation", 0, 360);
        mRotationAnimation.setDuration(6);
        mRotationAnimation.setRepeatCount(Animation.INFINITE);
    }

    public void setLoadingAnimationDuration(int duration) {
        mRotationAnimation.setDuration(duration);
    }

    @Override
    public void checkAndInitPlaceHolderVo() {
        if (mPlaceHolderVo == null) {
            // 设置默认的图片和文案
            mPlaceHolderVo = new ImageAndTextPlaceHolderVo.Builder()
                    .setLoadingImageResource(R.drawable.icon_loading)
                    .setEmptyImageResource(R.drawable.icon_empty)
                    .setErrorImageResource(R.drawable.icon_error)
                    .setLoadingText(mContext.getString(R.string.loading_tip))
                    .setEmptyText(mContext.getString(R.string.empty_tip))
                    .setErrorText(mContext.getString(R.string.error_tip))
                    .build();
        }
    }

    @Override
    public void bindState(State state) {
        switch (state) {
            case LOADING:
                if (mPlaceHolderVo != null) {
                    mImageView.setImageResource(mPlaceHolderVo.loadingImageResource);
                    if (!mRotationAnimation.isRunning()) {
                        mRotationAnimation.start();
                    }
                    mTextView.setText(mPlaceHolderVo.loadingText);
                }
                break;
            case EMPTY:
                if (mRotationAnimation.isRunning()) {
                    mRotationAnimation.end();
                }
                if (mPlaceHolderVo != null) {
                    mImageView.setImageResource(mPlaceHolderVo.emptyImageResource);
                    mTextView.setText(mPlaceHolderVo.emptyText);
                }
                break;
            case ERROR:
                if (mRotationAnimation.isRunning()) {
                    mRotationAnimation.end();
                }
                if (mPlaceHolderVo != null) {
                    mImageView.setImageResource(mPlaceHolderVo.errorImageResource);
                    mTextView.setText(mPlaceHolderVo.errorText);
                }
                break;
            case SUCCESS:
                if (mRotationAnimation.isRunning()) {
                    mRotationAnimation.end();
                }
                break;
        }
    }
}
