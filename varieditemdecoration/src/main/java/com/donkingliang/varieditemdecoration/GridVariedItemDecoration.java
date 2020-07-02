package com.donkingliang.varieditemdecoration;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @Author donkingliang QQ:1043214265 github:https://github.com/donkingliang
 * @Description 自定义GridItemDecoration的基类，根据position返回每个item的间隔大小和装饰Drawable，Drawable可以为null。
 * 垂直间隔和水平间隔分别设置
 * @Date 2020/6/22
 */
public abstract class GridVariedItemDecoration extends RecyclerView.ItemDecoration implements IVariedItemDecoration {

    /**
     * 是否显示最后一行的Divider，默认不显示
     */
    private boolean showLastDivider = false;

    private final Rect mBounds = new Rect();

    @Override
    public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        if (!checkLayoutManager(parent)) {
            return;
        }

        GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
        int orientation = layoutManager.getOrientation();

        canvas.save();

        if (parent.getClipToPadding()) {
            // 设置绘制画布去掉padding
            canvas.clipRect(parent.getPaddingLeft(), parent.getPaddingTop(),
                    parent.getWidth() - parent.getPaddingRight(), parent.getHeight() - parent.getPaddingBottom());
        }

        final int childCount = parent.getChildCount();

        GridLayoutManager.SpanSizeLookup spanSizeLookup = layoutManager.getSpanSizeLookup();
        int spanCount = layoutManager.getSpanCount();
        int itemCount = layoutManager.getItemCount();

        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            parent.getDecoratedBoundsWithMargins(child, mBounds);

            int position = parent.getChildAdapterPosition(child);

            Drawable columnDivider = getColumnDivider(position);
            boolean hasRightDivider = columnDivider != null &&
                    ((orientation == RecyclerView.HORIZONTAL && isShowLastDivider())
                            || !isRightItem(position, spanCount, itemCount, spanSizeLookup, orientation));
            if (hasRightDivider) {
                final int right = mBounds.right + Math.round(child.getTranslationX());
                final int left = right - getColumnDividerSize(position);
                columnDivider.setBounds(left, mBounds.top, right, mBounds.bottom);
                columnDivider.draw(canvas);
            }

            Drawable rowDivider = getRowDivider(position);
            boolean hasBottomDivider = rowDivider != null &&
                    ((orientation == RecyclerView.VERTICAL && showLastDivider)
                            || !isBottomItem(position, spanCount, itemCount, spanSizeLookup, orientation));
            if (hasBottomDivider) {
                final int bottom = mBounds.bottom + Math.round(child.getTranslationY());
                final int top = bottom - getRowDividerSize(position);
                rowDivider.setBounds(mBounds.left, top, mBounds.right, bottom);
                rowDivider.draw(canvas);
            }
        }
        canvas.restore();
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {

        if (!checkLayoutManager(parent)) {
            outRect.set(0, 0, 0, 0);
            return;
        }

        GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
        int orientation = layoutManager.getOrientation();
        GridLayoutManager.SpanSizeLookup spanSizeLookup = layoutManager.getSpanSizeLookup();
        int spanCount = layoutManager.getSpanCount();
        int itemCount = layoutManager.getItemCount();
        int position = parent.getChildAdapterPosition(view);

        int right = 0;
        int bottom = 0;

        boolean hasRightDivider = (orientation == RecyclerView.HORIZONTAL && showLastDivider)
                || !isRightItem(position, spanCount, itemCount, spanSizeLookup, orientation);
        if (hasRightDivider) {
            right = getColumnDividerSize(position);
        }

        boolean hasBottomDivider = (orientation == RecyclerView.VERTICAL && showLastDivider)
                || !isBottomItem(position, spanCount, itemCount, spanSizeLookup, orientation);
        if (hasBottomDivider) {
            bottom = getRowDividerSize(position);
        }

        outRect.set(0, 0, right, bottom);
    }

    /**
     * 判断是否是位于右边的item
     *
     * @param view
     * @param position
     * @return
     */
    public boolean isRightItem(RecyclerView view, int position) {
        if (!checkLayoutManager(view)) {
            return false;
        }

        GridLayoutManager layoutManager = (GridLayoutManager) view.getLayoutManager();
        int orientation = layoutManager.getOrientation();
        GridLayoutManager.SpanSizeLookup spanSizeLookup = layoutManager.getSpanSizeLookup();
        int spanCount = layoutManager.getSpanCount();
        int itemCount = layoutManager.getItemCount();
        return isRightItem(position, spanCount, itemCount, spanSizeLookup, orientation);
    }

    /**
     * 判断是否是位于右边的item
     */
    private boolean isRightItem(int position, int spanCount, int itemCount, GridLayoutManager.SpanSizeLookup spanSizeLookup, int orientation) {

        if (orientation == RecyclerView.VERTICAL) {
            int spanIndex = spanSizeLookup.getSpanIndex(position, spanCount);
            int positionSpanSize = spanSizeLookup.getSpanSize(position);
            return positionSpanSize + spanIndex == spanCount;
        } else {

            // 最后一列的开始位置
            int lastColumnPosition = itemCount - 1;
            while (lastColumnPosition >= 0 && spanSizeLookup.getSpanIndex(lastColumnPosition, spanCount) != 0) {
                lastColumnPosition--;
            }

            return lastColumnPosition <= position;
        }
    }

    /**
     * 判断是否是位于底部的item
     *
     * @param view
     * @param position
     * @return
     */
    public boolean isBottomItem(RecyclerView view, int position) {
        if (!checkLayoutManager(view)) {
            return false;
        }

        GridLayoutManager layoutManager = (GridLayoutManager) view.getLayoutManager();
        int orientation = layoutManager.getOrientation();
        GridLayoutManager.SpanSizeLookup spanSizeLookup = layoutManager.getSpanSizeLookup();
        int spanCount = layoutManager.getSpanCount();
        int itemCount = layoutManager.getItemCount();
        return isBottomItem(position, spanCount, itemCount, spanSizeLookup, orientation);
    }

    /**
     * 判断是否是位于底部的item
     */
    private boolean isBottomItem(int position, int spanCount, int itemCount, GridLayoutManager.SpanSizeLookup spanSizeLookup, int orientation) {

        if (orientation == RecyclerView.VERTICAL) {
            // 最后一行的开始位置
            int lastRowPosition = itemCount - 1;
            while (lastRowPosition >= 0 && spanSizeLookup.getSpanIndex(lastRowPosition, spanCount) != 0) {
                lastRowPosition--;
            }
            return lastRowPosition <= position;
        } else {
            int spanIndex = spanSizeLookup.getSpanIndex(position, spanCount);
            int positionSpanSize = spanSizeLookup.getSpanSize(position);
            return positionSpanSize + spanIndex == spanCount;
        }
    }

    /**
     * 设置是否显示最后一行的Divider，默认不显示
     */
    public void setShowLastDivider(boolean isShow) {
        showLastDivider = isShow;
    }

    @Override
    public boolean isShowLastDivider() {
        return showLastDivider;
    }

    @Override
    public boolean checkLayoutManager(RecyclerView view) {
        return view.getLayoutManager() != null && view.getLayoutManager() instanceof GridLayoutManager;
    }
}
