package com.rustfisher.baselib;

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
public class GuideAdapter extends RecyclerView.Adapter<GuideAdapter.VH> {

    private List<GuideAdapter.OptionItem> dataList;
    private GuideAdapter.OnOptClickListener onItemClickListener;
    private OnClzListener onClzListener;

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
        return new GuideAdapter.VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.base_item_opt, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GuideAdapter.VH holder, int position) {
        final GuideAdapter.OptionItem optionItem = dataList.get(position);
        holder.iv1.setImageResource(optionItem.headIvResId);
        holder.tv1.setText(optionItem.name);
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onClick(optionItem);
                }
                if (onClzListener != null && optionItem.useClz) {
                    onClzListener.onClick(optionItem.clz);
                }
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


    public void setOnItemClickListener(GuideAdapter.OnOptClickListener onItemClickListener) {
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
            iv1 = itemView.findViewById(R.id.iv1);
            tv1 = itemView.findViewById(R.id.tv1);
            tv2 = itemView.findViewById(R.id.tv2);
        }
    }

    public static class OptionItem {
        public int num;
        public String name;
        public String desc;
        public boolean useClz = false;
        public Class clz;
        public int headIvResId = R.drawable.item_type_code;

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
        void onClick(GuideAdapter.OptionItem item);
    }

    public interface OnClzListener {
        void onClick(Class actClz);
    }

}
