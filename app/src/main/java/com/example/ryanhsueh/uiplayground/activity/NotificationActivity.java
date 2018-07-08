package com.example.ryanhsueh.uiplayground.activity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.ryanhsueh.uiplayground.R;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
    }

    public void simpleNotification(View view) {
        NotificationManager manager = getNotificationManager();

        Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        initChannels("111", "Channel 111", NotificationManager.IMPORTANCE_DEFAULT);

        Notification notification = getNotifiactionBuider()
                .setContentText("This is notification message")
                .setSound(uri)
                .setLights(Color.CYAN, 750, 3000)
                .setVibrate(new long[]{0 ,500, 1000, 500})
                .build();

        manager.notify(1, notification);
    }

    public void styleTextNotification(View view) {
        NotificationManager manager = getNotificationManager();

        // TODO : no effect of priority
        initChannels("222", "Channel 222", NotificationManager.IMPORTANCE_HIGH);

        Notification notification = getNotifiactionBuider()
                .setStyle(new NotificationCompat.BigTextStyle().bigText("This is a long long long long long long long long long long message"))
                .build();

        manager.notify(2, notification);
    }

    public void stylePictureNotification(View view) {
        NotificationManager manager = getNotificationManager();

        initChannels("333", "Channel 333", NotificationManager.IMPORTANCE_DEFAULT);

        Notification notification = getNotifiactionBuider()
                .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(
                        BitmapFactory.decodeResource(getResources(), R.drawable.img_lights)))
                .build();

        manager.notify(3, notification);
    }

    private NotificationCompat.Builder getNotifiactionBuider() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
//                .setContentIntent(pi)
                .setContentTitle("Content Title");

        return builder;
    }

    private void initChannels(String id, String name, int importance) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            return;
        }

        NotificationChannel channel = new NotificationChannel(id, name, importance);
        channel.setDescription("Channel description");
        getNotificationManager().createNotificationChannel(channel);
    }

    private NotificationManager getNotificationManager() {
        return (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }
}
