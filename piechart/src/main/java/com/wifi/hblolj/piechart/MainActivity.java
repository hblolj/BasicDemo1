package com.wifi.hblolj.piechart;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wifi.hblolj.piechart.bean.MonthData;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager viewpager;
    private String myJson = "[{\"date\":\"2016年5月\",\"obj\":[{\"title\":\"外卖\",\"value\":34}," +
            "{\"title\":\"娱乐\",\"value\":21},{\"title\":\"其他\",\"value\":45}]}," +
            "{\"date\":\"2016年6月\",\"obj\":[{\"title\":\"外卖\",\"value\":32}," +
            "{\"title\":\"娱乐\",\"value\":22},{\"title\":\"其他\",\"value\":42}]}," +
            "{\"date\":\"2016年7月\",\"obj\":[{\"title\":\"外卖\",\"value\":34}," +
            "{\"title\":\"娱乐\",\"value\":123},{\"title\":\"其他\",\"value\":24}]}," +
            "{\"date\":\"2016年8月\",\"obj\":[{\"title\":\"外卖\",\"value\":145}," +
            "{\"title\":\"娱乐\",\"value\":123},{\"title\":\"其他\",\"value\":124}]}]";
    private ArrayList<MonthData> mData;
    private Button jump_next;
    private Button jump_pre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewpager = (ViewPager) findViewById(R.id.vp_main);
        jump_next = ((Button) findViewById(R.id.btn_next));
        jump_pre = ((Button) findViewById(R.id.btn_pre));

        jump_next.setOnClickListener(this);
        jump_pre.setOnClickListener(this);
        initDatas();
        initViews();
    }

    private void initDatas() {
        Gson gson = new Gson();
        mData = gson.fromJson(myJson, new TypeToken<ArrayList<MonthData>>(){}.getType());
    }

    private void initViews() {
        viewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return PieFragment.newInstance(mData.get(position));
            }

            @Override
            public int getCount() {
                return mData.size();
            }
        });

        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                updateJumpText();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        updateJumpText();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_next:
                if (viewpager.getCurrentItem() != viewpager.getAdapter().getCount())
                viewpager.setCurrentItem(viewpager.getCurrentItem()+1);
                break;
            case R.id.btn_pre:
                if (viewpager.getCurrentItem() != 0)
                viewpager.setCurrentItem(viewpager.getCurrentItem()-1);
                break;
        }
        updateJumpText();
    }

    private void updateJumpText() {
        if (viewpager.getCurrentItem() != viewpager.getAdapter().getCount()-1) {
            jump_next.setText(handlerDate(mData.get(viewpager.getCurrentItem()+1).date));
        }else {
            jump_next.setText("没有了!");
        }

        if (viewpager.getCurrentItem() != 0) {
            jump_pre.setText(handlerDate(mData.get(viewpager.getCurrentItem()-1).date));
        }else {
            jump_pre.setText("没有了!");
        }
    }

    private String handlerDate(String date) {
        return date.substring(date.indexOf("年")+1);
    }
}
