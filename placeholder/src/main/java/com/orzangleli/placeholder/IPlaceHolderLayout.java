package com.orzangleli.placeholder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * <p>description：
 * <p>description：新版占位控件
 * 具有以下功能：
 * 1. 加载中
 * 2. 加载失败
 * 3. 数据为空
 * 4. 加载成功
 * <p>===============================
 * <p>creator：lixiancheng
 * <p>create time：2018/7/20 上午11:31
 * <p>===============================
 * <p>reasons for modification：
 * <p>Modifier：
 * <p>Modify time：
 * <p>@version
 */

public abstract class IPlaceHolderLayout<T> extends FrameLayout{
    protected State mState = State.SUCCESS;
    protected State[] mAvailableStates = new State[]{State.ERROR};
    protected View mPlaceHolderView;
    protected IPlaceHolderCallback mPlaceHolderCallback;
    protected Context mContext;
    protected View mContentView;

    protected T mPlaceHolderVo;

    private ViewGroup mParentView;
    private int mTargetPositionInParent;
    private ViewGroup.LayoutParams mLayoutParamsInParent;


    public IPlaceHolderLayout(@NonNull Context context) {
        super(context);
        init(context);
    }

    public IPlaceHolderLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public IPlaceHolderLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    protected  void init(Context context){
        mContext = context;
    }

    private void checkAndInitPlaceHolderView() {
        if (mPlaceHolderView != null) {
            return;
        }
        mPlaceHolderView = LayoutInflater.from(mContext).inflate(getLayoutId(), this, false);
        this.addView(mPlaceHolderView);

        bindView(mPlaceHolderView);
        mPlaceHolderView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAvailableStateForClick() && mPlaceHolderCallback != null) {
                    mPlaceHolderCallback.onRetry(mState);
                }
            }
        });
    }

    protected abstract int getLayoutId();
    protected abstract void bindView(View rootView);

    public State getState() {
        return mState;
    }

    public void setState(State state) {
        this.mState = state;
        checkAndInitPlaceHolderVo();

        if (mState == State.SUCCESS) {
            detachPlaceHolderFromParent();
        } else {
            checkAndInitPlaceHolderView();
            attachPlaceHolderToParent();
        }
        bindState(state);
    }

    public abstract void checkAndInitPlaceHolderVo();
    public abstract void bindState(State state);

    public void setPlaceHolderCallback(IPlaceHolderCallback placeHolderCallback) {
        this.mPlaceHolderCallback = placeHolderCallback;
    }

    /**
     * 设置可以点击的State
     */
    public void setAvailableStateForClick(State[] states) {
        mAvailableStates = states;
    }


    public boolean isAvailableStateForClick() {
        for (int i = 0; i< mAvailableStates.length; i++) {
            State state = mAvailableStates[i];
            if (mState == state) {
                return true;
            }
        }
        return false;
    }

    public void setContentView(View contentView) {
        mContentView = contentView;
        /**
         * 确保添加 contentView 时，此时只是加入了 PlaceHolderView,没有其他的 View.
         */
        if (mParentView == null && mContentView != null) {
            removeViewFromParent(mContentView);
            this.addView(mContentView);
        }
    }

    public void showSuccess() {
        this.setState(State.SUCCESS);
    }

    public void showError() {
        this.setState(State.ERROR);
    }

    public void showEmpty() {
        this.setState(State.EMPTY);
    }

    public void showLoading() {
        this.setState(State.LOADING);
    }


    /**
     * 取消移花接木的过程，将mContentView 还原成之前的层级
     */
    protected void detachPlaceHolderFromParent() {
        if (mContentView != null) {
            // 1. 将 mContentView 从 PlaceHolderLayout 中移除
            removeViewFromParent(mContentView);
            mContentView.setVisibility(View.VISIBLE);
            // 3. 向 parent 中添加 mContentView
            if (mParentView != null) {
                removeViewFromParent(this);
                mParentView.addView(mContentView, mTargetPositionInParent, mLayoutParamsInParent);
            } else {
                this.addView(mContentView);
            }
        }
        if (mPlaceHolderView != null) {
            mPlaceHolderView.setVisibility(View.GONE);
        }
    }

    /**
     * 重新移花接木一次
     */
    protected void attachPlaceHolderToParent() {
        if (mContentView != null) {
            // 1. 将 mContentView 从 mParentView 中移除
            removeViewFromParent(mContentView);
            // 2. 从 PlaceHolderLayout 中添加 mContentView
            this.addView(mContentView);
            mContentView.setVisibility(View.GONE);
            // 3. 向 parent 中添加 mContentView
            if (mParentView != null) {
                removeViewFromParent(this);
                mParentView.addView(this, mTargetPositionInParent, mLayoutParamsInParent);
            } else {
                if (mPlaceHolderView != null) {
                    removeViewFromParent(mPlaceHolderView);
                    this.addView(mPlaceHolderView);
                }
            }
        }
        if (mPlaceHolderView != null) {
            mPlaceHolderView.setVisibility(View.VISIBLE);
        }
    }

    public void setPlaceHolderVo(T placeHolderVo) {
        mPlaceHolderVo = placeHolderVo;
    }

    public void saveContentInfo(ViewGroup parent, int targetPositionInParent, ViewGroup.LayoutParams layoutParams) {
        mParentView = parent;
        mTargetPositionInParent = targetPositionInParent;
        mLayoutParamsInParent = layoutParams;
    }

    private void removeViewFromParent(View view) {
        if (view != null && view.getParent() != null) {
            ((ViewGroup)view.getParent()).removeView(view);
        }
    }
}
