package com.hblolj.rxjava2demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * 模拟的搜索框关键字搜索
 * 在监听到搜索框内容变化时，设置最小搜索间隔  debounce
 * 在连续改变搜索框内容时，如果前面发送的请求还没有完成，后面又发送新的内容 switchMap
 * 则停止前面的发送，改为发送后面的数据源
 * 过滤了空的信息
 */
public class DebounceAndSwitchMapActivity extends AppCompatActivity {

    private EditText et_username;
    private TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debounce_and_switch_map);
        et_username = (EditText) findViewById(R.id.et_userName);
        tv_result = (TextView) findViewById(R.id.search_result);
        RxTextView
                .textChanges(et_username)
                .debounce(1, TimeUnit.SECONDS)//200ms CD  尾巴
                .filter(new Predicate<CharSequence>() {
                    @Override
                    public boolean test(CharSequence charSequence) throws Exception {
                        return charSequence.toString().trim().length() > 0;
                    }
                })
                .switchMap(new Function<CharSequence, ObservableSource<Person>>() {
                    @Override
                    public ObservableSource<Person> apply(CharSequence charSequence) throws Exception {
                        //模拟关键字搜索
                        Person person = new Person(charSequence.toString(), "水管工", "18", "Man");
                        Log.d("flatMap", "输入: " + charSequence.toString());
                        return Observable.just(person);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Person>() {
                    @Override
                    public void accept(Person person) throws Exception {
                        Log.d("flatMap", "person: " + person.toString());
                        tv_result.setText(person.toString());
                    }
                });
    }
}
