package com.wifi.hblolj.piechart.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by hblolj on 2017/2/6.
 */

public class MonthData implements Parcelable {

    public String date;
    public List<PieChart> obj;

    protected MonthData(Parcel in) {
        date = in.readString();
    }

    public static final Creator<MonthData> CREATOR = new Creator<MonthData>() {
        @Override
        public MonthData createFromParcel(Parcel in) {
            return new MonthData(in);
        }

        @Override
        public MonthData[] newArray(int size) {
            return new MonthData[size];
        }
    };

    @Override
    public String toString() {
        return "MonthData{" +
                "date='" + date + '\'' +
                ", obj=" + obj +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(date);
    }

    /**
     * 拿到该月的总消费，用360除以sum得到每一块钱等价的度数
     * 知道每一块钱等价的度数，就可以知道每一项消费类别的度数
     * 在旋转90度之后需要倒转点击的类别的一半度数时用到
     * @return
     */
    public float getSum() {
        float sum = 0;
        for (PieChart pieChart : obj) {
            sum += pieChart.value;
        }
        return sum;
    }

    /**
     * 该框架的旋转应该默认以第零个消费类别为基础旋转
     * 起始时，第零个模块的左边界在90度方向(正上方)
     * 默认的旋转度数为270度，旋转的方向应该为逆时针(上方从右往左)
     * 此时第零个模块的左边界会在0度方向(正右方)
     * 手动使其旋转90度(设置setRotationAngle(90))会使其顺时针旋转90度(下方从右往左)
     * 此时第零个模块的左边界会在360度方向(正下方)
     * 如果需要使第零个模块中分朝下,则使第零个模块顺时针旋转负的一半度数即可(就是减一半)
     * 应为该框架是以第零个模块为基础，其他模块旋转需要对应多旋转前面的模块
     * 第一个模块需要多旋转前面第零个模块的度数
     * 第二个模块需要多旋转前面第零个和第一个模块的度数
     * 以此类推，该方法就是计算当前点击需要旋转的模块之前的模块所包含的总度数的
     * @param index
     * @return
     */
    public float getSum(int index) {

        float sum = 0;
        for (int i = 0; i < index; i++) {
            sum += obj.get(i).value;
        }
        return sum;
    }

    public class PieChart {
        public String title;
        public int value;

        @Override
        public String toString() {
            return "PieChart{" +
                    "title='" + title + '\'' +
                    ", value=" + value +
                    '}';
        }
    }
}
