package com.wifi.hblolj.okhttppractice.Utils;

import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by hblolj on 2017/2/5.
 */

public class HttpUtils {

//    private static OkHttpClient sOkHttpClient = new OkHttpClient();

//    private static OkHttpClient sOkHttpClient = new OkHttpClient.Builder()
//            .readTimeout(5000, TimeUnit.MILLISECONDS)
//            .connectTimeout(5000, TimeUnit.MILLISECONDS)
//            .cookieJar(new JavaNetCookieJar)
//            .build();

    /**
     * 全局的okHttpClient
     */
    private static OkHttpClient sOkHttpClient = new OkHttpClient.Builder().cookieJar(new CookieJar() {

            private Map<String, List<Cookie>> cookieStore = new HashMap<String, List<Cookie>>();

            @Override
            public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                Log.d("HttpUtils", "host: " + url.host());
                cookieStore.put(url.host(), cookies);
            }

            @Override
            public List<Cookie> loadForRequest(HttpUrl url) {
                List<Cookie> cookies = cookieStore.get(url.host());
                return cookies!=null?cookies : new ArrayList<Cookie>();
            }
        })
            .connectTimeout(1500, TimeUnit.MILLISECONDS)
            .readTimeout(2000, TimeUnit.MILLISECONDS)
            .writeTimeout(2000, TimeUnit.MILLISECONDS)
            //这里可能不能用全局的okHttpClient，可能用工厂模式会更好
//            .cache(new Cache(file , 10*1024*1024))
            .build();

    /**
     * get请求方法
     * @param address  get的请求地址
     * @param callback 回调对象
     */
    public static void sendOkHttpRequestByGet(String address , Callback callback){
        Request request = new Request.Builder().url(address).get().build();
        Call call = sOkHttpClient.newCall(request);
        call.enqueue(callback);
    }

    /**
     *
     * @param address  post请求的地址
     * @param callback 回调对象
     * @param maps     post请求的参数
     */
    public static void sendOkHttpRequestByPost(String address, Callback callback, Map<String, String> maps){
        RequestBody requestBody = new FormBody.Builder()
                .add("username", maps.get("username"))
                .add("password", maps.get("password"))
                .build();
        Request request = new Request.Builder().url(address).post(requestBody).build();
        Call call = sOkHttpClient.newCall(request);
        call.enqueue(callback);
    }

    /**
     *
     * @param address  服务器地址
     * @param callback 回调对象
     * @param value    上传的字符串
     */
    public static void sendOkHttpRequestByPostString(String address, Callback callback, String value){
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain;charset=utf-8"), value);
        Request request = new Request.Builder().url(address).post(requestBody).build();
        Call call = sOkHttpClient.newCall(request);
        call.enqueue(callback);
    }

    /**
     * Post方法上传文件
     * @param address   完整的请求地址
     * @param callback  回调对象
     * @param file      上传的文件对象
     */
    public static void sendOkHttpRequestByPostFile(String address, Callback callback, File file){
        //指定了MediaType为application/octet-stream，并且传入File生成requestBody
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
        //构造Request
        Request request = new Request.Builder().url(address).post(requestBody).build();
        //构造Call
        Call call = sOkHttpClient.newCall(request);
        //异步执行
        call.enqueue(callback);
    }

    /**
     * okHttp3下的文件时上传
     * @param address  访问的地址
     * @param callback 回调对象
     * @param file     上传的文件
     */
    public static void sendOkHttpRequestByUpload(String address, Callback callback, File file){
        //构造MultipartBody(复合Body)
        MultipartBody multipartBody = new MultipartBody.Builder()
                //配置上传的类型
                .setType(MultipartBody.FORM)
                //上传的普通键值对
                .addFormDataPart("username", "hblolj")
                .addFormDataPart("password", "123456")
                //参数对应为name, fileName, reuqestBody
                .addFormDataPart("picture", "一张照片.jpg",
                    RequestBody.create(MediaType.parse("application/octet-stream"), file))
                .build();
        //构造Request
        Request request = new Request.Builder().url(address).post(multipartBody).build();
        //构造Call
        Call call = sOkHttpClient.newCall(request);
        //异步执行
        call.enqueue(callback);

        //        MultipartBody multipartBody = new MultipartBody.Builder()
//                .setType(MultipartBody.FORM)
//                .addFormDataPart("username", "hblolj")
//                .addFormDataPart("password", "123456")
//                .addFormDataPart("picture", "一张照片.jpg",
//                        RequestBody.create(MediaType.parse("application/octet-stream"), file))
//                .build();
//        Request request = new Request.Builder().url(address).post(multipartBody).build();
    }

    /**
     * okhttp3下的文件下载
     * @param address  下载地址
     * @param callback 回调对象
     */
    public static void sendOkHttpRequestByDownloadFile(String address, Callback callback){
        Request request = new Request.Builder().url(address).get().build();

        Call call = sOkHttpClient.newCall(request);
        call.enqueue(callback);
    }
}
