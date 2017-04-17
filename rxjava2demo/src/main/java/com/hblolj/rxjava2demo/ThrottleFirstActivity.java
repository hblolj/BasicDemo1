package com.hblolj.rxjava2demo;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * 防止重复点击
 * ThrottleFirst会设置一个时间间隔，会取每个时间间隔的开始发射物作为最终发射物
 */
public class ThrottleFirstActivity extends AppCompatActivity {

    private Button btn_once;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_throttle_first);
        btn_once = (Button) findViewById(R.id.btn_once);
        RxView
                .clicks(btn_once)
                .throttleFirst(3, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("ThrottleFirstActivity", "onSubscribe: onSubscribe");
                    }

                    @Override
                    public void onNext(Object value) {
                        Log.d("ThrottleFirstActivity", "onSubscribe: 点击了");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        Log.d("ThrottleFirstActivity", "onSubscribe: onComplete");
                    }
                });
    }
}
