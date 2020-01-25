package com.rustfisher.tutorial2020.recycler;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
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
import java.util.Arrays;
import java.util.List;

/**
 * Created on 2019-12-14
 */
public class ReGuideAct extends AbsActivity {
    private static final int OPT_1 = 1;
    private static final int OPT_2 = 2;
    private static final int OPT_INPUT_DATA = 3;

    GuideAdapter mGuideAdapter;
    private List<OptionItem> mOptions = Arrays.asList(new OptionItem(OPT_1, "列表1 - 字母"),
            new OptionItem(OPT_2, "列表2"),
            new OptionItem(OPT_INPUT_DATA, "输入数据的列表"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_re_guide);

        mGuideAdapter = new GuideAdapter();
        mGuideAdapter.setDataList(mOptions);
        RecyclerView letterReView = findViewById(R.id.re_view);
        letterReView.setAdapter(mGuideAdapter);
        letterReView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        mGuideAdapter.setOnItemClickListener(new OnOptClickListener() {
            @Override
            public void onClick(OptionItem item) {
                switch (item.num) {
                    case OPT_1:
                        startActivity(new Intent(getApplicationContext(), RecyclerViewDemoActivity.class));
                        break;
                    case OPT_2:
                        startActivity(new Intent(getApplicationContext(), RecyclerViewDemo2Act.class));
                        break;
                    case OPT_INPUT_DATA:
                        startInputData();
                        break;
                }
            }

        });
        letterReView.addItemDecoration(new RecyclerView.ItemDecoration() {

            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.bottom = 4;
            }
        });
    }

    private void startInputData() {
        Intent intent = new Intent(getApplicationContext(), RecyclerViewDemo2Act.class);
        DataTest out = new DataTest("input time", 233, 666, 999);
        Log.d(TAG, "startInputData: sending object: " + out);
        intent.putExtra(RecyclerViewDemo2Act.K_INPUT_DATA, out);
        startActivity(intent);
    }

    private class GuideAdapter extends RecyclerView.Adapter<VH> {

        private List<OptionItem> dataList;
        private OnOptClickListener onItemClickListener;

        public GuideAdapter() {
            this.dataList = new ArrayList<>();
        }

        public void setDataList(List<OptionItem> dataList) {
            this.dataList = new ArrayList<>(dataList);
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_opt, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull VH holder, int position) {
            final OptionItem optionItem = dataList.get(position);
            holder.tv1.setText(optionItem.name);
            holder.item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onClick(optionItem);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }


        public void setOnItemClickListener(OnOptClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
        }
    }

    private class VH extends RecyclerView.ViewHolder {
        View item;
        TextView tv1;

        public VH(@NonNull View itemView) {
            super(itemView);
            item = itemView;
            tv1 = itemView.findViewById(R.id.tv1);
        }
    }

    private static class OptionItem {
        public int num;
        public String name;

        public OptionItem(int num, String name) {
            this.num = num;
            this.name = name;
        }
    }

    public interface OnOptClickListener {
        void onClick(OptionItem item);
    }
}
