"use strict"

//国内=854584713  海外=673307600
var gameId = 673307600; // Game ID
//bannerId 国内=903285741 海外=942573186
var bannerId="942573186";
//interstitialId 国内=158439062 海外=173690482
var interstitialId="173690482";
//videoId 国内=804975613 海外=475126903
var videoId="475126903";

var banner = {};
var interstitialAd = {};
var videoAd = {};

var bannerLoaded=false;
var interstitialAdLoaded=false;
var videoAdLoaded=false;

//游戏调用GameSDK.init及AdSDK.init初始化，调用前需要将回调函数注册好
function init() {
    //设置初始化回调函数
    GameSDK.setOnInitCB(onInit);
    //设置平台进入前台回调函数
    GameSDK.setOnResumeCB(onResume);
    //设置平台进入后台回调函数
    GameSDK.setOnPauseCB(onPause);
    //设置平台声音控制回调函数
    GameSDK.setOnAudioCB(onAudio);
    //设置支付回调函数
    GameSDK.setOnPayCB(onPay);
    //初始化GameSDK
    GameSDK.init(gameId);
    //初始化AdSDK,依赖GameSDK
    AdSDK.init();
}

//平台收到游戏init初始化后，会通过该函数回传用户信息
//    error,  // int 错误代码：0、成功（见文末）
//    userId,  // int当前玩家id
//    nickName,  // string当前玩家名称
//    headUrl,  // string 当前玩家头像地址
//    location,  //string当前玩家地址
//    sex,  // string 玩家性别："f" - 女；"m" - 男；"x" – 未知
//    age // int 当前玩家年龄
function onInit(param) {
    console.log("onInit:", "userId "+ param.userId+" nickName "+ param.nickName+ " headUrl "+param.headUrl+" age "+param.age);
}

//退出游戏
function quit() {
    GameSDK.quit(1);
}

//游戏调用该函数，拉起平台支付
//   orderId: int 订单号
//   goodsName: string 商品名称
//   goodsDesc: string 商品描述
//   orderAmount: int 订单金额
//   extension： string 透传参数
//   notifyURL: string 支付付款通知地址
function pay(){
    GameSDK.pay(+new Date,"钻石","钻石恒久远，一颗永流传",1,"支付","支付付款通知地址")
}

//平台收到游戏pay函数后,拉起支付，支付结果通过该函数返回
//    result,  // int 支付结果 0、成功，非0、错误号
//    message   // string 描述
function onPay(param) {
    console.log("onPay:", " result "+param.result+" message "+param.message);
}

//设置平台横屏
function setScreenLandescape() {
    GameSDK.setOrientation(0);
}

//设置平台竖屏
function setScreenPortrait() {
    GameSDK.setOrientation(1);
}

//平台进入前台，通过该函数通知游戏
function onResume() {
    console.log("onResume:");
}

//平台进入后台，通过该函数通知游戏
function onPause() {
    console.log("onPause:");
}

//平台通知游戏 开启关闭声音
//    enable,  // int 是否开启 0、关，1、开
//    volume   // int 音量 1 - 100
function onAudio(param) {
    console.log("onAudio:", " enable "+param.enable+" volume "+param.volume);
}


   // 创建Banner广告 创建一次即可，自动刷新广告
   // 参数
   //   adId: string 广告序号ID 游戏自定义
   //   bannerAdId: string Banner广告ID 需后台申请
   //   position: int 广告位置 1 屏幕顶部 2 屏幕底部
function createBannerAd() {
    bannerLoaded = false;
    banner = AdSDK.createBannerAd("1", bannerId, 2);
    //注册onLoad函数,游戏调用创建banner时，AdSDK通知平台创建广告，当创建成功，会执行该回调函数通知游戏
    banner.onLoad(function () {
        bannerLoaded=true;
        console.log("banner 创建banner成功，可以调用展示");
    });
    //注册onError函数,游戏调用创建banner时，AdSDK通知平台创建广告，当创建失败，会执行该回调函数通知游戏
    banner.onError(function (param) {
        destroyBannerAd();
        console.log("banner 创建banner失败，错误码 = ", param.errorCode);
    });
    console.log('banner 游戏创建banner广告');
}


function showBannerAd() {
    if(bannerLoaded){//Object.keys(banner).length &&
        banner.show().then(function () {
            console.log('banner 广告显示成功')
        }, function (err) {
            console.log('banner 广告显示失败')
        });
        console.log('banner 展示banner广告');
    }else{
        console.log('banner 未加载成功');
    }
}

function hideBannerAd() {
    banner.hide();
    console.log('banner 隐藏banner广告');
}


function destroyBannerAd() {
    bannerLoaded=false;
    banner.destroy();
    console.log('banner 销毁banner广告');
}

     // 创建插屏广告 展示一次调用一次创建
     // 参数
     //   adId: string 广告序号ID 游戏自定义
     //   interstitialAdId: string 插屏广告ID 需后台申请
     //   style: int 广告类型 1 全屏 2 半屏
function createInterstitialAd() {
    interstitialAd = AdSDK.createInterstitialAd("1", interstitialId, 2);
     //注册onLoad函数,游戏调用创建插屏时，AdSDK通知平台创建广告，当创建成功，会执行该回调函数通知游戏
    interstitialAd.onLoad(function () {
        interstitialAdLoaded=true;
        console.log("interstitialAd 创建插屏成功，可以调用展示");
    });
     //注册onError函数,游戏调用创建插屏时，AdSDK通知平台创建广告，当创建失败，会执行该回调函数通知游戏
    interstitialAd.onError(function (param) {
        console.log("interstitialAd 创建插屏失败，错误码 = ", param.errorCode);
        destroyInterstitialAd();//加载广告失败，销毁
    });
    console.log("interstitialAd 游戏创建插屏广告");
}

function showInterstitialAd() {
    if(interstitialAdLoaded){
        interstitialAd.show().then(function () {
            console.log("interstitialAd 广告显示成功,可给与奖励")
            destroyInterstitialAd();//展示成功后，销毁
        }, function (err) {
            console.log("interstitialAd 广告显示失败")
        })
        console.log("interstitialAd 游戏展示插屏广告");
    }else{
        console.log("interstitialAd 广告未加载成功");
    }
}

function hideInterstitialAd() {
    interstitialAd.hide();
    console.log("interstitialAd 游戏隐藏插屏广告");
}


function destroyInterstitialAd() {
    interstitialAdLoaded=false;
    interstitialAd.destroy();
    console.log("interstitialAd 游戏销毁插屏广告");
}

    // 创建激励视频广告 展示一次创建一次
    // 参数
    //   adId: string 广告序号ID 游戏自定义
    //   videoAdId: string 视频广告ID 需后台申请
    //   screenOrientation: int 广告类型 1 横屏 2 竖屏
function createRewardedVideoAd() {
    videoAd = AdSDK.createRewardedVideoAd("1", videoId, 1);
    //注册onLoad函数,游戏调用创建视频时，AdSDK通知平台创建广告，当创建成功，会执行该回调函数通知游戏
    videoAd.onLoad(function () {
        videoAdLoaded=true;
        console.log("rewardedvideoAd 创建视频广告成功，可以调用展示")
    });
    //注册onError函数,游戏调用创建视频时，AdSDK通知平台创建广告，当创建失败，会执行该回调函数通知游戏
    videoAd.onError(function (param) {
        console.log("rewardedvideoAd 创建视频广告失败，错误码 = ", param.errorCode);
        destroyRewardedVideoAd();//加载广告失败，销毁
    });
     //注册onClose函数,视频广告播放完整或主动退出观看视频时，展示落地页面，右上方关闭视频按钮触发，该函数执行。
    videoAd.onClose(function () {
        console.log("rewardedvideoAd 视频广告关闭,回到游戏页面");
        destroyRewardedVideoAd();//视频广告关闭，销毁
    });
    console.log("rewardedvideoAd 游戏创建视频广告");
}

function showRewardedVideoAd() {
    if(videoAdLoaded){
         videoAd.show().then(function () {
            console.log("rewardedvideoAd 广告显示成功，可以给与奖励")
         }, function (err) {
            console.log("rewardedvideoAd 广告显示失败")
         })
        console.log("rewardedvideoAd 游戏展示视频广告");
    }else{
        console.log("rewardedvideoAd 视频广告未加载成功");
    }

}

function destroyRewardedVideoAd() {
    videoAdLoaded = false;
    videoAd.destroy();
    console.log("rewardedvideoAd 游戏销毁视频广告");
}



