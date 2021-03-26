package com.rustfisher.tutorial2020.recycler.multi;

import android.view.View;
import android.widget.TextView;

import com.rustfisher.tutorial2020.R;

public class TwoVH extends BaseVH {
    public TextView tv1;
    public TextView tv2;

    public TwoVH(View itemView, ItemTypeDef.Type type) {
        super(itemView, type);
        tv1 = itemView.findViewById(R.id.tv1);
        tv2 = itemView.findViewById(R.id.tv2);
    }
}
