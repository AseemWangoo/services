import 'package:flutter/services.dart';

const _platform = MethodChannel(
  'com.imagefetcher.background_image_fetcher_example/service',
);

class Helpers {
  ///Fetch Images from Method Channel...
  Future<Map<dynamic, dynamic>> get fetchImages =>
      _InternalHelpers()._fetchImages();

  ///Index names to key.....
  String indexToKey(int index) => _InternalHelpers()._keyName(index);
}

class _InternalHelpers {
  //Fetch Images from Method Channel...
  Future<Map<dynamic, dynamic>> _fetchImages() async {
    Map<dynamic, dynamic> respFromService;

    try {
      respFromService = await _platform.invokeMethod<Map>('fetchServiceData');
    } on Exception {
      respFromService = null;
    }

    return respFromService;
  }

  //Index to key names...
  String _keyName(int index) {
    String _keyName = '';

    switch (index) {
      case 0:
        _keyName = 'firstImage';
        break;

      case 1:
        _keyName = 'secondImage';
        break;

      case 2:
        _keyName = 'thirdImage';
        break;

      case 3:
        _keyName = 'fourthImage';
        break;

      default:
        _keyName = 'firstImage';
    }

    return _keyName;
  }

  
}
