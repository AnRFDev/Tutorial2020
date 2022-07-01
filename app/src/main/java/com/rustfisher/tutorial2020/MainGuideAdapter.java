package com.rustfisher.tutorial2020;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 导航列表
 * Created on 2019-12-20
 */
public class MainGuideAdapter extends RecyclerView.Adapter<MainGuideAdapter.VH> {

    private List<MainGuideAdapter.OptionItem> dataList;
    private MainGuideAdapter.OnOptClickListener onItemClickListener;
    private OnClzListener onClzListener;

    public MainGuideAdapter() {
        this.dataList = new ArrayList<>();
    }

    public void setDataList(List<MainGuideAdapter.OptionItem> dataList) {
        this.dataList = new ArrayList<>(dataList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MainGuideAdapter.VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainGuideAdapter.VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.main_list_item_opt, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainGuideAdapter.VH holder, int position) {
        final MainGuideAdapter.OptionItem optionItem = dataList.get(position);
        holder.iv1.setImageResource(optionItem.headIvResId);
        holder.tv1.setText(optionItem.name);
        holder.item.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onClick(optionItem);
            }
            if (onClzListener != null && optionItem.useClz) {
                onClzListener.onClick(optionItem.clz);
            }
        });
        if (TextUtils.isEmpty(optionItem.desc)) {
            holder.tv2.setVisibility(View.GONE);
        } else {
            holder.tv2.setVisibility(View.VISIBLE);
            holder.tv2.setText(optionItem.desc);
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    public void setOnItemClickListener(MainGuideAdapter.OnOptClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnClzListener(OnClzListener onClzListener) {
        this.onClzListener = onClzListener;
    }

    public static class VH extends RecyclerView.ViewHolder {
        View item;
        ImageView iv1;
        TextView tv1;
        TextView tv2;

        public VH(@NonNull View itemView) {
            super(itemView);
            item = itemView;
            iv1 = itemView.findViewById(com.rustfisher.baselib.R.id.iv1);
            tv1 = itemView.findViewById(com.rustfisher.baselib.R.id.tv1);
            tv2 = itemView.findViewById(com.rustfisher.baselib.R.id.tv2);
        }
    }

    public static class OptionItem {
        public int num;
        public String name;
        public String desc;
        public boolean useClz = false;
        public Class clz;
        public int headIvResId = com.rustfisher.baselib.R.drawable.item_type_code;

        public OptionItem(int num, String name) {
            this.num = num;
            this.name = name;
        }

        public OptionItem(String name, boolean useClz, Class clz) {
            this.useClz = useClz;
            this.name = name;
            this.clz = clz;
        }

        public OptionItem(String name, String desc, boolean useClz, Class clz) {
            this.name = name;
            this.desc = desc;
            this.useClz = useClz;
            this.clz = clz;
        }

        public OptionItem(String name, String desc, boolean useClz, Class clz, int headIvResId) {
            this.name = name;
            this.desc = desc;
            this.useClz = useClz;
            this.clz = clz;
            this.headIvResId = headIvResId;
        }
    }

    public interface OnOptClickListener {
        void onClick(MainGuideAdapter.OptionItem item);
    }

    public interface OnClzListener {
        void onClick(Class actClz);
    }

}
