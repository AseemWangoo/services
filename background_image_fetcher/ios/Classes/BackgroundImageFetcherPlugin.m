#import "BackgroundImageFetcherPlugin.h"
#import <background_image_fetcher/background_image_fetcher-Swift.h>

@implementation BackgroundImageFetcherPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftBackgroundImageFetcherPlugin registerWithRegistrar:registrar];
}
@end
