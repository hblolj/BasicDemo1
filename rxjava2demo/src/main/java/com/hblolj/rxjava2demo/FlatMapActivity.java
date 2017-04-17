package com.hblolj.rxjava2demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FlatMapActivity extends AppCompatActivity {

    private TextView tv_result;
    private Api api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flat_map);
        tv_result = (TextView) findViewById(R.id.tv_result);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.191.1:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(Api.class);
    }


    public void TestFlatMap(View view) {
//        Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
//
//            }
//        });
        final Map<String, String > map = new HashMap<>();
        map.put("username", "hblolj");
        map.put("password", "123456");
        Observable.just(map).flatMap(new Function<Map<String, String>, ObservableSource<IntegerResult>>() {
            @Override
            public ObservableSource<IntegerResult> apply(Map<String, String> s) throws Exception {
                String username = map.get("username");
                String password = map.get("password");
                IntegerResult id = api.loginById(username, password).execute().body();
                return Observable.just(id);
            }
        }).flatMap(new Function<IntegerResult, ObservableSource<Person>>() {
            @Override
            public ObservableSource<Person> apply(IntegerResult integer) throws Exception {
                Person person = api.getPersonInfo(integer.getId()).execute().body();
                return Observable.just(person);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Person>() {
            @Override
            public void accept(Person person) throws Exception {
                tv_result.setText(person.toString());
            }
        });
    }
}
