package com.hblolj.rxjava2demo.imageLoader;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import com.hblolj.rxjava2demo.Good;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

/**
 * Created by hblolj on 2017/4/16.
 */

public class RxImageLoader {

    private static final String TAG = "RxImageLoader";

    //RxImageLoader.with(this).load(url).into();

    private String mUrl;

    private RequestCreator mRequestCreator;

    static RxImageLoader singleton;

    private RxImageLoader() {
        mRequestCreator = new RequestCreator();
    }

    public static RxImageLoader with(Context context) {
        if (singleton == null){
            synchronized (RxImageLoader.class){
                if (singleton == null){
                    singleton = new Builder(context).build();
                }
            }
        }
        return singleton;
    }

    public RxImageLoader load(String url){
        this.mUrl = url;
        return singleton;
    }

    public void into(final ImageView imageView){

        Observable.concat(mRequestCreator.getImage4MemoryCache(mUrl),
                mRequestCreator.getImage4DiskCache(mUrl),
                mRequestCreator.getImage4NetworkCache(mUrl))
                .filter(new Predicate<Image>() {
                    @Override
                    public boolean test(Image image) throws Exception {
                        if (image != null){
                            if (image.getBitmap() != null){
                                return true;
                            }
                        }
                        return false;
                    }
                }).firstElement()
                .subscribe(new Consumer<Image>() {
                    @Override
                    public void accept(Image image) throws Exception {
                        imageView.setImageBitmap(image.getBitmap());
                    }
                });
    }


    private static class Builder {

        private Context mContext;

        public Builder(Context context) {
            mContext = context;
        }

        public RxImageLoader build() {
            return new RxImageLoader();
        }
    }
}
