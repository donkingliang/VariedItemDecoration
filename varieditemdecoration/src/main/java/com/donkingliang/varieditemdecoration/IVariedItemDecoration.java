package com.donkingliang.varieditemdecoration;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;

/**
 * @Author teach liang
 * @Description 定义VariedItemDecoration的接口模板
 * @Date 2020/6/22
 */
public interface IVariedItemDecoration {

    /**
     * 是否显示最后一行的行Decoration
     */
    boolean isShowLastDivider();

    /**
     * 根据下标返回行Decoration的size
     *
     * @param position
     * @return
     */
    int getRowDividerSize(int position);

    /**
     * 根据下标返回行Decoration的Drawable
     *
     * @param position
     * @return
     */
    Drawable getRowDivider(int position);

    /**
     * 根据下标返回列Decoration的size
     *
     * @param position
     * @return
     */
    int getColumnDividerSize(int position);

    /**
     * 根据下标返回列Decoration的Drawable
     *
     * @param position
     * @return
     */
    Drawable getColumnDivider(int position);

    /**
     * 检测是否是可支持的LayoutManager
     *
     * @param view
     * @return
     */
    boolean checkLayoutManager(RecyclerView view);

}
