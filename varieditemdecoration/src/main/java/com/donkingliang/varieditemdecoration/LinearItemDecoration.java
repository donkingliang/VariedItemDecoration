package com.donkingliang.varieditemdecoration;

import android.graphics.drawable.Drawable;

/**
 * @Author teach liang
 * @Description
 * @Date 2020/6/19
 */
public class LinearItemDecoration extends LinearVariedItemDecoration {

    private Drawable mDivider;

    private int mDividerSize;

    public LinearItemDecoration(int dividerSize, Drawable divider) {
        mDividerSize = dividerSize;
        mDivider = divider;
    }

    @Override
    public int getDividerSize(int position) {
        return mDividerSize;
    }

    @Override
    public Drawable getDivider(int position) {
        return mDivider;
    }
}
