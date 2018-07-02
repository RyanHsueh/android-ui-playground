package com.example.ryanhsueh.uiplayground.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class TestService extends Service {
    private static final String TAG = TestService.class.getSimpleName();
    
    private TestBinder mBinder = new TestBinder();
    public class TestBinder extends Binder {
        
        public void downloadData() {
            Log.d(TAG, "downloadData: ");
        }
        
        public int getProgress() {
            return 0;
        }
        
    }
    
    public TestService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: ");
        return mBinder;
    }
}
