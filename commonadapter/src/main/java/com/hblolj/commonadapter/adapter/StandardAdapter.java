package com.hblolj.commonadapter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hblolj.commonadapter.R;
import com.hblolj.commonadapter.bean.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hblolj on 2017/3/3.
 */

public class StandardAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<Bean> mBeens = new ArrayList<>();

    public StandardAdapter(Context context, List<Bean> beens) {
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
        ViewHolder holder = null;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_list, parent, false);
            holder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            holder.tv_content = (TextView) convertView.findViewById(R.id.tv_content);
            holder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            holder.tv_phone = (TextView) convertView.findViewById(R.id.tv_phone);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        Bean bean = mBeens.get(position);
        holder.tv_title.setText(bean.getTitle());
        holder.tv_content.setText(bean.getContent());
        holder.tv_time.setText(bean.getTime());
        holder.tv_phone.setText(bean.getPhone());

        return convertView;
    }

    class ViewHolder{
        TextView tv_title;
        TextView tv_content;
        TextView tv_time;
        TextView tv_phone;
    }
}
