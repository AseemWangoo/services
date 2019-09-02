package com.imagefetcher.background_image_fetcher;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/** BackgroundImageFetcherPlugin */
public class BackgroundImageFetcherPlugin implements MethodCallHandler {
  /** Plugin registration. */
  public static void registerWith(Registrar registrar) {
    final MethodChannel channel = new MethodChannel(registrar.messenger(), "background_image_fetcher");
    channel.setMethodCallHandler(new BackgroundImageFetcherPlugin());
  }

  String sayHelloToDart() {
    return "Hii, dart !!!! from Android....";
  }

  @Override
  public void onMethodCall(MethodCall call, Result result) {
    if (call.method.equals("getPlatformVersion")) {
      result.success("Android " + android.os.Build.VERSION.RELEASE);
      return;
    }

    if (call.method.equals("helloFromNative")) {
      String message = sayHelloToDart();
      result.success(message);
      return;
    }

    result.notImplemented();
  }
}
