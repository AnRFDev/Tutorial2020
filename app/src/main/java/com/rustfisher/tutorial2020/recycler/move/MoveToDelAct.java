package com.rustfisher.tutorial2020.recycler.move;

import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rustfisher.tutorial2020.AbsActivity;
import com.rustfisher.tutorial2020.R;
import com.rustfisher.tutorial2020.databinding.ActRecyMoveBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 侧滑删除
 * Created on 2020-3-24
 */
public class MoveToDelAct extends AbsActivity {

    private Adapter mAdapter = new Adapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActRecyMoveBinding binding = DataBindingUtil.setContentView(this, R.layout.act_recy_move);

        binding.reView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        binding.reView.setAdapter(mAdapter);

        binding.reView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = getResources().getDimensionPixelOffset(R.dimen.re_2_gap);
            }
        });

        List<Data> dataList = new ArrayList<>();
        int color1 = Color.parseColor("#03A9F4");
        int color2 = Color.parseColor("#2196F3");
        int c = 0;
        for (String s : "an.rustfisher.com".split("")) {
            if (!TextUtils.isEmpty(s)) {
                c++;
                int color = color1;
                if (c % 2 == 0) {
                    color = color2;
                }
                dataList.add(new Data(s, color));
            }
        }

        mAdapter.setDataList(dataList);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ReViewTouchCallback(new IActionListener() {
            @Override
            public boolean onItemMove(int src, int target) {
                Log.d(TAG, "onItemMove: " + src + " -> " + target);
                Collections.swap(mAdapter.getDataList(), src, target);
                mAdapter.notifyItemMoved(src, target);
                return true;
            }

            @Override
            public void onItemRemove(int pos) {
                mAdapter.getDataList().remove(pos);
                mAdapter.notifyItemRemoved(pos);
            }
        }));
        itemTouchHelper.attachToRecyclerView(binding.reView);
    }

    public class Data {
        public String str;
        public int bgColor;

        public Data(String str, int bgColor) {
            this.str = str;
            this.bgColor = bgColor;
        }
    }

    private class VH extends RecyclerView.ViewHolder {
        TextView tv1;

        public VH(@NonNull View itemView) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.tv);
        }
    }

    private class Adapter extends RecyclerView.Adapter<VH> {

        private List<Data> dataList = new ArrayList<>();

        public Adapter() {
        }

        public List<Data> getDataList() {
            return dataList;
        }

        public void setDataList(List<Data> dataList) {
            this.dataList = dataList;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recy_move, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull VH holder, int position) {
            Data data = dataList.get(position);
            holder.tv1.setText(data.str);
            holder.tv1.setBackgroundColor(data.bgColor);
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }
    }

}
