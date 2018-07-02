package com.example.ryanhsueh.uiplayground;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ServiceTryActivity extends AppCompatActivity {


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_try);
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

}
