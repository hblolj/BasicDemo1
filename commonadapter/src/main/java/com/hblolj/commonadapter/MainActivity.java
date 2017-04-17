package com.hblolj.commonadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.hblolj.commonadapter.adapter.StandardAdapter;
import com.hblolj.commonadapter.adapter.StandardAdapterPlus;
import com.hblolj.commonadapter.adapter.StandardAdapterPlusPlus;
import com.hblolj.commonadapter.bean.Bean;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private List<Bean> mBeanList;
    private StandardAdapter mStandardAdapter;
    private StandardAdapterPlus mStandardAdapterPlus;
    private StandardAdapterPlusPlus mStandardAdapterPlusPlus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDatas();
        initViews();
        mListView.setAdapter(mStandardAdapterPlusPlus);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, position+"", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initViews() {
        mListView = ((ListView) findViewById(R.id.id_lv));
    }

    private void initDatas() {
        mBeanList = new ArrayList<>();
        for (int i=0; i<10; i++){
            Bean bean = new Bean("Android适配器" + i, "Android万能适配器Android万能适配器Android万能适配器",
                    "2016-3-3", "10086");
            mBeanList.add(bean);
        }
//        mStandardAdapter = new StandardAdapter(this, mBeanList);
//        mStandardAdapterPlus = new StandardAdapterPlus(this, mBeanList);
        mStandardAdapterPlusPlus = new StandardAdapterPlusPlus(mBeanList, this);
    }
}
