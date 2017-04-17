package com.hblolj.rxjava2demo.imageLoader;

import android.graphics.Bitmap;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by hblolj on 2017/4/16.
 */

public abstract class CacheObservable {

    public Observable<Image> getImage(final String url){
        return Observable.create(new ObservableOnSubscribe<Image>() {
            @Override
            public void subscribe(ObservableEmitter<Image> e) throws Exception {
                if (e.isDisposed()) {
                    Image image = getData4Cache(url);
                    e.onNext(image);
                    e.onComplete();
                }
            }
        });
    }

    public abstract void putData2Cache(String url);

    public abstract Image getData4Cache(String url);
}
