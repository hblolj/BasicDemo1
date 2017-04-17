package com.hblolj.rxjava2demo.imageLoader;

import io.reactivex.Observable;

/**
 * Created by hblolj on 2017/4/16.
 */

public class RequestCreator {

    MemoryCacheObservable mMemoryCacheObservable;
    DiskCacheObservable mDiskCacheObservable;
    NetWorkCacheObservable mNetWorkCacheObservable;

    public RequestCreator() {
        mMemoryCacheObservable = new MemoryCacheObservable();
        mDiskCacheObservable = new DiskCacheObservable();
        mNetWorkCacheObservable = new NetWorkCacheObservable();
    }

    public Observable<Image> getImage4MemoryCache(String url){
        return mMemoryCacheObservable.getImage(url);
    }

    public Observable<Image> getImage4DiskCache(String url){
        return mDiskCacheObservable.getImage(url);
    }

    public Observable<Image> getImage4NetworkCache(String url){
        return mNetWorkCacheObservable.getImage(url);
    }
}
