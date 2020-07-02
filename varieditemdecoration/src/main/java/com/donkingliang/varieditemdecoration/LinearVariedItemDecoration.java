package com.donkingliang.varieditemdecoration;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @Author donkingliang QQ:1043214265 github:https://github.com/donkingliang
 * @Description 自定义LinearItemDecoration的基类，根据position返回每个item的间隔大小和装饰Drawable，Drawable可以为null
 * @Date 2020/6/22
 */
public abstract class LinearVariedItemDecoration extends RecyclerView.ItemDecoration implements IVariedItemDecoration {

    /**
     * 是否显示最后一个项的Divider，默认不显示
     */
    private boolean showLastDivider = false;

    private final Rect mBounds = new Rect();

    @Override
    public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        if (!checkLayoutManager(parent)) {
            return;
        }

        canvas.save();

        if (parent.getClipToPadding()) {
            // 设置绘制画布去掉padding
            canvas.clipRect(parent.getPaddingLeft(), parent.getPaddingTop(),
                    parent.getWidth() - parent.getPaddingRight(), parent.getHeight() - parent.getPaddingBottom());
        }

        LinearLayoutManager layoutManager = (LinearLayoutManager) parent.getLayoutManager();
        int orientation = layoutManager.getOrientation();
        int itemCount = layoutManager.getItemCount();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(child);

            // 最后的item是否显示Divider
            if (!isShowLastDivider() && isLastItem(position, itemCount)) {
                continue;
            }

            parent.getDecoratedBoundsWithMargins(child, mBounds);
            if (orientation == RecyclerView.VERTICAL) {
                Drawable divider = getRowDivider(position);
                if (divider != null) {
                    final int bottom = mBounds.bottom + Math.round(child.getTranslationY());
                    final int top = bottom - getRowDividerSize(position);
                    divider.setBounds(mBounds.left, top, mBounds.right, bottom);
                    divider.draw(canvas);
                }
            } else {
                Drawable divider = getColumnDivider(position);
                if (divider != null) {
                    final int right = mBounds.right + Math.round(child.getTranslationX());
                    final int left = right - getColumnDividerSize(position);
                    divider.setBounds(left, mBounds.top, right, mBounds.bottom);
                    divider.draw(canvas);
                }
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

        LinearLayoutManager layoutManager = (LinearLayoutManager) parent.getLayoutManager();
        int orientation = layoutManager.getOrientation();
        int itemCount = layoutManager.getItemCount();
        int position = parent.getChildAdapterPosition(view);

        // 最后的item是否显示Divider
        if (!isShowLastDivider() && isLastItem(position, itemCount)) {
            outRect.set(0, 0, 0, 0);
            return;
        }

        if (orientation == RecyclerView.VERTICAL) {
            outRect.set(0, 0, 0, getRowDividerSize(position));
        } else {
            outRect.set(0, 0, getRowDividerSize(position), 0);
        }
    }

    /**
     * 判断是否是最后一个item，最后一个item不添加Divider
     */
    private boolean isLastItem(int position, int itemCount) {
        return position == itemCount - 1;
    }

    /**
     * 设置是否显示最后一个项的Divider，默认不显示
     */
    public void setShowLastDivider(boolean isShow) {
        showLastDivider = isShow;
    }

    @Override
    public boolean isShowLastDivider() {
        return showLastDivider;
    }

    @Override
    public int getRowDividerSize(int position) {
        return getDividerSize(position);
    }

    @Override
    public Drawable getRowDivider(int position) {
        return getDivider(position);
    }

    @Override
    public int getColumnDividerSize(int position) {
        return getDividerSize(position);
    }

    @Override
    public Drawable getColumnDivider(int position) {
        return getDivider(position);
    }

    @Override
    public boolean checkLayoutManager(RecyclerView view) {
        return view.getLayoutManager() != null && view.getLayoutManager() instanceof LinearLayoutManager;
    }

    /**
     * 根据下标返回Decoration的size
     *
     * @param position
     * @return
     */
    public abstract int getDividerSize(int position);

    /**
     * 根据下标返回Decoration的Drawable
     *
     * @param position
     * @return
     */
    public abstract Drawable getDivider(int position);
}
