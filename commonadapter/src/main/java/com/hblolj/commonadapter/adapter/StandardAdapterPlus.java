package com.hblolj.commonadapter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hblolj.commonadapter.R;
import com.hblolj.commonadapter.bean.Bean;
import com.hblolj.commonadapter.utils.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hblolj on 2017/3/3.
 */

public class StandardAdapterPlus extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<Bean> mBeens = new ArrayList<>();

    public StandardAdapterPlus(Context context, List<Bean> beens) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mBeens = beens;
    }

    @Override
    public int getCount() {
        return mBeens.size();
    }

    @Override
    public Object getItem(int position) {
        return mBeens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = ViewHolder.getInstance(mContext, convertView, R.layout.item_list, parent
                , position);

        TextView tv_title = holder.getView(R.id.tv_title);
        TextView tv_content = holder.getView(R.id.tv_content);
        TextView tv_time = holder.getView(R.id.tv_time);
        TextView tv_phone = holder.getView(R.id.tv_phone);

        Bean bean = mBeens.get(position);
        tv_title.setText(bean.getTitle());
        tv_content.setText(bean.getContent());
        tv_time.setText(bean.getTime());
        tv_phone.setText(bean.getPhone());

        return holder.getConvertview();
    }
}
