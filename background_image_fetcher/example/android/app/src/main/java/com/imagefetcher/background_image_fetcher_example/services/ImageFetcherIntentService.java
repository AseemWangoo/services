package com.imagefetcher.background_image_fetcher_example.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.text.TextUtils;
import android.util.Log;

import com.imagefetcher.background_image_fetcher_example.http.DownloadData;

public class ImageFetcherIntentService extends IntentService {

    private static final String TAG = "ImageIntentService";

    public ImageFetcherIntentService() {
        super("ImageFetcherIntentService");
    }

    public static final int STATUS_RUNNING = 0;
    public static final int STATUS_FINISHED = 1;
    public static final int STATUS_ERROR = 2;

    @Override
    protected void onHandleIntent(Intent intent) {

        Log.d(TAG, "Image Fetcher Intent Service Started!");

        if (intent != null) {
            final ResultReceiver receiver = intent.getParcelableExtra("receiver");

            String[] urls = intent.getStringArrayExtra("urls");

            Bundle bundle = new Bundle();

            /* Service Started */
            receiver.send(STATUS_RUNNING, Bundle.EMPTY);

            for (int i = 0; i < urls.length; i++) {

                String urlToCall = urls[i];

                try {
                    byte[] response = new DownloadData().downloadData(urlToCall);

                    /* Sending result back to activity */
                    if (null != response) {
                        bundle.putByteArray("result" + i, response);

                        /* Service Finished */
                        receiver.send(STATUS_FINISHED, bundle);
                    }
                } catch (Exception e) {

                    /* Sending error message back to activity */
                    bundle.putString(Intent.EXTRA_TEXT, e.toString());
                    receiver.send(STATUS_ERROR, bundle);
                }
            }

        }

        Log.d(TAG, "Image Fetcher Intent Service Stopping!");
        this.stopSelf();
    }

}
