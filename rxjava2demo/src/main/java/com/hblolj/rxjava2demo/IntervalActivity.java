package com.hblolj.rxjava2demo;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

/**
 * 发送验证码倒计时
 */
public class IntervalActivity extends AppCompatActivity {

    private Button btn_sendCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interval);
        btn_sendCode = (Button) findViewById(R.id.btn_sendCode);
//        RxView.clicks(btn_sendCode)
//                .inte
    }

    public void SendCode(View view) {
        //循环多少次退出
        final long count = 10;
        //延时0，间隔count，单位Seconds
        Observable.interval(0, 1, TimeUnit.SECONDS)
                .take(count)
                .map(new Function<Long, Long>() {
                    @Override
                    public Long apply(Long aLong) throws Exception {
                        return count - (aLong+1);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("IntervalActivity", "onSubscribe: ");
                        btn_sendCode.setEnabled(false);
                        btn_sendCode.setBackgroundColor(Color.DKGRAY);
                        btn_sendCode.setTextColor(Color.BLACK);
                    }

                    @Override
                    public void onNext(Long value) {
                        Log.d("IntervalActivity", "onNext: " + value);
                        btn_sendCode.setText("剩余" + value + "秒");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        Log.d("IntervalActivity", "onComplete: ");
                        btn_sendCode.setEnabled(true);
                        btn_sendCode.setTextColor(Color.WHITE);
                        btn_sendCode.setBackgroundColor(Color.RED);
                        btn_sendCode.setText("点击发送验证码");
                    }
                });
    }
}
