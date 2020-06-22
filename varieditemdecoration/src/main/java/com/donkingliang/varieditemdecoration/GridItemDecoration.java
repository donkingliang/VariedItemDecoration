package com.donkingliang.varieditemdecoration;

import android.graphics.drawable.Drawable;

/**
 * @Author teach liang
 * @Description
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
