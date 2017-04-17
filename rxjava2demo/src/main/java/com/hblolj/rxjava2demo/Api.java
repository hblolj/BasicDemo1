package com.hblolj.rxjava2demo;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by hblolj on 2017/4/14.
 */

public interface Api {

    @GET("OkHttpPractice/infoById")
    Call<Person> getPersonInfo(@Query("id") int id);

    @FormUrlEncoded
    @POST("OkHttpPractice/loginById")
    Call<IntegerResult> loginById(@Field("username") String username, @Field("password") String password);

    @GET("OkHttpPractice/goodInfo")
    Observable<List<Good>> getGoodMessage();
}
