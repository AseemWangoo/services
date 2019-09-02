import 'package:flare_flutter/flare_actor.dart';
import 'package:flutter/material.dart';

class Animator extends StatelessWidget {
  const Animator({
    Key key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      child: FlareActor(
        'assets/animations/Loading.flr',
        animation: 'Alarm',
      ),
    );
  }
}
