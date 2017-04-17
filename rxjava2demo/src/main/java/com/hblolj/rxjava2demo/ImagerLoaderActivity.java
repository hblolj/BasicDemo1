package com.hblolj.rxjava2demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.hblolj.rxjava2demo.imageLoader.RxImageLoader;

public class ImagerLoaderActivity extends AppCompatActivity {

    private Button btn_loadImage;
    private ImageView iv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imager_loader);
        btn_loadImage = (Button) findViewById(R.id.loadImage);
        iv_result = (ImageView) findViewById(R.id.iv_result);

        RxImageLoader.with(this).load("xxxxx").into(iv_result);
    }
}
