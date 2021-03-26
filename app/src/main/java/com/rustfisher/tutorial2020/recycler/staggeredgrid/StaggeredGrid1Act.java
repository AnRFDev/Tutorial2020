package com.rustfisher.tutorial2020.recycler.staggeredgrid;

import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.rustfisher.tutorial2020.AbsActivity;
import com.rustfisher.tutorial2020.R;
import com.rustfisher.tutorial2020.databinding.ActRecyStaggeredGrid1Binding;

import java.util.ArrayList;
import java.util.List;

/**
 * 瀑布流
 * Created on 2020-3-25
 */
public class StaggeredGrid1Act extends AbsActivity {

    private Adapter mAdapter = new Adapter();
    private int color1 = Color.parseColor("#1976D2");
    private int color2 = Color.parseColor("#42A5F5");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActRecyStaggeredGrid1Binding binding = DataBindingUtil.setContentView(this, R.layout.act_recy_staggered_grid_1);

        binding.reView.setLayoutManager(new StaggeredGridLayoutManager(2, RecyclerView.VERTICAL));
        binding.reView.setAdapter(mAdapter);

        binding.reView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                int gap = getResources().getDimensionPixelOffset(R.dimen.re_2_gap) / 2;
                outRect.top = gap;
                outRect.left = gap;
                outRect.right = gap;
                outRect.bottom = gap;
            }
        });

        List<Data> dataList = new ArrayList<>();

        int c = 0;
        for (int i = 0; i <= 31; i++) {
            c++;
            String s = "data " + i;
            int color = color1;
            if (c % 2 == 0) {
                color = color2;
                s = "data\nnumber\n" + i;
            } else if (c == 11) {
                s = "Rust Fisher\nRecyclerView\ndemo\nStaggered\nGird\nLayoutManager";
            }
            dataList.add(new Data(s, color));
        }

        mAdapter.setDataList(dataList);
        setOnClickListener(mOnClickListener, binding.headAdd1, binding.headMinus1, binding.tailAdd1,
                binding.tailMinus1, binding.head3Add1, binding.head3Minus1);
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.head_add1:
                    mAdapter.getDataList().add(0, new Data("data\n" + System.currentTimeMillis(), color1));
                    mAdapter.notifyItemInserted(0);
                    break;
                case R.id.head_minus1:
                    mAdapter.getDataList().remove(0);
                    mAdapter.notifyItemRemoved(0);
                    break;
                case R.id.tail_add1:
                    mAdapter.getDataList().add(new Data("data\n" + System.currentTimeMillis(), color2));
                    mAdapter.notifyItemInserted(mAdapter.getDataList().size() - 1);
                    break;
                case R.id.tail_minus1:
                    int tail = mAdapter.getItemCount() - 1;
                    mAdapter.getDataList().remove(tail);
                    mAdapter.notifyItemRemoved(tail);
                    break;
                case R.id.head3_add1:
                    mAdapter.getDataList().add(3, new Data("data\n" + System.currentTimeMillis(), color1));
                    mAdapter.notifyItemInserted(3);
                    break;
                case R.id.head3_minus1:
                    mAdapter.getDataList().remove(3);
                    mAdapter.notifyItemRemoved(3);
                    break;
            }
        }
    };

    public class Data {
        public String str;
        public int bgColor;

        public Data() {
        }

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
