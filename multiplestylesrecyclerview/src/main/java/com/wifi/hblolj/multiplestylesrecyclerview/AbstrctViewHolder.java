package com.wifi.hblolj.multiplestylesrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by hblolj on 2017/2/4.
 */

public abstract class AbstrctViewHolder<T> extends RecyclerView.ViewHolder{

    public AbstrctViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bindData(T data);
}
