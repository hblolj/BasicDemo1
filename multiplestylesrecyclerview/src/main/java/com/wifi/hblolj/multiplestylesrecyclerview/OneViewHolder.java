package com.wifi.hblolj.multiplestylesrecyclerview;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by hblolj on 2017/2/4.
 */

public class OneViewHolder extends AbstrctViewHolder<DataModelOne> {

    public ImageView avater;
    public TextView name;

    public OneViewHolder(View itemView) {
        super(itemView);
        avater = (ImageView) itemView.findViewById(R.id.avater);
        name = (TextView) itemView.findViewById(R.id.name);
        itemView.setBackgroundColor(Color.GRAY);
    }

    @Override
    public void bindData(DataModelOne data) {
        avater.setImageResource(data.avater);
        name.setText(data.name);
    }
}
