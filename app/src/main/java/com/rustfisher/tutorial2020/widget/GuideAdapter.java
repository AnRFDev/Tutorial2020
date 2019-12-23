package com.rustfisher.tutorial2020.widget;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.rustfisher.tutorial2020.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 导航列表
 * Created on 2019-12-20
 */
public class GuideAdapter extends RecyclerView.Adapter<GuideAdapter.VH> {

    private List<GuideAdapter.OptionItem> dataList;
    private GuideAdapter.OnOptClickListener onItemClickListener;

    public GuideAdapter() {
        this.dataList = new ArrayList<>();
    }

    public void setDataList(List<GuideAdapter.OptionItem> dataList) {
        this.dataList = new ArrayList<>(dataList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GuideAdapter.VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GuideAdapter.VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_opt, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GuideAdapter.VH holder, int position) {
        final GuideAdapter.OptionItem optionItem = dataList.get(position);
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


    public void setOnItemClickListener(GuideAdapter.OnOptClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    public class VH extends RecyclerView.ViewHolder {
        View item;
        TextView tv1;

        public VH(@NonNull View itemView) {
            super(itemView);
            item = itemView;
            tv1 = itemView.findViewById(R.id.tv1);
        }
    }

    public static class OptionItem {
        public int num;
        public String name;

        public OptionItem(int num, String name) {
            this.num = num;
            this.name = name;
        }
    }

    public interface OnOptClickListener {
        void onClick(GuideAdapter.OptionItem item);
    }

}
