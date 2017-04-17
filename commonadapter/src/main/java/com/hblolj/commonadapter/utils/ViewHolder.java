package com.hblolj.commonadapter.utils;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 抽象出来的公共ViewHolder
 * Created by hblolj on 2017/3/3.
 */

public class ViewHolder {

    private SparseArray<View> views;
    private View convertview;
    private Context mContext;
    private int position;

    private ViewHolder(Context context, int itemLayoutResId, ViewGroup parent, int position) {
        convertview = LayoutInflater.from(context).inflate(itemLayoutResId, parent, false);
        views = new SparseArray<>();
        mContext = context;
        convertview.setTag(this);
        this.position = position;
    }

    public static ViewHolder getInstance(Context context, View convertview, int itemLayoutResId,
                                         ViewGroup parent, int position) {
        if (convertview == null){
            return new ViewHolder(context, itemLayoutResId, parent, position);
        }else {
            ViewHolder holder = (ViewHolder) convertview.getTag();
            holder.position = position;
            return holder;
        }
    }

    public View getConvertview() {
        return convertview;
    }

    public <T extends View> T getView(int ResId) {
        View view = views.get(ResId);
        if (view == null){
            view = getConvertview().findViewById(ResId);
            views.put(ResId, view);
        }
        return (T) view;
    }

    public ViewHolder setText(int ResId, String text){
        TextView tv = getView(ResId);
        tv.setText(text);
        return this;
    }
}
