package com.hblolj.rxjava2demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private TextView mResult;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mResult = ((TextView) findViewById(R.id.tv_result));
    }

    public void test(View view) {
        Observable<String> observable = getObservable();
        observable.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                //相当于onNext
                mResult.append(s);
                mResult.append("\n");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                //相当于onError
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                //相当于onComplete
            }
        }, new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {
                //相当于onSubscribe
            }
        });
//        Observer<String> observer = getObserver();
//        observable.subscribe(observer);
    }

    /**
     * 获取事件源，被观察者
     */
    public Observable<String> getObservable(){
//        Observable<String> observable = Observable.just("打豆豆", "睡觉", "吃饭");
//        Observable<String> observable = Observable.fromArray("打豆豆", "睡觉", "吃饭");
        Observable<String> observable = Observable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "打豆豆";
            }
        });
//        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(ObservableEmitter<String> e) throws Exception {
//                e.onNext("吃饭");
//                e.onNext("睡觉");
//                e.onNext("打豆豆");
//                e.onComplete();
//            }
//        });
        return observable;
    }

    /**
     * 获取观察者
     */
    public Observer<String> getObserver(){
        Observer<String> observer = new Observer<String>() {

            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe: ");
            }

            @Override
            public void onNext(String value) {
                Log.d(TAG, "onNext: " + value);
                mResult.append(value);
                mResult.append("\n");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: ");
            }
        };

        return observer;
    }

    public void test2(){

    }
}
