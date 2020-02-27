package com.rustfisher.tutorial2020.text;

import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rustfisher.tutorial2020.R;

import java.util.ArrayList;
import java.util.List;

public class TextAdapter extends RecyclerView.Adapter<TextAdapter.SVH> {
    private List<SpannableString> dataList = new ArrayList<>();

    public void setDataList(List<SpannableString> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    public void addData(SpannableString s) {
        dataList.add(s);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_spannable_str_1, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SVH holder, int position) {
        holder.tv.setText(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class SVH extends RecyclerView.ViewHolder {
        TextView tv;

        public SVH(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
        }
    }

}
