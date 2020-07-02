package com.donkingliang.varieditemdecoration;

import android.graphics.drawable.Drawable;

/**
 * @Author donkingliang QQ:1043214265 github:https://github.com/donkingliang
 * @Description 通用的GridItemDecoration类，用于设置GridLayoutManager的RecyclerView间隔装饰
 * @Date 2020/6/19
 */
public class GridItemDecoration extends GridVariedItemDecoration {

    private Drawable mRowDivider;
    private Drawable mColumnDivider;

    private int mRowDividerSize;
    private int mColumnDividerSize;

    public GridItemDecoration(int rowDividerSize, Drawable rowDivider, int columnDividerSize, Drawable columnDivider) {
        mRowDividerSize = rowDividerSize;
        mRowDivider = rowDivider;
        mColumnDividerSize = columnDividerSize;
        mColumnDivider = columnDivider;
    }

    @Override
    public int getRowDividerSize(int position) {
        return mRowDividerSize;
    }

    @Override
    public Drawable getRowDivider(int position) {
        return mRowDivider;
    }

    @Override
    public int getColumnDividerSize(int position) {
        return mColumnDividerSize;
    }

    @Override
    public Drawable getColumnDivider(int position) {
        return mColumnDivider;
    }
}
