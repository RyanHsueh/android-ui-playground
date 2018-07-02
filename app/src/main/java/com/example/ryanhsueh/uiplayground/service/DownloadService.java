package com.example.ryanhsueh.uiplayground.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.example.ryanhsueh.uiplayground.DownloadAsyncTask;
import com.example.ryanhsueh.uiplayground.DownloadObserver;
import com.example.ryanhsueh.uiplayground.R;
import com.example.ryanhsueh.uiplayground.ServiceTryActivity;

import java.io.File;

public class DownloadService extends Service implements DownloadObserver {

    private static final int NOTIFICATION_ID = 1;
    private static final String NOTIFICATION_CHANNEL_ID = "DownloadService_channel";

    private DownloadAsyncTask mDownloadTask;
    private String mDownloadUrl;

    private DownloadBinder mBinder = new DownloadBinder();
    public class DownloadBinder extends Binder {

        public void startDownload(String url) {
            if (mDownloadTask == null) {
                mDownloadUrl = url;

                mDownloadTask = new DownloadAsyncTask(DownloadService.this);
                mDownloadTask.execute(url);
                startForeground(NOTIFICATION_ID, getNotification("Downloading...", 0));
                Toast.makeText(DownloadService.this, "Downloading...", Toast.LENGTH_SHORT).show();
            }
        }

        public void pauseDownload() {
            if (mDownloadTask != null) {
                mDownloadTask.pauseDownload();
            }
        }

        public void cancelDownload() {
            if (mDownloadTask != null) {
                mDownloadTask.cancelDownload();
            }
            if (mDownloadUrl != null) {
                String fileName = mDownloadUrl.substring(mDownloadUrl.lastIndexOf("/"));
                String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
                File file = new File(directory + fileName);
                if (file.exists()) {
                    file.delete();
                }

                getNotificationManager().cancel(NOTIFICATION_ID);
                stopForeground(true);
                Toast.makeText(DownloadService.this, "Canceled", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public DownloadService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onProgress(int progress) {
        getNotificationManager().notify(NOTIFICATION_ID, getNotification("Downloading...", progress));
    }

    @Override
    public void onSuccess() {
        mDownloadTask = null;
        stopForeground(true);
        Toast.makeText(DownloadService.this, "Download Completed!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailed() {
        mDownloadTask = null;
        stopForeground(true);
        getNotificationManager().notify(NOTIFICATION_ID, getNotification("Downloading...", -1));
        Toast.makeText(DownloadService.this, "Download Failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPaused() {
        mDownloadTask = null;
        Toast.makeText(DownloadService.this, "Download Paused", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCanceled() {
        mDownloadTask = null;
        stopForeground(true);
        Toast.makeText(DownloadService.this, "Download Canceled", Toast.LENGTH_SHORT).show();

    }

    private NotificationManager getNotificationManager() {
        return (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
    }

    private Notification getNotification(String title, int progress) {
        initChannels();

        Intent intent = new Intent(this, ServiceTryActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setContentIntent(pi)
                .setContentTitle(title);
        if (progress > 0) {
            builder.setContentText(progress + "%");
            builder.setProgress(100, progress, false);
        }

        return builder.build();
    }

    private void initChannels() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            return;
        }

        NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,
                "Channel name",
                NotificationManager.IMPORTANCE_DEFAULT);
        channel.setDescription("Channel description");
        getNotificationManager().createNotificationChannel(channel);
    }
}
