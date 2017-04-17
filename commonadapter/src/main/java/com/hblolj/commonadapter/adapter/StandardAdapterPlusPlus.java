package com.hblolj.commonadapter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hblolj.commonadapter.R;
import com.hblolj.commonadapter.bean.Bean;
import com.hblolj.commonadapter.utils.CommonAdapter;
import com.hblolj.commonadapter.utils.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hblolj on 2017/3/3.
 */

public class StandardAdapterPlusPlus extends CommonAdapter<Bean> {

    public StandardAdapterPlusPlus(List<Bean> datas, Context context) {
        super(datas, context, R.layout.item_list);
    }

    @Override
    protected void convert(ViewHolder holder, Bean bean) {
        holder.setText(R.id.tv_title, bean.getTitle())
                .setText(R.id.tv_content, bean.getContent())
                .setText(R.id.tv_time, bean.getTime())
                .setText(R.id.tv_phone, bean.getPhone());
    }
}
