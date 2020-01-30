package com.rustfisher.tutorial2020.recycler.multi;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rustfisher.tutorial2020.R;

import java.util.ArrayList;
import java.util.List;

public class MultiItemAdapter extends RecyclerView.Adapter<BaseVH> {

    private List<BaseMultiData> dataList = new ArrayList<>();

    public void setDataList(List<BaseMultiData> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BaseVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (ItemTypeDef.Type.getItemTypeByCode(viewType)) {
            case ONE_TEXT:
                return new OneVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_multi_1, parent, false), ItemTypeDef.Type.ONE_TEXT);
            case TWO_TEXT:
                return new TwoVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_multi_2, parent, false), ItemTypeDef.Type.TWO_TEXT);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseVH holder, int position) {
        BaseMultiData data = dataList.get(position);
        switch (holder.getType()) {
            case ONE_TEXT:
                ((OneVH) holder).tv1.setText(((MultiData1) data).getStr1());
                break;
            case TWO_TEXT:
                TwoVH twoVH = (TwoVH) holder;
                MultiData2 data2 = (MultiData2) data;
                twoVH.tv1.setText(data2.getStr1());
                twoVH.tv2.setText(data2.getStr2());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return dataList.get(position).typeCode();
    }
}
