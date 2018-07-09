package com.example.ryanhsueh.uiplayground.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.ryanhsueh.uiplayground.R;

import java.io.File;
import java.io.IOException;

public class MediaActivity extends AppCompatActivity {

    private VideoView mVideoView;
    private MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);

        mVideoView = findViewById(R.id.video_view);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    PackageManager.PERMISSION_GRANTED);
        } else {
            initVideo();
            initMediaPlayer();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initVideo();
                    initMediaPlayer();
                } else {
                    Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mVideoView != null) {
            mVideoView.suspend();
        }
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
        }
    }

    private void initVideo() {
        File file = new File(Environment.getExternalStorageDirectory(), "IMG_6782.mp4");
        mVideoView.setVideoPath(file.getPath());
    }

    private void initMediaPlayer() {
        File file = new File(Environment.getExternalStorageDirectory(), "g6.mp3");
        try {
            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setDataSource(file.getPath());
            mMediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void playVideo(View view) {
        if (!mVideoView.isPlaying()) {
            mVideoView.start();
        }
    }
    public void pauseVideo(View view) {
        if (mVideoView.isPlaying()) {
            mVideoView.pause();
        }
    }
    public void resumeVideo(View view) {
        if (mVideoView.isPlaying()) {
            mVideoView.resume();
        }
    }


    public void playMusic(View view) {
        if (!mMediaPlayer.isPlaying()) {
            mMediaPlayer.start();
        }
    }
    public void pauseMusic(View view) {
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
        }
    }
    public void stopMusic(View view) {
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.reset();
            initMediaPlayer();
        }
    }
}
