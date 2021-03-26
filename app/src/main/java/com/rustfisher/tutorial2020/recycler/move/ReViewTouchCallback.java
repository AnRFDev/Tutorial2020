package com.rustfisher.tutorial2020.recycler.move;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class ReViewTouchCallback extends ItemTouchHelper.Callback {

    private final IActionListener mIActionListener;

    public ReViewTouchCallback(IActionListener IActionListener) {
        mIActionListener = IActionListener;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN; // 上下拖动
        int swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT; // 向左滑动
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return mIActionListener.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        mIActionListener.onItemRemove(viewHolder.getAdapterPosition());
    }
}