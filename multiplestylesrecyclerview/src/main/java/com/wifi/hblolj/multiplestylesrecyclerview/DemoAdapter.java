package com.wifi.hblolj.multiplestylesrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hblolj on 2017/2/4.
 */

public class DemoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater mInflater;
    private List<DataModel> mDataModels = new ArrayList<>();

    private List<DataModelOne> mDataModelOnes = new ArrayList<>();
    private List<DataModelTwo> mDataModelTwos = new ArrayList<>();
    private List<DataModelThree> mDataModelThrees = new ArrayList<>();

    public DemoAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case DataModel.TYPE_ONE:
                return new OneViewHolder(mInflater.inflate(R.layout.item_one, parent, false));
            case DataModel.TYPE_TWO:
                return new TwoViewHolder(mInflater.inflate(R.layout.item_two, parent, false));
            case DataModel.TYPE_THREE:
                return new ThreeViewHolder(mInflater.inflate(R.layout.item_three, parent, false));
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
//        ((AbstrctViewHolder)holder).bindData(mDataModels.get(position));
        int realPosition = position - mPositions.get(viewType);
        switch (viewType){
            case DataModel.TYPE_ONE:
                ((OneViewHolder)holder).bindData(mDataModelOnes.get(realPosition));
                break;
            case DataModel.TYPE_TWO:
                ((TwoViewHolder)holder).bindData(mDataModelTwos.get(realPosition));
                break;
            case DataModel.TYPE_THREE:
                ((ThreeViewHolder)holder).bindData(mDataModelThrees.get(realPosition));
                break;
        }
    }

    public void addData(List<DataModel> dataModels){
        mDataModels.addAll(dataModels);
    }

    public void addData2(List<DataModelOne> modelOnes, List<DataModelTwo> modelTwos,
                         List<DataModelThree> modelThrees){

        this.mDataModelOnes = modelOnes;
        this.mDataModelTwos = modelTwos;
        this.mDataModelThrees = modelThrees;

        addListByType(DataModel.TYPE_ONE, mDataModelOnes);
        addListByType(DataModel.TYPE_TWO, mDataModelTwos);
        addListByType(DataModel.TYPE_THREE, mDataModelThrees);
    }

    private List<Integer> types = new ArrayList<>();
    private Map<Integer, Integer> mPositions = new HashMap<>();
    private void addListByType(int type, List list) {
        mPositions.put(type, types.size());//记录该类型添加时，第一个元素所处的位置
        for (int i=0; i<list.size(); i++){
            types.add(type);
        }
    }

    @Override
    public int getItemCount() {
        return types.size();
    }

    @Override
    public int getItemViewType(int position) {
        return types.get(position);
    }
}
