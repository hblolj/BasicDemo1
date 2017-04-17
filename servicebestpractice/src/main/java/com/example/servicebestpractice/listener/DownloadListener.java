package com.example.servicebestpractice.listener;

/**
 * Created by hblolj on 2016/12/19.
 */

public interface DownloadListener {

    void onProgress(int progress);

    void onSuccess();

    void onFailed();

    void onPaused();

    void onCanceled();
}
