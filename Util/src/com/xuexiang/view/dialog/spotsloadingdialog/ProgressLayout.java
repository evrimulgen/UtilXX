package com.xuexiang.view.dialog.spotsloadingdialog;

import com.xuexiang.util.resource.MResource;
import com.xuexiang.util.resource.RUtils;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by Maxim Dybarsky | maxim.dybarskyy@gmail.com
 * on 13.01.15 at 17:34
 */
public class ProgressLayout extends FrameLayout {

    private static final int DEFAULT_COUNT = 5;
    private int spotsCount;

    public ProgressLayout(Context context) {
        this(context, null);
    }

    public ProgressLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr, 0);
    }

//    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
//    public ProgressLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//        init(attrs, defStyleAttr, defStyleRes);
//    }

    private void init(AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        TypedArray a = getContext().getTheme().obtainStyledAttributes(
                attrs,RUtils.getStyleable(getContext(), "SpotsDialog"),
                defStyleAttr, defStyleRes);

        spotsCount = a.getInt(MResource.getIdByName(getContext(), "styleable", "SpotsDialog_DialogSpotCount"), DEFAULT_COUNT);
        a.recycle();
    }

    public int getSpotsCount() {
        return spotsCount;
    }
}