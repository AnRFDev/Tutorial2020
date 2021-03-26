package com.rustfisher.tutorial2020.recycler.multi;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseVH extends RecyclerView.ViewHolder {
    private ItemTypeDef.Type type;
    public View root;

    public BaseVH(View itemView, ItemTypeDef.Type type) {
        super(itemView);
        this.root = itemView;
        this.type = type;
    }

    public ItemTypeDef.Type getType() {
        return type;
    }
}