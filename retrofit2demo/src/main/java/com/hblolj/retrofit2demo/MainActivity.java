package com.hblolj.retrofit2demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;
    private Api api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = ((TextView) findViewById(R.id.tv_result));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.191.1:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(Api.class);

    }

    /**
     * Get
     */
    private void get() {
        Call<Person> call = api.getUserInfo();
        call.enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                Person person = response.body();
                mTextView.setText(person.toString());
            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    /**
     * 动态路径
     */
    private void getWithPath() {
        Call<Person> call = api.getUserInfoWithPath("OkHttpPractice");
        call.enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                Person person = response.body();
                mTextView.setText(person.toString());
            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    /**
     * Get带参
     */
    private void getWithParam() {
        Call<Person> call = api.login("hblolj", "123456");
        call.enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                Person person = response.body();
                mTextView.setText(person.toString());
            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    /**
     * Get带多个参数
     */
    private void getWithMap() {
        Map<String, String> map = new HashMap<>();
        map.put("username", "hblolj");
        map.put("password", "999999");
        Call<Person> call = api.login(map);
        call.enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                Person person = response.body();
                mTextView.setText(person.toString());
            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    /**
     * Post上传非表单形式
     */
    private void addUserWithPost() {
        Person person = new Person();
        person.setAge("18岁");
        person.setSex("男");
        person.setName("Zard");
        person.setJob("LOL");
        api.addUser(person).enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                Log.d("addUserWithPost", "onResponse: " + response.body().toString());
            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    /**
     * Post上传表单形式
     */
    private void editUserWithPostForm() {
        api.editUser("hblolj", "999999").enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                Log.d("editUserWithPostForm", "onResponse: " + response.body().toString());
            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    /**
     * Post上传文件
     */
    private void uploadPhotoWithPost() {
        //DCIM/Camera/IMG_20170306_144600.jpg
//        Log.d("uploadPhotoWithPost", "uploadPhotoWithPost: " + Environment.getExternalStorageDirectory());
//        File file = new File(Environment.getRootDirectory(), "stack.txt");
//        if (!file.exists()){
//            Log.i("MainActivity", "文件不存在");
//            mTextView.setText("文件不存在!");
//            return;
//        }
        String header3 = "Three";
        RequestBody text = RequestBody.create(MediaType.parse("application/octet-stream"), "这是模拟的文件内容，该死的手机，内存卡也坏了。");
        RequestBody description = RequestBody.create(MediaType.parse("text/plain"), "米奇脆");
        MultipartBody.Part data = MultipartBody.Part.createFormData("picture", "info.txt", text);
        api.uploadPhoto(header3, data, description).enqueue(new Callback<BaseResult>() {
            @Override
            public void onResponse(Call<BaseResult> call, Response<BaseResult> response) {
                Log.d("uploadPhotoWithPost", "onResponse: " + response.body().toString());
            }

            @Override
            public void onFailure(Call<BaseResult> call, Throwable t) {
                Log.d("uploadPhotoWithPost", "onResponse: 返回数据异常");
                t.printStackTrace();
            }
        });
    }

    public void get(View view) {
        get();
    }

    public void getWithPath(View view) {
        getWithPath();
    }

    public void getWithParam(View view) {
        getWithParam();
    }

    public void getWithMap(View view) {
        getWithMap();
    }

    public void addUserWithPost(View view) {
        addUserWithPost();
    }

    public void editUserWithPostForm(View view) {
        editUserWithPostForm();
    }

    public void uploadPhotoWithPost(View view) {
        uploadPhotoWithPost();
    }
}
