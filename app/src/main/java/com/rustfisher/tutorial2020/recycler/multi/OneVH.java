package com.rustfisher.tutorial2020.recycler.multi;

import android.view.View;
import android.widget.TextView;

import com.rustfisher.tutorial2020.R;

public class OneVH extends BaseVH {
    public TextView tv1;

    public OneVH(View itemView, ItemTypeDef.Type type) {
        super(itemView, type);
        tv1 = itemView.findViewById(R.id.tv1);
    }
}
