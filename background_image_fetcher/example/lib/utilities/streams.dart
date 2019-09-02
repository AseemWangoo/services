import 'dart:async';

class ImageStreamBloc {
  StreamController<Map<dynamic, dynamic>> _streamController =
      StreamController<Map<dynamic, dynamic>>();

  Stream<Map<dynamic, dynamic>> get imageStream => _streamController.stream;

  Sink<Map<dynamic, dynamic>> get addImageStream => _streamController.sink;

  ///CONCEPT OF BACKING PROPERTY...
  StreamController<Map<dynamic, dynamic>> get streamController =>
      _streamController;

  dispose() {
    _streamController.close();
  }
}
