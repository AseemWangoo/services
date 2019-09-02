import 'dart:typed_data';
import 'package:flutter/material.dart';

class ImageWidget extends StatelessWidget {
  const ImageWidget({
    Key key,
    @required Uint8List bytes,
  })  : _bytes = bytes,
        super(key: key);

  final Uint8List _bytes;

  @override
  Widget build(BuildContext context) {
    //

    return Card(
      elevation: 0.0,
      margin: const EdgeInsets.all(1.0),
      child: ClipRRect(
        borderRadius: BorderRadius.circular(8.0),
        child: Image.memory(
          _bytes,
          fit: BoxFit.cover,
        ),
      ),
    );
  }
}
