package com.donkingliang.universalitemdecoration;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @Author teach liang
 * @Description
 * @Date 2020/6/19
 */
public class GridItemDecoration extends RecyclerView.ItemDecoration {

    private Drawable mRowDivider;
    private Drawable mColumnDivider;

    private int mRowSpace;
    private int mColumnSpace;

    private final Rect mBounds = new Rect();

    /**
     * 是否显示最后一行的Divider，默认不显示
     */
    private boolean showLastDivider = false;

    public GridItemDecoration(int rowSpace, Drawable rowDivider, int columnSpace, Drawable columnDivider) {
        mRowSpace = rowSpace;
        mRowDivider = rowDivider;
        mColumnSpace = columnSpace;
        mColumnDivider = columnDivider;
    }

    public void setShowLastDivider(boolean isShow) {
        showLastDivider = isShow;
    }

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
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            parent.getDecoratedBoundsWithMargins(child, mBounds);

            boolean hasRightDivider = mColumnDivider != null &&
                    ((orientation == RecyclerView.HORIZONTAL && showLastDivider)
                            || !isRightItem(child, parent, orientation));
            if (hasRightDivider) {
                final int right = mBounds.right + Math.round(child.getTranslationX());
                final int left = right - mColumnSpace;
                mColumnDivider.setBounds(left, mBounds.top, right, mBounds.bottom);
                mColumnDivider.draw(canvas);
            }

            boolean hasBottomDivider = mRowDivider != null &&
                    ((orientation == RecyclerView.VERTICAL && showLastDivider)
                            || !isBottomItem(child, parent, orientation));
            if (hasBottomDivider) {
                final int bottom = mBounds.bottom + Math.round(child.getTranslationY());
                final int top = bottom - mRowSpace;
                mRowDivider.setBounds(mBounds.left, top, mBounds.right, bottom);
                mRowDivider.draw(canvas);
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


        int right = 0;
        int bottom = 0;

        boolean hasRightDivider = (orientation == RecyclerView.HORIZONTAL && showLastDivider)
                || !isRightItem(view, parent, orientation);
        if (hasRightDivider) {
            right = mColumnSpace;
        }

        boolean hasBottomDivider = (orientation == RecyclerView.VERTICAL && showLastDivider)
                || !isBottomItem(view, parent, orientation);
        if (hasBottomDivider) {
            bottom = mRowSpace;
        }

        outRect.set(0, 0, right, bottom);
    }

    /**
     * 判断是否是位于右边的item
     *
     * @param view
     * @param parent
     * @return
     */
    private boolean isRightItem(View view, RecyclerView parent, int orientation) {

        int itemPosition = parent.getChildAdapterPosition(view);

        GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
        GridLayoutManager.SpanSizeLookup spanSizeLookup = layoutManager.getSpanSizeLookup();
        if (orientation == RecyclerView.VERTICAL) {
            int spanCount = layoutManager.getSpanCount();
            int spanIndex = spanSizeLookup.getSpanIndex(itemPosition, spanCount);
            int positionSpanSize = spanSizeLookup.getSpanSize(itemPosition);
            return positionSpanSize + spanIndex == spanCount;
        } else {

            // 最后一列的开始位置
            int lastColumnPosition = layoutManager.getItemCount() - 1;
            while (lastColumnPosition >= 0 && spanSizeLookup.getSpanIndex(lastColumnPosition, layoutManager.getSpanCount()) != 0) {
                lastColumnPosition--;
            }

            return lastColumnPosition >= itemPosition;
        }
    }

    /**
     * 判断是否是位于底部的item
     *
     * @param view
     * @param parent
     * @return
     */
    private boolean isBottomItem(View view, RecyclerView parent, int orientation) {

        int itemPosition = parent.getChildAdapterPosition(view);

        GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
        GridLayoutManager.SpanSizeLookup spanSizeLookup = layoutManager.getSpanSizeLookup();
        if (orientation == RecyclerView.VERTICAL) {
            // 最后一行的开始位置
            int lastRowPosition = layoutManager.getItemCount() - 1;
            while (lastRowPosition >= 0 && spanSizeLookup.getSpanIndex(lastRowPosition, layoutManager.getSpanCount()) != 0) {
                lastRowPosition--;
            }
            return lastRowPosition <= itemPosition;
        } else {
            int spanCount = layoutManager.getSpanCount();
            int spanIndex = spanSizeLookup.getSpanIndex(itemPosition, spanCount);
            int positionSpanSize = spanSizeLookup.getSpanSize(itemPosition);
            return positionSpanSize + spanIndex == spanCount;
        }
    }

    private boolean checkLayoutManager(RecyclerView parent) {
        return parent.getLayoutManager() != null && parent.getLayoutManager() instanceof GridLayoutManager;
    }

}
