package com.rustfisher.tutorial2020.recycler.move;

public interface IActionListener {
    boolean onItemMove(int pos, int targetPos);

    void onItemRemove(int pos);
}
