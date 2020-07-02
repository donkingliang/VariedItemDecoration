package com.donkingliang.varieditemdecoration.demo.decoration;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.donkingliang.varieditemdecoration.LinearVariedItemDecoration;
import com.donkingliang.varieditemdecoration.demo.R;

/**
 * @Author donkingliang
 * @Description 自定义LinearItemDecoration
 * @Date 2020/7/1
 */
public class MyLinearItemDecoration extends LinearVariedItemDecoration {

    private Drawable[] drawables = new Drawable[5];

    public MyLinearItemDecoration(Context context) {

        drawables[0] =context.getResources().getDrawable(R.drawable.row_divider_1);
        drawables[1] =context.getResources().getDrawable(R.drawable.row_divider_2);
        drawables[2] =context.getResources().getDrawable(R.drawable.row_divider_3);
        drawables[3] =context.getResources().getDrawable(R.drawable.row_divider_4);
        drawables[4] =context.getResources().getDrawable(R.drawable.row_divider_5);
    }

    @Override
    public int getDividerSize(int position) {

        // 根据position返回间隔的大小
        if (position % 3 == 0){
            return 40;
        } else {
            return 20;
        }
    }

    @Override
    public Drawable getDivider(int position) {
        // 根据position返回Drawable  可以为null
        return drawables[position % 5];
    }
}
