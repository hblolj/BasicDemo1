package com.wifi.hblolj.multiplestylesrecyclerview;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by hblolj on 2017/2/4.
 */

public class ThreeViewHolder extends AbstrctViewHolder<DataModelThree> {

    public ImageView avater;
    public ImageView bg_avater;
    public TextView name;
    public TextView content;

    public ThreeViewHolder(View itemView) {
        super(itemView);
        avater = (ImageView) itemView.findViewById(R.id.avater);
        bg_avater = (ImageView) itemView.findViewById(R.id.avater_bg);
        name = (TextView) itemView.findViewById(R.id.name);
        content = (TextView) itemView.findViewById(R.id.content);
        itemView.setBackgroundColor(Color.WHITE);
    }

    @Override
    public void bindData(DataModelThree data) {
        avater.setImageResource(data.avater);
        bg_avater.setImageResource(data.bg_avater);
        name.setText(data.name);
        content.setText(data.content);
    }
}
