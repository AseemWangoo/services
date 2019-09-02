import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:background_image_fetcher/background_image_fetcher.dart';

void main() {
  const MethodChannel channel = MethodChannel('background_image_fetcher');

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await BackgroundImageFetcher.platformVersion, '42');
  });
}
