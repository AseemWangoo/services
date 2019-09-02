import 'dart:async';

import 'package:flutter/services.dart';

class BackgroundImageFetcher {
  static const MethodChannel _channel =
      const MethodChannel('background_image_fetcher');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static Future<String> get helloFromNative async {
    final String message = await _channel.invokeMethod('helloFromNative');
    return message;
  }
}
