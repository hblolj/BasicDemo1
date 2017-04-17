package com.hblolj.retrofit2demo;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by hblolj on 2017/3/23.
 */

public interface Api {

    /**
     * 普通的Get请求
     * @return
     */
    @GET("OkHttpPractice/info")
    Call<Person> getUserInfo();

    /**
     * 路径替代
     * @param project
     * @return
     */
    @GET("{Project}/info")
    Call<Person> getUserInfoWithPath(@Path("Project") String project);

    /**
     * 带参数
     * @param username
     * @param password
     * @return
     */
    @GET("OkHttpPractice/login")
    Call<Person> login(@Query("username") String username, @Query("password") String password);

    /**
     * 带多个参数
     * @param map
     * @return
     */
    @GET("OkHttpPractice/login")
    Call<Person> login(@QueryMap Map<String, String> map);

    /**
     * Post方式上传
     * 非表单形式
     * @param person
     * @return
     */
    @POST("OkHttpPractice/postJson")
    Call<Person> addUser(@Body Person person);

    /**
     * Post方式上传
     * 表单形式
     * @param username
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST("OkHttpPractice/login")
    Call<Person> editUser(@Field("username") String username, @Field("password") String password);

    @Multipart
    @Headers({"header1:TheFirst", "header2:TheSecond"})
    @POST("OkHttpPractice/uploadFile")
    Call<BaseResult> uploadPhoto(@Header("header3") String header3, @Part MultipartBody.Part picture, @Part("username") RequestBody username);
}
