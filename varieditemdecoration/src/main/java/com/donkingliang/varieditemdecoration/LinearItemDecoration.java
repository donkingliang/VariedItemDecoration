package com.donkingliang.varieditemdecoration;

import android.graphics.drawable.Drawable;

/**
 * @Author donkingliang QQ:1043214265 github:https://github.com/donkingliang
 * @Description 通用的LinearItemDecoration类，用于设置LinearLayoutManager的RecyclerView间隔装饰
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
