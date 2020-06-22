package com.donkingliang.varieditemdecoration;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
