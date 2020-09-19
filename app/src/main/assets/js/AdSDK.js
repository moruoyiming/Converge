"use strict";

(function () {
    var AdSDK = window.AdSDK = {
        bannerAds: {},
        interstitialAds: {},
        videoAds: {},
        init: function () {
            var self = this;
            GameSDK.registerCallback("ad_banner_onLoad", function (param) {
                var banner = self.bannerAds[param.adId];
                if (banner && banner.onLoad) {
                    banner.onLoad(param);
                }
            });
            GameSDK.registerCallback("ad_banner_onError", function (param) {
                var banner = self.bannerAds[param.adId];
                if (banner && banner.onError) {
                    banner.onError(param);
                }
            });
            GameSDK.registerCallback("ad_banner_onResize", function (param) {
                var banner = self.bannerAds[param.adId];
                if (banner && banner.onResize) {
                    banner.onResize(param);
                }
            });
            GameSDK.registerCallback("ad_interstitial_onLoad", function (param) {
                var interstitialAd = self.interstitialAds[param.adId];
                if (interstitialAd && interstitialAd.onLoad) {
                    interstitialAd.onLoad(param);
                }
            });
            GameSDK.registerCallback("ad_interstitial_onError", function (param) {
                var interstitialAd = self.interstitialAds[param.adId];
                if (interstitialAd && interstitialAd.onError) {
                    interstitialAd.onError(param);
                }
            });
            GameSDK.registerCallback("ad_interstitial_onResize", function (param) {
                var interstitialAd = self.interstitialAds[param.adId];
                if (interstitialAd && interstitialAd.onResize) {
                    interstitialAd.onResize(param);
                }
            });
            GameSDK.registerCallback("ad_rewardedVideo_onLoad", function (param) {
                var videoAd = self.videoAds[param.adId];
                if (videoAd && videoAd.onLoad) {
                    videoAd.onLoad(param);
                }
            });
            GameSDK.registerCallback("ad_rewardedVideo_onError", function (param) {
                var videoAd = self.videoAds[param.adId];
                if (videoAd && videoAd.onError) {
                    videoAd.onError(param);
                }
            });
            GameSDK.registerCallback("ad_rewardedVideo_onClose", function (param) {
                var videoAd = self.videoAds[param.adId];
                if (videoAd && videoAd.onClose) {
                    videoAd.onClose(param);
                }
            });
            GameSDK.registerCallback("ad_onShow", function (param) {
                if (param.type == 1) {
                    var banner = self.bannerAds[param.adId];
                    if( banner && banner.mHandler ){
                        banner.mHandler(param.success);
                    }
                } else if (param.type == 2) {
                    var interstitialAd = self.interstitialAds[param.adId];
                    if( interstitialAd && interstitialAd.mHandler ){
                        interstitialAd.mHandler(param.success);
                    }
                } else if (param.type == 3) {
                    var videoAd = self.videoAds[param.adId];
                    if( videoAd && videoAd.mHandler ){
                        videoAd.mHandler(param.success);
                    }
                }
            });
        },
        // 创建Banner广告
        // 参数
        //   adId: string 广告序号ID
        //   bannerAdId: string Banner广告ID
        //   position: int 广告位置 1 屏幕顶部 2 屏幕底部 3屏幕中间
        createBannerAd : function (adId, bannerAdId, position) {
            var self = this;
            var bannerAd = {"adId": adId};
            var param = {
                "adId": adId,
                "bannerAdId": bannerAdId,
                "position": position,
            };

            //显示 banner 广告
            bannerAd.show = function () {
                var param = {
                    "adId": adId
                };
                var promise = new Promise(function (resolve, reject) {
                    var handler = function (params) {
                        if (params == 0) {
                            resolve();
                        } else {
                            reject();
                        }
                    };
                    self.bannerAds[adId].mHandler = handler;
                });
                GameSDK.callNative("ad_banner_show", param);
                return promise;
            };
            //隐藏 banner 广告
            bannerAd.hide = function () {
                var param = {
                    "adId": adId
                };
                GameSDK.callNative("ad_banner_hide", param);
            };

            // 销毁 banner 广告
            bannerAd.destroy = function () {
                var param = {
                    "adId": adId
                };
                self.bannerAds[adId] = null;
                GameSDK.callNative("ad_banner_destroy", param);
            };

            //监听 banner 广告加载事件
            bannerAd.onLoad = function (cb) {
                this.onLoad = cb;
            };

            //监听 banner 广告错误事件
            bannerAd.onError = function (cb) {
                this.onError = cb;
            };

            //监听 banner 广告尺寸变化事件  未实现
            bannerAd.onResize = function (cb) {
                this.onResize = cb;
            };

            //取消监听 banner 广告加载事件
            bannerAd.offLoad = function (cb) {
                if( this.onLoad == cb ){
                    this.onLoad = null;
                }
            };

            //取消监听 banner 广告错误事件
            bannerAd.offError = function (cb) {
                if( this.onError == cb ){
                    this.onError = null;
                }
            };

            //取消监听 banner 广告尺寸变化事件
            bannerAd.offResize = function (cb) {
                if( this.onResize == cb ){
                    this.onResize = null;
                }
            };
            self.bannerAds[adId] = bannerAd;
            GameSDK.callNative("ad_banner_create", param);
            return bannerAd;
        },
        // 创建插屏广告
        // 参数
        //   adId: string 广告序号ID
        //   interstitialAdId: string 插屏广告ID
        //   style: int 广告类型 1 全屏 2 半屏
        createInterstitialAd : function (adId, interstitialAdId, style) {
            var self = this;
            var interstitialAd = {"adId": adId};
            var param = {
                "adId": adId,
                "interstitialAdId": interstitialAdId,
                "style": style,
            };

            //显示 插屏 广告
            interstitialAd.show = function () {
                var param = {
                    "adId": adId
                };

                var promise = new Promise(function (resolve, reject) {
                    var handler = function (params) {
                        if (params == 0) {
                            resolve();
                        } else {
                            reject();
                        }
                    };
                    self.interstitialAds[adId].mHandler = handler;
                });
                GameSDK.callNative("ad_interstitial_show", param);
                return promise;
            };

            // 隐藏 插屏 广告
            interstitialAd.hide = function () {
                var param = {
                    "adId": adId
                };
                GameSDK.callNative("ad_interstitial_hide", param);
            };

            // 销毁 插屏 广告
            interstitialAd.destroy = function () {
                var param = {
                    "adId": adId
                };
                self.interstitialAds[adId] = null;
                GameSDK.callNative("ad_interstitial_destroy", param);
            };

            // 监听 插屏 广告加载事件
            interstitialAd.onLoad = function (cb) {
                this.onLoad = cb;
            };

            // 监听 插屏 广告错误事件
            interstitialAd.onError = function (cb) {
                this.onError = cb;
            };
            // 监听 插屏 广告尺寸变化事件
            interstitialAd.onResize = function (cb) {
                this.onResize = cb;
            };

            //取消监听 插屏 广告加载事件
            interstitialAd.offLoad = function (cb) {
                if( this.onLoad == cb ){
                    this.onLoad = null;
                }
            };

            //取消监听 插屏 广告错误事件
            interstitialAd.offError = function (cb) {
                if( this.onError == cb ){
                    this.onError = null;
                }
            };

            //取消监听 插屏 广告尺寸变化事件
            interstitialAd.offResize = function (cb) {
                if( this.onResize == cb ){
                    this.onResize = null;
                }
            };
            self.interstitialAds[adId] = interstitialAd;
            GameSDK.callNative("ad_interstitial_create", param);
            return interstitialAd;
        },
        // 创建激励视频广告
        // 参数
        //   videoAdId: string 视频广告ID
        //   screenOrientation: int 广告类型 1 横屏 2 竖屏
        createRewardedVideoAd : function (adId, videoAdId, screenOrientation) {
            var self = this;
            var rewardedVideoAd = {"adId": adId};
            var param = {
                "adId": adId,
                "videoAdId": videoAdId,
                "screenOrientation": screenOrientation,
            };

            //显示激励视频广告
            rewardedVideoAd.show = function () {
                var param = {
                    "adId": adId
                };

                var promise = new Promise(function (resolve, reject) {
                    var handler = function (params) {
                        if (params == 0) {
                            resolve();
                        } else {
                            reject();
                        }
                    };
                    self.videoAds[adId].mHandler = handler;
                });
                GameSDK.callNative("ad_rewardedVideo_show", param);
                return promise;
            };

            // 隐藏激励视频广告
            rewardedVideoAd.hide = function () {
                var param = {
                    "adId": adId
                };
                GameSDK.callNative("ad_rewardedVideo_hide", param);
            };

            // 销毁激励视频广告
            rewardedVideoAd.destroy = function () {
                var param = {
                    "adId": adId
                };
                self.videoAds[adId] = null;
                GameSDK.callNative("ad_rewardedVideo_destroy", param);
            };

            //监听激励视频广告加载事件
            rewardedVideoAd.onLoad = function (cb) {
                this.onLoad = cb;
            };

            //监听激励视频错误事件
            rewardedVideoAd.onError = function (cb) {
                this.onError = cb;
            };

            //监听用户点击 关闭广告 按钮的事件
            rewardedVideoAd.onClose = function (cb) {
                this.onClose = cb;
            };

            //监听激励视频广告加载事件
            rewardedVideoAd.offLoad = function (cb) {
                if( this.onLoad == cb ){
                    this.onLoad = null;
                }
            };

            //监听激励视频错误事件
            rewardedVideoAd.offError = function (cb) {
                if( this.onError == cb ){
                    this.onError = null;
                }
            };

            //监听用户点击 关闭广告 按钮的事件
            rewardedVideoAd.offClose = function (cb) {
                if( this.onClose == cb ){
                    this.onClose = null;
                }
            };
            self.videoAds[adId] = rewardedVideoAd;
            GameSDK.callNative("ad_rewardedVideo_create", param);
            return rewardedVideoAd;
        }
    };
})()


