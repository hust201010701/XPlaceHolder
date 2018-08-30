package com.orzangleli.placeholder;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

public class XPlaceHolderUtil {

    public static void attach(@NonNull Activity target, @NonNull IPlaceHolderLayout placeHolderLayout, @Nullable IPlaceHolderCallback placeHolderCallback) {
        attach(placeHolderLayout, placeHolderCallback, target);
    }

    public static void attach(@NonNull View target, @NonNull IPlaceHolderLayout placeHolderLayout, @Nullable IPlaceHolderCallback placeHolderCallback) {
        attach(placeHolderLayout, placeHolderCallback, target);
    }

    private static void attach(@NonNull IPlaceHolderLayout placeHolderLayout, @Nullable IPlaceHolderCallback placeHolderCallback, @NonNull Object target) {
        ViewGroup parent = null;
        View targetView = null;
        int targetPositionInParent = 0;
        if (target instanceof Activity) {
            targetPositionInParent = 0;
            Activity activity = (Activity) target;
            parent = activity.findViewById(android.R.id.content);
            targetView = parent.getChildAt(0);

        } else if (target instanceof View) {
            targetView = (View) target;
            parent = (ViewGroup) targetView.getParent();
            if (parent != null) {
                for (int i = 0; i < parent.getChildCount(); i++) {
                    View childAt = parent.getChildAt(i);
                    if (childAt == target) {
                        targetPositionInParent = i;
                        break;
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("ZZPlaceHolderUtil only support Activity and View. ");
        }

        ViewGroup.LayoutParams layoutParams = null;
        if (parent != null) {
            // 第二步 把target对应的LayoutParams保存起来
            layoutParams = targetView.getLayoutParams();
        }
        placeHolderLayout.saveContentInfo(parent, targetPositionInParent, layoutParams);
        placeHolderLayout.setPlaceHolderCallback(placeHolderCallback);
        placeHolderLayout.setContentView(targetView);

    }





}
