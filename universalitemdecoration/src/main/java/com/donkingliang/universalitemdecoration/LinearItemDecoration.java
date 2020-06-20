package com.donkingliang.universalitemdecoration;

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
public class LinearItemDecoration extends RecyclerView.ItemDecoration {

    private Drawable mDivider;

    private int mSpace;

    private final Rect mBounds = new Rect();

    /**
     * 是否显示最后一个项的Divider，默认不显示
     */
    private boolean showLastDivider = false;

    public LinearItemDecoration(int space, Drawable divider) {
        mSpace = space;
        mDivider = divider;
    }

    public void setShowLastDivider(boolean isShow) {
        showLastDivider = isShow;
    }

    @Override
    public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        if (parent.getLayoutManager() == null || mDivider == null) {
            return;
        }

        LinearLayoutManager layoutManager = (LinearLayoutManager) parent.getLayoutManager();
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

            // 最后的item是否显示Divider
            if (!showLastDivider && isLastItem(child, parent)) {
                continue;
            }

            parent.getDecoratedBoundsWithMargins(child, mBounds);
            if (orientation == RecyclerView.VERTICAL) {
                final int bottom = mBounds.bottom + Math.round(child.getTranslationY());
                final int top = bottom - mSpace;
                mDivider.setBounds(mBounds.left, top, mBounds.right, bottom);
            } else {
                final int right = mBounds.right + Math.round(child.getTranslationX());
                final int left = right - mSpace;
                mDivider.setBounds(left, mBounds.top, right, mBounds.bottom);
            }

            mDivider.draw(canvas);
        }
        canvas.restore();
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        if (!checkLayoutManager(parent) || mSpace <= 0) {
            outRect.set(0, 0, 0, 0);
            return;
        }

        LinearLayoutManager layoutManager = (LinearLayoutManager) parent.getLayoutManager();
        int orientation = layoutManager.getOrientation();

        // 最后的item是否显示Divider
        if (!showLastDivider && isLastItem(view, parent)) {
            outRect.set(0, 0, 0, 0);
            return;
        }

        if (orientation == RecyclerView.VERTICAL) {
            outRect.set(0, 0, 0, mSpace);
        } else {
            outRect.set(0, 0, mSpace, 0);
        }
    }

    private boolean checkLayoutManager(RecyclerView parent) {
        return parent.getLayoutManager() != null && parent.getLayoutManager() instanceof LinearLayoutManager;
    }

    /**
     * 判断是否是最后一个item，最后一个item不添加Divider
     */
    private boolean isLastItem(View view, RecyclerView parent) {
        if (parent.getLayoutManager() != null) {
            int itemPosition = parent.getChildAdapterPosition(view);
            int itemCount = parent.getLayoutManager().getItemCount();
            return itemPosition == itemCount - 1;
        }
        return true;
    }

}
