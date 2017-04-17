package com.wifi.hblolj.piechart;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.wifi.hblolj.piechart.bean.MonthData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hblolj on 2017/2/6.
 */

public class PieFragment extends Fragment implements OnChartValueSelectedListener {

    private static final String KEY_DATA = "piefragment_key_data";
    private MonthData mData;
    private PieChart mPieChart;
    private TextView des_tv;

    public static PieFragment newInstance(MonthData data) {

        Bundle args = new Bundle();
//        args.putString(KEY_DATA, data);
        args.putParcelable(KEY_DATA, data);
        PieFragment fragment = new PieFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            mData = arguments.getParcelable(KEY_DATA);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pie_fragment, null);
        mPieChart = (PieChart) view.findViewById(R.id.pichart);
        des_tv = ((TextView) view.findViewById(R.id.des_tv));
        initViews();
        //TextView textView = new TextView(getContext());
        //textView.setText(mData);
        return view;
    }

    private void initViews() {
        setData();
        Description desc = new Description();
        desc.setText("");
        mPieChart.setCenterText(getCenterText());
//        mPieChart.setDrawSliceText(false);
        mPieChart.setDrawEntryLabels(false);
        mPieChart.getData().getDataSet().setDrawValues(false);
        mPieChart.getLegend().setEnabled(false);
        mPieChart.setDescription(desc);
        mPieChart.setRotationEnabled(false);
        mPieChart.setOnChartValueSelectedListener(this);
//        mPieChart.setExtraOffsets(20, 0, 20, 0);
    }

    private CharSequence getCenterText() {
        CharSequence text = "总支出\n" + mData.getSum() + "块";
        SpannableString spannableString = new SpannableString(text);
        spannableString.setSpan(new ForegroundColorSpan(Color.rgb(200 ,200, 200)), 0, 3, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        spannableString.setSpan(new AbsoluteSizeSpan(64, true), 4, text.length()-1, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        return spannableString;
    }

    private void setData() {
        List<Integer> colors = new ArrayList<>();
        colors.add(Color.rgb(0,191,255));
        colors.add(Color.rgb(124,252,0));
        colors.add(Color.rgb(255,99,71));
        List<PieEntry> entrys = new ArrayList<>();
        for (int i=0; i<mData.obj.size(); i++){
            MonthData.PieChart chart = mData.obj.get(i);
            PieEntry pieEntry = new PieEntry(chart.value, chart.title);
            entrys.add(pieEntry);
        }
        PieDataSet dataSet = new PieDataSet(entrys, "");
        dataSet.setColors(colors);
        PieData pieData = new PieData(dataSet);
        pieData.setValueTextSize(20);
        mPieChart.setData(pieData);
    }

    /**
     * 点击突出的监听函数
     * @param e
     * @param h
     */
    @Override
    public void onValueSelected(Entry e, Highlight h) {
        //设置旋转角度
        float proportion = 360f/mData.getSum();
        float x = h.getX();
        MonthData.PieChart cell = mData.obj.get((int) x);
//        float angle = 90 - cell.value * proportion / 2;
        float angle = 90 - cell.value * proportion / 2
                - mData.getSum(((int) h.getX())) * proportion;
        mPieChart.setRotationAngle(angle);
        updateDesText(cell);
    }

    private void updateDesText(MonthData.PieChart cell) {
        des_tv.setVisibility(View.VISIBLE);
        des_tv.setText(cell.title + " : " + cell.value);
    }

    /**
     * 点击收缩的监听函数
     */
    @Override
    public void onNothingSelected() {
        des_tv.setVisibility(View.GONE);
    }
}
