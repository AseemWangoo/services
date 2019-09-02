package com.imagefetcher.background_image_fetcher_example;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.imagefetcher.background_image_fetcher_example.randomization.UrlRandomization;
import com.imagefetcher.background_image_fetcher_example.services.DownloadReceiver;
import com.imagefetcher.background_image_fetcher_example.services.ImageFetcherIntentService;

import java.util.HashMap;

import io.flutter.app.FlutterActivity;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugins.GeneratedPluginRegistrant;

public class MainActivity extends FlutterActivity implements DownloadReceiver.Receiver, MethodChannel.MethodCallHandler {

    private DownloadReceiver mReceiver;

    private static final String TAG = "MainActivity";

    private HashMap<String, byte[]> resultFromService = new HashMap<>();

    private static final String FLUTTER_CHANNEL = "com.imagefetcher.background_image_fetcher_example/service";

    String[] randomURLs = UrlRandomization.randomURLs();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GeneratedPluginRegistrant.registerWith(this);


        /* Starting Download Service */
        mReceiver = new DownloadReceiver(new Handler());
        mReceiver.setReceiver(this);

        Intent intent = new Intent(Intent.ACTION_SYNC, null, this, ImageFetcherIntentService.class);

        /* Send optional extras to Download IntentService */
        intent.putExtra("urls", randomURLs);
        intent.putExtra("receiver", mReceiver);
        intent.putExtra("requestId", 101);

        startService(intent);

        /* Flutter Method Channel */
        new MethodChannel(getFlutterView(), FLUTTER_CHANNEL).setMethodCallHandler(this::onMethodCall);
    }

    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {

        //Receive response from service....

        switch (resultCode) {
            case ImageFetcherIntentService.STATUS_RUNNING:
                Log.d(TAG, "Intent Service Running !!!!! ");
                break;

            case ImageFetcherIntentService.STATUS_FINISHED:
                byte[] firstImage = resultData.getByteArray("result0");
                byte[] secondImage = resultData.getByteArray("result1");
                byte[] thirdImage = resultData.getByteArray("result2");
                byte[] fourthImage = resultData.getByteArray("result3");

                //Converting to Hash Map....
                HashMap<String, byte[]> imagesMap = new HashMap<>();

                imagesMap.put("firstImage", firstImage);
                imagesMap.put("secondImage", secondImage);
                imagesMap.put("thirdImage", thirdImage);
                imagesMap.put("fourthImage", fourthImage);

                Log.d(TAG, "Images " + imagesMap);

                resultFromService = imagesMap;
                break;

            case ImageFetcherIntentService.STATUS_ERROR:
                String error = resultData.getString(Intent.EXTRA_TEXT);
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Oh No Error !!!! " + error);

                break;
        }
    }

    @Override
    public void onMethodCall(MethodCall call, MethodChannel.Result result) {
        //

        try {
            if (call.method.equals("fetchServiceData")) {
                result.success(resultFromService);
            } else {
                result.error(null, "App not connected to service", null);
            }
        } catch (Exception e) {
            result.error(null, e.getMessage(), null);
        }

    }
}
