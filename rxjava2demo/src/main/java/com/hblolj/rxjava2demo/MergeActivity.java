package com.hblolj.rxjava2demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MergeActivity extends AppCompatActivity {

    private TextView tv_result;
    private Api api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merge);
        tv_result = (TextView) findViewById(R.id.tv_result);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.191.1:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        api = retrofit.create(Api.class);
    }

    public void Merge(View view) throws IOException {
        Observable.merge(GetGoodFromLocal(), GetGoodFromNetWork())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Good>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("MergeActivity", "onSubscribe: ");
                    }

                    @Override
                    public void onNext(List<Good> value) {
                        Log.d("MergeActivity", "onNext: ");
                        for (Good good : value){
                            tv_result.append(good.toString());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        Log.d("MergeActivity", "onComplete: ");
                    }
                });
    }

    public Observable<List<Good>> GetGoodFromLocal(){
        List<Good> goods = new ArrayList<>();
        goods.add(new Good("山楂片", "5.00元"));
        goods.add(new Good("第一行代码", "56.00元"));

        return Observable.just(goods);
    }

    public Observable<List<Good>> GetGoodFromNetWork() throws IOException {
//        return Observable.just("").flatMap(new Function<String, ObservableSource<List<Good>>>() {
//            @Override
//            public ObservableSource<List<Good>> apply(String s) throws Exception {
//                List<Good> goods = api.getGoodMessage().execute().body();
//                return Observable.just(goods);
//            }
//        }).subscribeOn(Schedulers.io());
        return api.getGoodMessage().subscribeOn(Schedulers.io());
    }
}
