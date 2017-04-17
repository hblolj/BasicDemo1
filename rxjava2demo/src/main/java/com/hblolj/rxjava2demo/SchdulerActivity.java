package com.hblolj.rxjava2demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SchdulerActivity extends AppCompatActivity {

    private TextView tv_result;
    private Api api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schduler);
        tv_result = (TextView) findViewById(R.id.tv_result);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.191.1:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(Api.class);
    }

    public void sendRequest(View view) {

//        api.getPersonInfo().enqueue(new Callback<Person>() {
//            @Override
//            public void onResponse(Call<Person> call, Response<Person> response) {
//                Person person = response.body();
////                        tv_result.setText(person.toString());
//            }
//
//            @Override
//            public void onFailure(Call<Person> call, Throwable t) {
//                Toast.makeText(SchdulerActivity.this, "Failed", Toast.LENGTH_SHORT).show();
//                tv_result.setText(t.getMessage());
//            }
//        });

        //创建事件源(被观察者)
        Observable.create(new ObservableOnSubscribe<Person>() {
            @Override
            public void subscribe(final ObservableEmitter<Person> e) throws Exception {
//                Person person = new Person();
//                person.setAge("18");
//                person.setName("hblolj");
//                person.setJob("Singer");
//                person.setSex("Man");
                Person person = api.getPersonInfo(1).execute().body();

                e.onNext(person);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Person>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Person value) {
                tv_result.setText(value.toString());
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
