package com.example.ryanhsueh.uiplayground;

public interface DownloadObserver {
    void onProgress(int progress);
    void onSuccess();
    void onFailed();
    void onPaused();
    void onCanceled();
}
