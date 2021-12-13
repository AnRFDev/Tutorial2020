package com.rustfisher.tutorial2020.dialog;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rustfisher.baselib.AbsActivity;
import com.rustfisher.tutorial2020.R;
import com.rustfisher.tutorial2020.dialog.widget.AdjustLocationDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DialogLocationAct extends AbsActivity {

    private Adapter mAdapter = new Adapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_dialog_location);

        RecyclerView recyclerView = findViewById(R.id.re_view);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add("第" + i + "个子项");
        }
        mAdapter.setDataList(list);
        mAdapter.setOnItemClick(new OnItemClick() {
            @Override
            public void onClick(int rawX, int rawY) {
                int etHeight = getResources().getDimensionPixelOffset(R.dimen.adjust_dialog_et_height);
                AdjustLocationDialog.newDialog(rawX, rawY - etHeight).show(getSupportFragmentManager(), "show_dialog");
            }
        });
    }

    private static class VH extends RecyclerView.ViewHolder {
        TextView tv;

        public VH(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
        }
    }

    private class Adapter extends RecyclerView.Adapter<VH> {
        private float downX;
        private float downY;
        private float rawX;
        private float rawY;

        private OnItemClick onItemClick;
        private List<String> dataList = new ArrayList<>();

        public void setDataList(List<String> dataList) {
            this.dataList = dataList;
            notifyDataSetChanged();
        }

        public void setOnItemClick(OnItemClick onItemClick) {
            this.onItemClick = onItemClick;
        }

        @NonNull
        @Override
        public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_1_tv, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull VH holder, int position) {
            holder.tv.setText(dataList.get(position));
            holder.tv.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    downX = event.getX();
                    downY = event.getY();
                    rawX = event.getRawX();
                    rawY = event.getRawY();
                    Log.d(TAG, String.format(Locale.CHINA, "x,y {%.2f, %.2f}; raw-x,y {%.2f,%.2f}",
                            downX, downY, rawX, rawY));
                    return false;
                }
            });
            holder.tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClick != null) {
                        onItemClick.onClick((int) rawX, (int) rawY);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }
    }

    public interface OnItemClick {
        void onClick(int rawX, int rawY);
    }
}
