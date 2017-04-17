package com.wifi.hblolj.okhttppractice;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wifi.hblolj.okhttppractice.Utils.HttpUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    //服务端在D:\Workspaces\MyEclipse 10\OkHttpPractice
    private static final String TAG = "okhttp_practice";
    private String baseUrl = "http://192.168.191.1:8080/OkHttpPractice/";
    private TextView tv_result;
    private ImageView iv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_result = (TextView) findViewById(R.id.tv_result);
        iv_result = (ImageView) findViewById(R.id.iv_result);
    }

    /**
     * Get请求方法
     * @param view
     */
    public void doGet(View view){
        String address = baseUrl + "login?username=hblolj&password=123456";
        HttpUtils.sendOkHttpRequestByGet(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i(TAG, "onFailure: " + e.getMessage());
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                Log.i(TAG, "onResponse: " + result);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv_result.setText(result);
                    }
                });
            }
        });
    }

    /**
     * Post请求上传键值对
     * @param view
     */
    public void doPost(View view){
        String address = baseUrl + "login";
        Map<String, String> map = new HashMap<>();
        map.put("username", "hblolj");
        map.put("password", "123456");
        HttpUtils.sendOkHttpRequestByPost(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i(TAG, "onFailure: " + e.getMessage());
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                Log.i(TAG, "onResponse: " + result);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv_result.setText(result);
                    }
                });
            }
        }, map);
    }

    /**
     * Post请求上传字符串
     * @param view
     */
    public void doPostString(View view){
        String address = baseUrl + "postString";
        HttpUtils.sendOkHttpRequestByPostString(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i(TAG, "onFailure: " + e.getMessage());
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                Log.i(TAG, "onResponse: " + result);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv_result.setText(result);
                    }
                });
            }
        }, "我是通过PostString传递的字符内容");
    }

    /**
     * Post请求上传文件
     * @param view
     */
    public void doPostFile(View view){
        String address = baseUrl + "postFile";
        File file = new File(Environment.getExternalStorageDirectory(), "shareimg.png");
        if (!file.exists()){
            Log.i(TAG, "文件不存在");
            tv_result.setText("文件不存在!");
            return;
        }
        HttpUtils.sendOkHttpRequestByPostFile(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i(TAG, "onFailure: " + e.getMessage());
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                Log.i(TAG, "onResponse: " + result);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv_result.setText(result);
                    }
                });
            }
        }, file);
    }

    /**
     * okHttp3下的文件上传
     * @param view
     */
    public void doUpload(View view){
        String address = baseUrl + "uploadFile";
        File file = new File(Environment.getExternalStorageDirectory(), "tagPicture.jpg");
        if (!file.exists()){
            Log.i(TAG, "文件不存在");
            tv_result.setText("文件不存在!");
            return;
        }
        HttpUtils.sendOkHttpRequestByUpload(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i(TAG, "onFailure: " + e.getMessage());
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                Log.i(TAG, "onResponse: " + result);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv_result.setText(result);
                    }
                });
            }
        }, file);
    }

    /**
     * okhttp3下的文件下载
     * @param view
     */
    public void doDownloadFile(View view){
        String address = baseUrl + "files/picture.jpg";
        HttpUtils.sendOkHttpRequestByDownloadFile(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i(TAG, "onFailure: " + e.getMessage());
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream is = response.body().byteStream();
                File file = new File(Environment.getExternalStorageDirectory(), "一张照片6666.jpg");
                FileOutputStream fout = new FileOutputStream(file);
                int len = 0;
                byte[] buf = new byte[128];
                while((len = is.read(buf)) != -1){
                    fout.write(buf, 0, len);
                }
                fout.flush();
                fout.close();
                is.close();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv_result.setText("下载成功");
                    }
                });
            }
        });
    }

    public void doDownloadImg(View view){
        String address = baseUrl + "files/picture.jpg";
        HttpUtils.sendOkHttpRequestByDownloadFile(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i(TAG, "onFailure: " + e.getMessage());
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream is = response.body().byteStream();
                final Bitmap bitmap = BitmapFactory.decodeStream(is);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv_result.setText("下载成功");
                        iv_result.setImageBitmap(bitmap);
                    }
                });
            }
        });
    }
}
