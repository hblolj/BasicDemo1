package com.wifi.hblolj.multiplestylesrecyclerview;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by hblolj on 2017/2/4.
 */

public class TwoViewHolder extends AbstrctViewHolder<DataModelTwo> {

    public ImageView avater;
    public TextView name;
    public TextView content;

    public TwoViewHolder(View itemView) {
        super(itemView);
        avater = (ImageView) itemView.findViewById(R.id.avater);
        name = (TextView) itemView.findViewById(R.id.name);
        content = (TextView) itemView.findViewById(R.id.content);
        itemView.setBackgroundColor(Color.GREEN);
    }

    @Override
    public void bindData(DataModelTwo data) {
        avater.setImageResource(data.avater);
        name.setText(data.name);
        content.setText(data.content);
    }
}
