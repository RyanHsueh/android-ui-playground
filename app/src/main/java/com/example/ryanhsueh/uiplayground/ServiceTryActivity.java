package com.example.ryanhsueh.uiplayground;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.ryanhsueh.uiplayground.service.DownloadService;
import com.example.ryanhsueh.uiplayground.service.TestService;

public class ServiceTryActivity extends AppCompatActivity {
    private static final String TAG = ServiceTryActivity.class.getSimpleName();

    private TestService.TestBinder mTestService;
    private ServiceConnection mTestServiceConn = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mTestService = (TestService.TestBinder) iBinder;
            mTestService.downloadData();
            int progress = mTestService.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
        }
    };


    private DownloadService.DownloadBinder mDownloadService;
    private ServiceConnection mDownloadServiceConn = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(TAG, "onServiceConnected: DownloadService.DownloadBinder");
            mDownloadService = (DownloadService.DownloadBinder) iBinder;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_try);

        Intent intent = new Intent(this, DownloadService.class);
        startService(intent);
        bindService(intent, mDownloadServiceConn, BIND_AUTO_CREATE);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission deny !!", Toast.LENGTH_SHORT).show();
                    finish();
                }

                break;
            default:
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mDownloadService != null) {
            unbindService(mDownloadServiceConn);
        }
    }

    public void startService(View view) {
        Intent intent = new Intent(this, TestService.class);
        startService(intent);
    }
    public void stopService(View view) {
        Intent intent = new Intent(this, TestService.class);
        stopService(intent);
    }

    public void bindService(View view) {
        Intent intent = new Intent(this, TestService.class);
        bindService(intent, mTestServiceConn, BIND_AUTO_CREATE);
    }
    public void unbindService(View view) {
        if (mTestService != null) {
            unbindService(mTestServiceConn);
        }
    }


    public void startDownload(View view) {
        mDownloadService.startDownload("https://raw.githubusercontent.com/guolindev/eclipse/master/eclipse-inst-win64.exe");
    }
    public void pauseDownload(View view) {
        mDownloadService.pauseDownload();
    }
    public void cancelDownload(View view) {
        mDownloadService.cancelDownload();
    }


}
