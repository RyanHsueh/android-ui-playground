package com.example.ryanhsueh.uiplayground;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DownloadAsyncTask extends AsyncTask<String, Integer, DownloadAsyncTask.Status> {
    private static final String TAG = DownloadAsyncTask.class.getSimpleName();

    enum Status {
        SUCCESS,
        FAILED,
        PAUSED,
        CANCELED
    }

    private DownloadObserver mObserver;

    private boolean isCanceled = false;
    private boolean isPaused = false;
    private int lastProgress;

    public DownloadAsyncTask (DownloadObserver observer) {
        mObserver = observer;
    }

    @Override
    protected DownloadAsyncTask.Status doInBackground(String... params) {

        InputStream is = null;
        RandomAccessFile savedFile = null;
        File file = null;

        Status finalStatus = Status.FAILED;
        try {

            long downloadedLength = 0; // length of downloaded file
            String downloadUrl = params[0];
            String filename = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
            String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
            file = new File(directory + filename);
            if (file.exists()) {
                downloadedLength = file.length();
            }

            long contentLength = getContentLength(downloadUrl);
            if (contentLength == 0) {
                return Status.FAILED;
            } else if (contentLength == downloadedLength) {
                return Status.SUCCESS;
            }

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    // 斷點下載，指定從哪個字節開始下載
                    .addHeader("RANGE", "bytes=" + downloadedLength + "-")
                    .url(downloadUrl)
                    .build();
            Response response = client.newCall(request).execute();
            if (response != null && response.isSuccessful()) {
                is = response.body().byteStream();
                savedFile = new RandomAccessFile(file, "rw");
                savedFile.seek(downloadedLength); // 跳過已下載的字節

                byte[] b = new byte[1024];
                int total = 0;
                int len;
                while ((len = is.read(b)) != -1) {
                    if (isCanceled) {
                        return Status.CANCELED;
                    } if (isPaused) {
                        return Status.PAUSED;
                    } else {
                        total += len;
                        savedFile.write(b, 0, len);

                        int progress = (int)((total + downloadedLength) * 100  / contentLength);
                        publishProgress(progress);
                    }
                }

                response.body().close();
                finalStatus = Status.SUCCESS;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                if (is != null) {
                    is.close();
                }
                if (savedFile != null) {
                    savedFile.close();
                }
                if (isCanceled && file != null) {
                    file.delete();
                }
            } catch (Exception e) {
                e.getStackTrace();
            }
        }

        return finalStatus;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        int progress = values[0];
        if (progress > lastProgress) {
            lastProgress = progress;
            mObserver.onProgress(progress);

            Log.d(TAG, "onProgressUpdate: " + progress);
        }
    }

    @Override
    protected void onPostExecute(DownloadAsyncTask.Status status) {
        Log.d(TAG, "onPostExecute: " + status);
        switch (status) {
            case SUCCESS:
                mObserver.onSuccess();
                break;
            case FAILED:
                mObserver.onFailed();
                break;
            case CANCELED:
                mObserver.onCanceled();
                break;
            case PAUSED:
                mObserver.onPaused();
                break;
        }
    }

    public void pauseDownload() {
        isPaused = true;
    }

    public void cancelDownload() {
        isCanceled = true;
    }

    private long getContentLength(String downloadUrl) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(downloadUrl)
                .build();
        Response response = client.newCall(request).execute();
        if (response != null && response.isSuccessful()) {
            long contentLength = response.body().contentLength();
            response.body().close();
            return contentLength;
        }

        return 0;
    }
}
