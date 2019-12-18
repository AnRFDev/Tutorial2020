package com.rustfisher.tutorial2020.recycler;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rustfisher.tutorial2020.AbsActivity;
import com.rustfisher.tutorial2020.R;
import com.rustfisher.tutorial2020.recycler.data.DataTest;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewDemo2Act extends AbsActivity {

    private Adapter mAdapter = new Adapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_recy_2_include);
        initHeader();
        RecyclerView recyclerView = findViewById(R.id.re_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(mAdapter);
        mAdapter.setDataList(genDataTestList());
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = getResources().getDimensionPixelOffset(R.dimen.re_2_gap);
            }
        });

    }

    private void initHeader() {
        View header = findViewById(R.id.header);
        TextView tv1 = header.findViewById(R.id.tv1);
        tv1.setText("时区");
        TextView tv2 = header.findViewById(R.id.tv2);
        tv2.setText("序号");
        TextView tv3 = header.findViewById(R.id.tv3);
        tv3.setText("人员");
        TextView tv4 = header.findViewById(R.id.tv4);
        tv4.setText("数量");
    }

    private List<DataTest> genDataTestList() {
        List<DataTest> list = new ArrayList<>();
        for (int i = 1; i <= 60; i++) {
            DataTest d = new DataTest("时区" + i, i, i, i);
            list.add(d);
        }
        return list;
    }

    private class VH extends RecyclerView.ViewHolder {
        TextView tv1;
        TextView tv2;
        TextView tv3;
        TextView tv4;

        public VH(@NonNull View itemView) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.tv1);
            tv2 = itemView.findViewById(R.id.tv2);
            tv3 = itemView.findViewById(R.id.tv3);
            tv4 = itemView.findViewById(R.id.tv4);
        }
    }

    private class Adapter extends RecyclerView.Adapter<VH> {

        private List<DataTest> dataList = new ArrayList<>();

        public Adapter() {
        }

        public void setDataList(List<DataTest> dataList) {
            this.dataList = dataList;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recy2, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull VH holder, int position) {
            DataTest dataTest = dataList.get(position);
            holder.tv1.setText(dataTest.getTimezone());
            holder.tv2.setText(String.valueOf(dataTest.getNumber()));
            holder.tv3.setText(String.valueOf(dataTest.getPersonCount()));
            holder.tv4.setText(String.valueOf(dataTest.getCount()));
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }
    }

}
