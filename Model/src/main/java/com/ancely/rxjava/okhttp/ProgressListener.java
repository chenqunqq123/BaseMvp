package com.ancely.rxjava.okhttp;

public interface ProgressListener {

    void onProgress(int progress, String url);

}