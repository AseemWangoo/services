import 'package:background_image_fetcher_example/utilities/helpers.dart';
import 'package:background_image_fetcher_example/widgets/image.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

class Body extends StatelessWidget {
  const Body({
    Key key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    //
    return _InternalWidget();
  }
}

class _InternalWidget extends StatelessWidget {
  //Internal Widget....

  const _InternalWidget({
    Key key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    //

    final _respFromService = Provider.of<Map<dynamic, dynamic>>(context);

    return Container(
      child: GridView.count(
        crossAxisCount: 2,
        children: List.generate(
          _respFromService.length,
          (index) {
            //

            final _keyName = Helpers().indexToKey(index);
            final _bytes = _respFromService['$_keyName'];

            return ImageWidget(
              bytes: _bytes,
            );
          },
        ),
      ),
    );
  }
}
