package com.wifi.hblolj.multiplestylesrecyclerview;

import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private DemoAdapter adapter;

    private int colors[] = {android.R.color.holo_red_dark,
            android.R.color.holo_blue_dark,
            android.R.color.holo_orange_dark};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int type = mRecyclerView.getAdapter().getItemViewType(position);
                if (type == DataModel.TYPE_THREE){
                    return gridLayoutManager.getSpanCount();
                }else {
                    return 1;
                }
            }
        });
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                GridLayoutManager.LayoutParams layoutParams = (GridLayoutManager.LayoutParams)
                        view.getLayoutParams();
                int spanSize = layoutParams.getSpanSize();//当前Item所占的列数
                int spanIndex = layoutParams.getSpanIndex();//当前Item的位置,最左边为0
                outRect.top = 20;
                //当前Item所占的列数与GridView的总列数是否相等
                //相等则只有一列，不等有多列
                //在这里不等，有两列
                if (spanSize != gridLayoutManager.getSpanCount()){
                    if (spanIndex == 1){
                        outRect.left = 10;
                    }else if (spanIndex == 0){
                        outRect.right = 10;
                    }
                }
            }
        });
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
//                false));

        adapter = new DemoAdapter(this);
        mRecyclerView.setAdapter(adapter);

        initDatas();
    }

    private void initDatas() {
        //模拟数据
        List<DataModel> dataModels = new ArrayList<>();
        for (int i=0; i<20; i++){
            int type = (int) ((Math.random() * 3) + 1);
            DataModel dataModel = new DataModel();
            dataModel.type = type;
            dataModel.avater = colors[type - 1];
            dataModel.content = "content : " + i;
            dataModel.name = "name : " + i;
            dataModel.bg_avater = colors[(type + 1) % 3];
            dataModels.add(dataModel);
        }

        List<DataModelThree> modelThrees = new ArrayList<>();
        for (int i=0; i<10; i++){
            DataModelThree dataModel = new DataModelThree();
            dataModel.avater = colors[2];
            dataModel.content = "content : " + i;
            dataModel.name = "name : " + i;
            dataModel.bg_avater = colors[0];
            modelThrees.add(dataModel);
        }

        List<DataModelTwo> modelTwos = new ArrayList<>();
        for (int i=0; i<10; i++){
            DataModelTwo dataModel = new DataModelTwo();
            dataModel.avater = colors[1];
            dataModel.content = "content : " + i;
            dataModel.name = "name : " + i;
            modelTwos.add(dataModel);
        }

        List<DataModelOne> modelOnes = new ArrayList<>();
        for (int i=0; i<10; i++){
            DataModelOne dataModel = new DataModelOne();
            dataModel.avater = colors[0];
            dataModel.name = "name : " + i;
            modelOnes.add(dataModel);
        }

        adapter.addData2(modelOnes, modelTwos, modelThrees);
//        adapter.addData(dataModels);
        adapter.notifyDataSetChanged();
    }
}
