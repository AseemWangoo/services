import 'package:background_image_fetcher_example/widgets/animator.dart';
import 'package:flutter/material.dart';
import 'package:background_image_fetcher_example/screens/home.dart';
import 'package:background_image_fetcher_example/app-level/theme/theme.dart';
import 'package:background_image_fetcher_example/utilities/helpers.dart';
import 'package:provider/provider.dart';
import 'package:background_image_fetcher_example/utilities/streams.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  final _imageStreamBloc = ImageStreamBloc();

  @override
  void initState() {
    super.initState();
    Helpers().fetchImages.then((_) {
      _imageStreamBloc.addImageStream.add(_);
    });
  }

  @override
  Widget build(BuildContext context) {
    //

    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Demo App'),
        ),
        body: _StreamWidget(
          imageStreamBloc: _imageStreamBloc,
        ),
      ),
      theme: appThemeData(),
    );
  }
}

class _StreamWidget extends StatelessWidget {
  const _StreamWidget({
    Key key,
    @required this.imageStreamBloc,
  }) : super(key: key);

  final ImageStreamBloc imageStreamBloc;

  @override
  Widget build(BuildContext context) {
    //

    return RefreshIndicator(
      onRefresh: _addDelay,
      child: StreamBuilder<Map<dynamic, dynamic>>(
        stream: imageStreamBloc.imageStream,
        builder: (context, snapshot) {
          //
          if (snapshot.hasData) {
            //
            final _imagesMap = ValueNotifier(snapshot.data);

            return MultiProvider(
              providers: [
                ValueListenableProvider<Map<dynamic, dynamic>>.value(
                  value: _imagesMap,
                ),
              ],
              child: Body(),
            );
          }

          return Animator();
        },
      ),
    );
  }

  Future<Null> _addDelay() async {
    imageStreamBloc.addImageStream.add(null);
    await Future.delayed(Duration(seconds: 4));

    final _map = await Helpers().fetchImages;
    imageStreamBloc.addImageStream.add(_map);
  }
}
