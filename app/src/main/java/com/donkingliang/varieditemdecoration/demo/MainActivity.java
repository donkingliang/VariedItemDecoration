package com.donkingliang.varieditemdecoration.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.donkingliang.varieditemdecoration.GridItemDecoration;
import com.donkingliang.varieditemdecoration.LinearItemDecoration;
import com.donkingliang.varieditemdecoration.demo.adapter.HorizontalAdapter;
import com.donkingliang.varieditemdecoration.demo.adapter.VerticalAdapter;
import com.donkingliang.varieditemdecoration.demo.decoration.MyGridItemDecoration;
import com.donkingliang.varieditemdecoration.demo.decoration.MyLinearItemDecoration;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        // 显示最后item的Divider，默认false
//        decoration.setShowLastDivider(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_ment, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // 移除前面的ItemDecoration
        removeItemDecoration();

        switch (item.getItemId()) {
            case R.id.v_linear:

                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                recyclerView.setAdapter(new VerticalAdapter(this, "Item - "));

                // 添加Decoration
                recyclerView.addItemDecoration(new LinearItemDecoration(20, getResources().getDrawable(R.drawable.row_divider)));
                return true;

            case R.id.h_linear:
                recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
                recyclerView.setAdapter(new HorizontalAdapter(this, "Item - "));

                // 添加Decoration
                recyclerView.addItemDecoration(new LinearItemDecoration(20, getResources().getDrawable(R.drawable.row_divider)));
                return true;

            case R.id.m_linear:
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                recyclerView.setAdapter(new VerticalAdapter(this, "Item - "));

                // 添加Decoration
                recyclerView.addItemDecoration(new MyLinearItemDecoration(this));
                return true;

            case R.id.v_grid:
                recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
                recyclerView.setAdapter(new VerticalAdapter(this, "Item - "));

                // 添加Decoration
                recyclerView.addItemDecoration(new GridItemDecoration(20, getResources().getDrawable(R.drawable.row_divider),
                        20, getResources().getDrawable(R.drawable.column_divider)));
                return true;

            case R.id.h_grid:
                recyclerView.setLayoutManager(new GridLayoutManager(this, 3, GridLayoutManager.HORIZONTAL, false));
                recyclerView.setAdapter(new HorizontalAdapter(this, "Item - "));

                // 添加Decoration
                recyclerView.addItemDecoration(new GridItemDecoration(20, getResources().getDrawable(R.drawable.row_divider),
                        20, getResources().getDrawable(R.drawable.column_divider)));
                return true;

            case R.id.m_grid:
                recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
                recyclerView.setAdapter(new VerticalAdapter(this, "Item - "));

                // 添加Decoration
                recyclerView.addItemDecoration(new MyGridItemDecoration(this));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void removeItemDecoration() {
        if (recyclerView.getItemDecorationCount() > 0) {
            recyclerView.removeItemDecoration(recyclerView.getItemDecorationAt(0));
        }
    }
}