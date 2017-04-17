package com.hblolj.commonadapter.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hblolj on 2017/3/3.
 */

public abstract class CommonAdapter<T> extends BaseAdapter {

    protected List<T> mDatas = new ArrayList<>();
    protected Context mContext;
    protected LayoutInflater mInflater;
    private int itemLayoutResId;

    public CommonAdapter(List<T> datas, Context context, int itemLayoutResId) {
        mDatas.clear();
        mDatas.addAll(datas);
        mContext = context;
        mInflater = LayoutInflater.from(context);
        this.itemLayoutResId = itemLayoutResId;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder = ViewHolder.getInstance(mContext, convertView, itemLayoutResId, parent,
                position);
        convert(holder, getItem(position));
        return holder.getConvertview();
    }

    protected abstract void convert(ViewHolder holder, T data);
}
