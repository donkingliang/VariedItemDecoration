package com.donkingliang.varieditemdecoration.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.donkingliang.varieditemdecoration.GridItemDecoration;
import com.donkingliang.varieditemdecoration.LinearItemDecoration;
import com.donkingliang.varieditemdecoration.demo.adapter.RecyclerViewAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView1 = findViewById(R.id.recyclerView1);
        recyclerView1.setLayoutManager(new GridLayoutManager(this, 3));
        RecyclerViewAdapter adapter1 = new RecyclerViewAdapter(this, "RecyclerView1-");
        recyclerView1.setAdapter(adapter1);
        GridItemDecoration decoration = new GridItemDecoration(40, getResources().getDrawable(R.drawable.row_divider),
                20, getResources().getDrawable(R.drawable.column_divider));
        decoration.setShowLastDivider(true);
        recyclerView1.addItemDecoration(decoration);

    }
}